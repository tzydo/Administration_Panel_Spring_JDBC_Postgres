package com.pl.spring.createWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Data;

@Data
public class CreateGroupRoleWindow {

    private Dialog<Pair<String, String>> dialog;
    private TextField groupName;

    public CreateGroupRoleWindow(){
       dialog = new Dialog<>();
        dialog.setTitle("Create Group Role");
        dialog.setHeaderText("Create your new Group");

        ButtonType buttonName = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonName, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        groupName = new TextField();
        groupName.setPromptText("name");

        grid.add(new Label("Group name:"), 0, 0);
        grid.add(groupName, 1, 0);

        dialog.getDialogPane().setContent(grid);
    }

}
