package com.pl.spring.createWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Data;

@Data
public class TableName {
    private Dialog<Pair<String, String>> dialog;
    private TextField name;

    public TableName(){
        dialog = new Dialog<>();
        dialog.setTitle("Create Table");
        dialog.setHeaderText("Create new Table");

        ButtonType create = new ButtonType("Create",ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(create, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        name = new TextField();
        name.setPromptText("name");

        grid.add(new Label("Table name :"), 0, 0);
        grid.add(name, 1, 0);

        dialog.getDialogPane().setContent(grid);
    }

}
