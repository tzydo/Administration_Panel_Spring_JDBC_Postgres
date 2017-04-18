package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.databaseItems;

import com.pl.spring.createWindows.CreateDatabaseWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@NoArgsConstructor
public class DatabaseLabel extends Label{

    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private String name;
    private ObjectWindow objectWindow;
    private StackPane stackPane;
    private ContextMenu cm;
    private MenuItem refresh, add;

    public DatabaseLabel(String name,
                         JdbcTemplate jdbcTemplate,
                         StatisticWindow statisticWindow,
                         ObjectWindow objectWindow,
                         StackPane stackPane) {

        this.objectWindow = objectWindow;
        this.name = name;
        this.stackPane = stackPane;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.cm = new ContextMenu();
        refresh = new MenuItem("Odświerz");
        refresh.setOnAction(e -> {
            objectWindow.build();
        });
        add = new MenuItem("Dodaj Baze");




        //!!!!!!!!!!!!!!!!!!!CREATE ROLE WINDOW!!!!!!!!!!!!!!!!!!!!
        add.setOnAction(e->{
            CreateDatabaseWindow createDatabaseWindow = new CreateDatabaseWindow();
            createDatabaseWindow.getDialog().showAndWait();

            //http://code.makery.ch/blog/javafx-dialogs-official/   <-tutorial
            //!!!!!!!!!!!!!!!!!!!!NEW Group BE NULL OR EMPTY
            if(createDatabaseWindow.getDatabaseName().getText().isEmpty()
                    ||  createDatabaseWindow.getDatabaseName().getText().equals(null)
                    ||  createDatabaseWindow.getDatabaseName().getText().equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ createDatabaseWindow.getDatabaseName().getText());
                String sql= "create database "+createDatabaseWindow.getDatabaseName().getText()+";";
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }
        });



        this.cm.getItems().addAll(refresh, add);
        this.setText(name);


        addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(stackPane, t.getScreenX(), t.getScreenY());
                } else {
                    cm.hide();
                }

            }

        });
    }

}
