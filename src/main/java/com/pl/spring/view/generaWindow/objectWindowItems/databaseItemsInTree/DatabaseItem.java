package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;

import com.pl.spring.view.generaWindow.GeneralWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class DatabaseItem extends Label{

    private ContextMenu cm;
    private MenuItem refresh, newTable,delete;
    private String name;
    private StackPane root;
    private ObjectWindow objectWindow;

    public DatabaseItem(String name, ObjectWindow objectWindow, StackPane root){
        this.setText(name);

        this.objectWindow = objectWindow;
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
            this.objectWindow.build();
            cm.hide();
        });

        //BAZA MOZE BYC UZYWANA PRZEZ WIELE APLIKACJI
        delete.setOnAction(e -> {
            JdbcTemplate jdbcTemplate = this.objectWindow.getJdbcTemplate();

            String sql = "SELECT * FROM pg_stat_activity WHERE datname = '" + name+ "';";
            jdbcTemplate.execute(sql);

            sql = "SELECT pg_terminate_backend (pg_stat_activity.pid) FROM pg_stat_activity WHERE            pg_stat_activity.datname = '"+ name +"';";

            jdbcTemplate.execute(sql);
            sql = "DROP DATABASE " + name + ";";

            jdbcTemplate.execute(sql);
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
