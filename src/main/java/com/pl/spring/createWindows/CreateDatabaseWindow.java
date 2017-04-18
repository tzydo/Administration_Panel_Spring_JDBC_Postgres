package com.pl.spring.createWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Data;

@Data
public class CreateDatabaseWindow {

    private Dialog<Pair<String, String>> dialog;
    private TextField databaseName;

    public CreateDatabaseWindow() {
        dialog = new Dialog<>();
        dialog.setTitle("Create Database");
        dialog.setHeaderText("Create new Database");

        ButtonType buttonName = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonName, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        databaseName = new TextField();
        databaseName.setPromptText("name");

        grid.add(new Label("Database name:"), 0, 0);
        grid.add(databaseName, 1, 0);

        dialog.getDialogPane().setContent(grid);
    }
}
