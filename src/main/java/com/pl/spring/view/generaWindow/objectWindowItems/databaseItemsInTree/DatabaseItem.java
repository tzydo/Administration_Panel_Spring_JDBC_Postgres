package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class DatabaseItem extends Label{

    private ContextMenu cm;
    private MenuItem refresh, newTable,delete;
    private String name;
    private StackPane root;

    public DatabaseItem(String name, StackPane root){
        this.setText(name);

        this.name = name;
        this.root = root;
        setStyle("-fx-padding: 1 100 1 10;\n" +
                "-fx-border-color: black;\n" +
                "-fx-background-color: gray");

        cm = new ContextMenu();
        refresh = new MenuItem("Odświerz");
        newTable = new MenuItem("Nowa tabela");
        delete = new MenuItem("Usuń");
        refresh.setOnAction(e -> {
//            generalWindow.reloadLeftWindow();
            cm.hide();
        });

        delete.setOnAction(e -> {
//            sqlQuery.deleteItemFromTreeDatabase(this.name);
//            generalWindow.reloadLeftWindow();
            cm.hide();
        });

        cm.getItems().addAll(refresh,newTable,delete);



        addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(root, t.getScreenX(), t.getScreenY());
                } else {
                    cm.hide();
                }

            }

        });


    }
}
