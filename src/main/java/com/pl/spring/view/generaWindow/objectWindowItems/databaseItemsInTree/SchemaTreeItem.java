package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;


import com.pl.spring.mapper.TableInfoMapper;
import com.pl.spring.model.TableInfo;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TableViewToStatisticWindow.TableViewTableInfo;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TableViewToStatisticWindow.TableViewTableModel;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SchemaTreeItem extends TreeItem<Label> {

    private Label schemaLabel;
    private JdbcTemplate jdbcTemplate;
    private String sql = null;
    private StatisticWindow statisticWindow;
    private TableInfo tableInfo;
    private TableViewTableInfo tableViewTableInfo;


    public SchemaTreeItem(Label schemaLabel, JdbcTemplate jdbcTemplate, StatisticWindow statisticWindow) {
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.setValue(schemaLabel);
        this.schemaLabel = schemaLabel;
        build();
    }

    private void build() {

        this.schemaLabel.setOnMouseClicked(e->{
            sql = "SELECT tablename FROM pg_catalog.pg_tables WHERE " +
                    "schemaname != 'pg_catalog' AND " +
                    "schemaname != 'information_schema' AND schemaname=?;";

            List<String> strings = jdbcTemplate.queryForList(sql,String.class,
                    schemaLabel.getText().toString());


            this.getChildren().clear();


            strings.forEach(ex->{
                addTableAndShowInStaticView(ex.toString());
            });

        });


    }

    private void addTableAndShowInStaticView(String tabName) {
        Label n = new Label("Table:  " +tabName);
        n.setOnMouseClicked(e->{
            sql = "select * from pg_tables where tablename = ?";
            tableInfo = jdbcTemplate.queryForObject(sql,new TableInfoMapper(),tabName);
            System.out.println(tableInfo.toString());
            buildRole();
        });
        this.getChildren().add(new TreeItem(n));
    }



    private void buildRole() {
        tableViewTableInfo = new TableViewTableInfo();
        tableViewTableInfo.build(tableInfo);

        statisticWindow.setValueInProperties(tableViewTableInfo.getTableView());
    }

}

