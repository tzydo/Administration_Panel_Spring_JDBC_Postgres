package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;


import com.pl.spring.mapper.TableModelMapper;
import com.pl.spring.model.TableModel;
import com.pl.spring.model.User;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow.TableViewTableModel;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@PropertySource("classpath:dataConfig.property")
public class Schema extends TreeItem<Label> {

    private JdbcTemplate jdbcTemplate;
    private Connection connection;
    private StatisticWindow statisticWindow;
    private User user;
    private Environment environment;
    Set<String> itemsNoRepeat = new TreeSet<>();
    private String name;
    private String schemaName = "";
    private TreeItem treeItem;
    private Label schemaLabel;
    private List list;
    private TableViewTableModel viewTableModel;
    private  List<TableModel> tableModelList;

    public Schema(String name,
                  String schemaName,
                  User user,
                  Environment environment,
                  StatisticWindow statisticWindow) {

        this.schemaName = schemaName;
        this.statisticWindow = statisticWindow;
        this.name = name;
        this.user = user;
        this.environment = environment;
        schemaLabel = new Label(this.name);
        schemaLabel.setStyle("-fx-background-color : Red");

        try {
        connection = DriverManager
                .getConnection(environment.getProperty("UrlToSchema") +
                        schemaName, user.getLogin(), user.getPassword());

        jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(connection, false));
        } catch (Exception e) {
            System.out.println("cos nie tak z polaczeniem w Schema  ");
        }


        schemaLabel.setOnMouseClicked(e -> {
            loadSchema();
            loadInfoToStatisticWindow();
        });

        this.setValue(schemaLabel);
    }


    private void loadInfoToStatisticWindow() {

       String param = (String) list.stream()
               .map(e->"'"+e.toString()+"'")
               .collect(Collectors.joining(","));


        String sql = "SELECT tablename, tableowner FROM pg_tables WHERE schemaname IN(" + param +");";

            tableModelList = jdbcTemplate.query(sql,new TableModelMapper());
            if(!tableModelList.isEmpty()){
                buildRole();
            }
    }


    private void buildRole() {
        viewTableModel = new TableViewTableModel();
        viewTableModel.build(tableModelList);

            statisticWindow.setValueInProperties(viewTableModel.getTableView());
    }

    public void loadSchema() {

            String sql = "SELECT nspname FROM pg_catalog.pg_namespace " +
                    "WHERE nspname != 'pg_toast' AND nspname != 'pg_temp_1' " +
                    "AND nspname!= 'pg_toast_temp_1' AND nspname != 'pg_catalog' " +
                    "AND nspname != 'information_schema';";


            list =jdbcTemplate.queryForList(sql, String.class);
            getChildren().clear();  //usuwanie wszystkiego z listy zeby uniknac powtorzen

                list.forEach(s -> {
                    getChildren().add(new SchemaTreeItem
                            (new Label(s.toString()),jdbcTemplate,statisticWindow));
                });



            schemaLabel.setText(name + "(" + list.size() + ")");

    }
}