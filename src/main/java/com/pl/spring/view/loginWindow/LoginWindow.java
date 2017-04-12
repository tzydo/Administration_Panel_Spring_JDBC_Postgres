package com.pl.spring.view.loginWindow;

import com.pl.spring.windowControllers.LoginWindowController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginWindow {

    private Group root;
    private Scene scene;
    private Rectangle background;
    private Label welcome, information, information2, information3;
    private VBox vbox;
    private HBox hbox;
    private TextField login;
    private PasswordField password;
    private Button button;

    @Autowired
    LoginWindowController loginWindowController;

    public LoginWindow() {
        buildScene();
    }

    public void buildScene() {

        root = new Group();
        scene = new Scene(root, 300, 250, Color.rgb(0, 0, 0, 0));
        scene.getStylesheets().add("css/LoginWindowStyle.css");


        // background
        background = new Rectangle(300, 250);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setId("background");

        vbox = new VBox(7);
        vbox.setId("vbox");
        // vbox.setPadding(new Insets(10, 0, 0, 25));
        vbox.setAlignment(Pos.CENTER);
        welcome = new Label("Welcome");
        welcome.setId("welcome");
        information = new Label("");
        information2 = new Label("");
        information3 = new Label("");

        login = new TextField();
        login.setId("login");
        login.setPromptText("username");

        password = new PasswordField();
        password.setId("password");
        password.setPromptText("password");

        hbox = new HBox();
        button = new Button("Sign In");

        button.setId("button");
        button.setOnAction((event) -> {
            loginWindowController.checkLogin(login.getText(), password.getText());
        });

        hbox.getChildren().add(button);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        vbox.getChildren().addAll(welcome, login, password, hbox, information, information2, information3);
        root.getChildren().addAll(background, vbox);
    }

    public void setInformation(String text, Color color, int nr) {
        if (nr == 1) {
            information.setTextFill(color);
            this.information.setText(text);
        }
        if (nr == 2) {
            information2.setTextFill(color);
            this.information2.setText(text);
        }
        if (nr == 3) {
            information3.setTextFill(color);
            this.information3.setText(text);
        }

    }
}
