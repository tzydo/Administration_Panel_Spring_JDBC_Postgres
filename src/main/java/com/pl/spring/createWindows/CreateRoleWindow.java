package com.pl.spring.createWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Data;

@Data
public class CreateRoleWindow {

    private Dialog<Pair<String, String>> dialog;
    private TextField username;
    private PasswordField password;


    public CreateRoleWindow(){
        dialog = new Dialog<>();
        dialog.setTitle("Create Role");
        dialog.setHeaderText("Create yout new Role");

        ButtonType nameRole = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(nameRole, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        username = new TextField();
        username.setPromptText("name");
        password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);


        dialog.getDialogPane().setContent(grid);
    }

}
