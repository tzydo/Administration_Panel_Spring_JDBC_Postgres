package com.pl.spring.windowControllers;

import com.pl.spring.connection.Connect;
import com.pl.spring.model.User;
import com.pl.spring.view.loginWindow.LoginWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class LoginWindowController {

    @Autowired
    LoginWindow loginWindow;

    @Autowired
    User user;

    @Autowired
    Connect connect;

    @Autowired
    GeneralWindowController generalWindowController;

    private Stage primaryStage;
    private Scene scene;

    public void show() {
        scene = loginWindow.getScene();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void checkLogin(String login, String password) {
        user.setLogin(login);
        user.setPassword(password);
        connect.initialized();
    }

    public void nextStage() {
        primaryStage.hide();
        generalWindowController.setStage();
        generalWindowController.show();
    }

}


