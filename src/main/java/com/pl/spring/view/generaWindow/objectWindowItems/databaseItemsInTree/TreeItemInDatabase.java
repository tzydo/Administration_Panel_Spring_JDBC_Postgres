package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;

import com.pl.spring.connection.Connect;
import com.pl.spring.model.User;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.util.TreeSet;


@Data
@NoArgsConstructor
public class TreeItemInDatabase extends TreeItem<Label> {

    private String nameItem;
    private TreeItem schemat;
    private JdbcTemplate jdbcTemplate;
    private User user;
    private Environment environment;
    private StatisticWindow statisticWindow;
    private StackPane stackPane;
    private ObjectWindow objectWindow;

    public TreeItemInDatabase(String nameItem,
                              JdbcTemplate jdbcTemplate,
                              User user,
                              Environment environment,
                              ObjectWindow objectWindow,
                              StackPane stackPane,
                              Label label,
                              StatisticWindow statisticWindow){

        this.setValue(label);
        this.jdbcTemplate = jdbcTemplate;
        this.user = user;
        this.environment = environment;
        this.objectWindow = objectWindow;
        this.stackPane = stackPane;
        this.nameItem = nameItem;
        this.statisticWindow = statisticWindow;

        addSchema();
    }

    private void addSchema() {
        this.schemat = new Schema(
                "Schematy",this.nameItem,
                user, environment,
                statisticWindow, stackPane,
                objectWindow);

        getChildren().add(this.schemat);

    }
}
