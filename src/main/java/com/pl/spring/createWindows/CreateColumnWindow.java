package com.pl.spring.createWindows;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Data;

@Data
public class CreateColumnWindow {

    private Dialog<Pair<String, String>> dialog;
    private TextField name;
    private ChoiceBox dataType;
    private String selectedDataType;


    public CreateColumnWindow(){
        dialog = new Dialog<>();
        dialog.setTitle("Create column");
        dialog.setHeaderText("Create new column");

        ButtonType add = new ButtonType("Dodaj kolumne",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Wykonaj",ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(add, cancel);



        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        dataType = new ChoiceBox(FXCollections.observableArrayList(
                "bit", "boolean", "integer","text","char","varchar")
        );


        dataType.setOnAction(e->{
            selectedDataType= dataType.getValue().toString();
        });
        name = new TextField();
        name.setPromptText("name");

        grid.add(new Label("Column name :"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Set type :"),0,1);
        grid.add(dataType,1,1);

        dialog.getDialogPane().setContent(grid);
    }

}
