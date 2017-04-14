package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;

import javafx.scene.control.Alert;

public class ErrorWindow {

    public ErrorWindow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Brak Uprawnien!");

        alert.showAndWait();
    }
}
