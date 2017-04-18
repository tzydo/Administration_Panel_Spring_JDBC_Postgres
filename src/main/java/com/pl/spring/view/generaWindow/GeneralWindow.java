package com.pl.spring.view.generaWindow;


import com.pl.spring.connection.Connect;
import com.pl.spring.createWindows.CreateDatabaseWindow;
import com.pl.spring.createWindows.CreateGroupRoleWindow;
import com.pl.spring.createWindows.CreateRoleWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.SqlWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralWindow {

   @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StatisticWindow statisticWindow;

    @Autowired
    private SqlWindow sqlWindow;

    @Autowired
    public ObjectWindow objectWindow;

    private Scene scene;
    private Group root;
    private StackPane stack;


    private VBox vbox, vBoxInCenter;
    private HBox hbox, hboxInCenter;
    private Stage primaryStage;
    private Menu fileMenu, helpMenu;
    private MenuItem fileItemAddExit;

    private MenuItem helpItemAboutMe;
    private Button newDatabase, newTable, refreashButon, newRole;
    private MenuBar menuBar;


    public void build() {
        root = new Group();
        scene = new Scene(root, 800, 600);
        stack = new StackPane();

        vbox = new VBox(4);
        initializeMenuBar();
        initializeButtonMenu();
        hboxInCenter = new HBox(2);
        vBoxInCenter = new VBox(2);
        loadCenterPane();

        vbox.getChildren().addAll(menuBar, hbox, hboxInCenter);
        stack.getChildren().add(vbox);
        root.getChildren().add(stack);
        primaryStage.setResizable(false);
    }

    private void initializeMenuBar() {
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        fileMenu = new Menu("Plik");
        helpMenu = new Menu("Pomoc");

        //<---------------------File Menu Button------------------------------->
        fileItemAddExit = new MenuItem("Wyjście");
        fileItemAddExit.setOnAction(e->{
            System.exit(0);
        });

        helpItemAboutMe = new MenuItem("O Programie");
        helpItemAboutMe.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About me");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!\n This program was made for test skills :D");
            alert.showAndWait();
        });

        fileMenu.getItems().addAll(fileItemAddExit);
        helpMenu.getItems().addAll(helpItemAboutMe);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

    }

    private void initializeButtonMenu() {
        hbox = new HBox(6);
        hbox.setAlignment(Pos.BASELINE_CENTER);


        //NOWA BAZA DANYCH PRZYCISK
        newDatabase = new Button("Nowa Baza");

        newDatabase.setOnAction(e->{
            CreateDatabaseWindow createDatabaseWindow = new CreateDatabaseWindow();
            createDatabaseWindow.getDialog().showAndWait();

            if(createDatabaseWindow.getDatabaseName().getText().isEmpty()
                    ||  createDatabaseWindow.getDatabaseName().getText().equals(null)
                    ||  createDatabaseWindow.getDatabaseName().getText().equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ createDatabaseWindow.getDatabaseName().getText());
                String sql= "create database "+createDatabaseWindow.getDatabaseName().getText()+";";
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }
        });


        newTable = new Button("Nowa grupa ról");
        newTable.setOnAction(e->{
            CreateGroupRoleWindow createRoleWindow = new CreateGroupRoleWindow();
            createRoleWindow.getDialog().showAndWait();
            String groupName = createRoleWindow.getGroupName().getText().toString();
        if(groupName.isEmpty()
                    ||  groupName.equals(null)
                    ||  groupName.equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ groupName);
                String sql= "create group "+groupName+";";
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }
        });




        //ODSWIERZ PRZYCISK
        refreashButon = new Button("Odświerz");
        refreashButon.setOnAction(e->{
            objectWindow.build();
        });




        //NOWY UZYTKOWNIK PRZYCISK
        newRole = new Button("Nowy użytkownik");
        newRole.setOnAction(e->{
            CreateRoleWindow createRoleWindow = new CreateRoleWindow();
            createRoleWindow.getDialog().showAndWait();

            String username = createRoleWindow.getUsername().getText().toString();
            String password = createRoleWindow.getPassword().getText().toString();
            if(username.isEmpty()
                    ||  username.equals(null)
                    ||  username.equals("")
                    ||  password.isEmpty()
                    ||  password.equals(null)
                    ||  password.equals("")){
            }else{
                String sql= "CREATE USER "+username+" WITH PASSWORD '"+password+"';";
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }
        });


        hbox.getChildren().addAll(newDatabase, newTable, refreashButon, newRole);

    }

    private void loadCenterPane() {
        hboxInCenter.setStyle("-fx-padding: 10 10 10 15");
        vBoxInCenter.setStyle("-fx-padding: 0 10 0  10");

        vBoxInCenter.getChildren().add(statisticWindow);
        vBoxInCenter.getChildren().add(sqlWindow.getVbox());

        objectWindow.build();
        hboxInCenter.getChildren().addAll(objectWindow,vBoxInCenter);
    }


    public Scene getScene() {
        return scene;
    }

}

