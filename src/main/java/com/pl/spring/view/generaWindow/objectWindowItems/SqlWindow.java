package com.pl.spring.view.generaWindow.objectWindowItems;

import javafx.scene.layout.StackPane;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class SqlWindow extends StackPane {

    public SqlWindow(){
        setMinSize(520, 220);
        setStyle("-fx-background-color: YELLOW;");
    }

}