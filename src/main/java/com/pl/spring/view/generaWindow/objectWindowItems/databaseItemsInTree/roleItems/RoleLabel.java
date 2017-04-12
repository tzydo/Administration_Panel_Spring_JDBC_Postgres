package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems;

import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Set;
import java.util.TreeSet;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleLabel extends Label {

    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private String name;
    private TableView tableView;
    private TableColumn roleName;
    private TableColumn owner;
    private TableColumn note;
    private Set<String> list = new TreeSet<>();



    public RoleLabel(String name, JdbcTemplate jdbcTemplate,StatisticWindow statisticWindow) {
        this.name = name;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;

        this.setText(name);
        this.setOnMouseClicked(e -> {

            //ADD ROLE w menu!!!!!!!!!!!!!!!!!!!!
            System.out.println("cos cos");

        });
    }
}

