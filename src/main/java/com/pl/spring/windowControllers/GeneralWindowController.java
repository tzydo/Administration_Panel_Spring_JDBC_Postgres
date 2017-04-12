package com.pl.spring.windowControllers;

import com.pl.spring.connection.Connect;
import com.pl.spring.view.generaWindow.GeneralWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GeneralWindowController {

    private Stage primaryStage;
    private Scene scene;

    @Autowired
    Connect connect;

    @Autowired
    GeneralWindow generalWindow;


    public void setStage() {
        this.primaryStage = new Stage();
    }

    public void show() {
        generalWindow.setPrimaryStage(primaryStage);
        generalWindow.build();
        scene = generalWindow.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("My postgres pane");
        primaryStage.show();
    }

}