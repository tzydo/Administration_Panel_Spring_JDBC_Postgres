package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;


import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SchemaTreeItem extends TreeItem<Label> {

    private Label schemaLabel;
    private JdbcTemplate jdbcTemplate;

    public SchemaTreeItem(Label schemaLabel, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.setValue(schemaLabel);
        this.schemaLabel = schemaLabel;
        build();
    }

    private void build() {
//        String sql = "SELECT oid,* FROM pg_catalog.pg_namespace WHERE nspname != 'pg_toast' AND nspname != 'pg_temp_1' AND nspname!= 'pg_toast_temp_1' AND nspname != 'pg_catalog' AND nspname != 'information_schema';\n";
//        jdbcTemplate.queryForObject(sql,String.class,schemaLabel.getText().toString());

//        String sql = "SELECT oid,* FROM pg_catalog.pg_namespace;";
//        List<String> strings = (List<String>) jdbcTemplate.queryForMap(sql,String.class);
//        strings.forEach(e-> System.out.println(e));

        this.schemaLabel.setOnMouseClicked(e->{
            String sql = "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema' AND schemaname=?;";
            List<String> strings = jdbcTemplate.queryForList(sql,String.class,schemaLabel.getText().toString());

            strings.forEach(ex->{
                System.out.println(ex.toString());
            });

        });




        addFunktion();
        addSekwens();
        addTable();
        addAutoFunction();
        addView();
    }

    private void addFunktion() {
        this.getChildren().add(new TreeItem(new Label("Funkcje")));
    }

    private void addSekwens() {
        this.getChildren().add(new TreeItem(new Label("Sekwencje")));
    }

    private void addTable() {
        this.getChildren().add(new TreeItem(new Label("Tabele")));
    }

    private void addAutoFunction() {
        this.getChildren().add(new TreeItem(new Label("Funkcje Wyzwalacza")));
    }

    private void addView() {
        this.getChildren().add(new TreeItem(new Label("Widok")));
    }

}

