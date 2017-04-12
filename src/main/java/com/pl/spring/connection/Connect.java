package com.pl.spring.connection;

import com.pl.spring.model.User;
import com.pl.spring.view.loginWindow.LoginWindow;
import com.pl.spring.windowControllers.LoginWindowController;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
@PropertySource("classpath:dataConfig.property")
public class Connect {


    @Autowired
    LoginWindow loginWindow;

    @Autowired
    LoginWindowController loginWindowController;

    @Autowired
    User user;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Environment environment;


    Connection connection;
    Statement statement;


    public void initialized() {
        if ((loadDriver() && connectToDatabase() && createStatment()) == true){
            loginWindowController.nextStage();
        };
    }



    public Boolean loadDriver() {
        Boolean isLoaded;
        try {
            Class.forName(environment.getProperty("ClassName"));
            loginWindow.setInformation("zaladowano sterownik", Color.LAWNGREEN, 1);
            isLoaded = true;
        } catch (Exception ex) {
            isLoaded = false;
            loginWindow.setInformation("blad sterownika", Color.RED, 1);
            System.out.println("brak sterownika");
        }
        return isLoaded;
    }




    public Boolean connectToDatabase() {
        Boolean isConnected;
        try {
            connection = DriverManager
                    .getConnection(environment.getProperty("Url"), user.getLogin() ,user.getPassword());


            //ustawienie polaczenia z baza dla zalogowanego uzytkownika!!!
            jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(connection,false));


            isConnected = true;
            loginWindow.setInformation("zalogowano", Color.LAWNGREEN, 2);
        } catch (Exception e) {
            loginWindow.setInformation("zly login lub haslo!", Color.RED, 2);
            System.out.println("blad loginu");
            isConnected = false;
        }
        return isConnected;
    }



    public Boolean createStatment() {
        Boolean isStatment;
        try {
            statement = connection.createStatement();

            loginWindow.setInformation("Utworzono statment", Color.LAWNGREEN, 3);
            isStatment = true;
        } catch (Exception e) {
            loginWindow.setInformation("Blad tworzenia statmentu!", Color.RED, 3);
            System.out.println("blad tworzenia statmentu");
            isStatment = false;
        }

        return isStatment;
    }

    public void closeConectionToDatabase() {
        try {
            System.out.println("Zamknieto polaczenie z baza");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Blad zamkniecia bazy!");
        }
    }
}
