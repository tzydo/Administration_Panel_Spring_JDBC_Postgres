package com.pl.spring.doMain;

import com.pl.spring.generalJavaController.JavaController;
import com.pl.spring.windowControllers.LoginWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
public class Main extends Application {

    private static LoginWindowController loginWindowController;
    public static ApplicationContext context;

    public static void main(String[] args) throws Exception {
        context = new AnnotationConfigApplicationContext(JavaController.class);
        loginWindowController = context.getBean(LoginWindowController.class);

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        loginWindowController.setPrimaryStage(primaryStage);
        loginWindowController.show();
    }
}
