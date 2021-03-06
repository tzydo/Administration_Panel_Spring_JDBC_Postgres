package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree;


import com.pl.spring.createWindows.CreateColumnWindow;
import com.pl.spring.createWindows.TableName;
import com.pl.spring.mapper.TableInfoMapper;
import com.pl.spring.model.TableInfo;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow.TableViewTableInfo;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaTreeItem extends TreeItem<Label> {

    private Label schemaLabel;
    private JdbcTemplate jdbcTemplate;
    private String sql = null;
    private StatisticWindow statisticWindow;
    private TableInfo tableInfo;
    private TableViewTableInfo tableViewTableInfo;
    private StackPane stackPane;
    private ObjectWindow objectWindow;
    private ContextMenu cm,cm2;
    private MenuItem removeTable, removeSchema, addTable;
    private CreateColumnWindow createColumnWindow = null;


    public SchemaTreeItem(Label schemaLabel,
                          JdbcTemplate jdbcTemplate,
                          StatisticWindow statisticWindow,
                          StackPane stackPane,
                          ObjectWindow objectWindow) {

        this.stackPane = stackPane;
        this.objectWindow = objectWindow;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.setValue(schemaLabel);
        this.schemaLabel = schemaLabel;
        this.cm = new ContextMenu();
        this.cm2 = new ContextMenu();
        removeTable = new MenuItem("Usuń");
        removeSchema = new MenuItem("Usuń");
        removeSchema.setOnAction(event -> {

                String sql = "Drop schema "+schemaLabel.getText().toString();
                try{
                    jdbcTemplate.execute(sql);
                    objectWindow.build();
                }catch (Exception exception){
                    showError();
                }
                           cm.hide();
        });


        addTable = new MenuItem("Dodaj tabele");
        addTable.setOnAction(event->{
        //CREATE TABLE WINDOW
            String name;

            TableName tableName = new TableName();
            tableName.getDialog().showAndWait();

            if(!tableName.getName().getText().toString().equals("")){
                name = tableName.getName().getText().toString();

                List<String> tableInfo = new ArrayList<>();

                do{
                    createColumnWindow = new CreateColumnWindow();
                    createColumnWindow.getDialog().showAndWait();
                    if(!createColumnWindow.getName().getText().toString().isEmpty()){
                        tableInfo.add(createColumnWindow.getName().getText().toString());
                        tableInfo.add(createColumnWindow.getSelectedDataType());
                    }else break;
                }while(!createColumnWindow.getName().getText().equals(""));

               try{
                   StringBuilder sql = new StringBuilder();
                          sql.append("Create table " + name + "(");

                   for(int i=0;i<tableInfo.size();i++){
                       if(i==(tableInfo.size()-2)){
                           sql.append(tableInfo.get(i)+" ");
                           sql.append(tableInfo.get(i+1));
                           break;
                       }
                       if((i%2)!= 0){
                           sql.append(tableInfo.get(i) + ",");
                       }else {
                           sql.append(tableInfo.get(i) + " ");
                       }
                   }
                   sql.append(");");


                   //TWORZENIE TABELI QUERRY
                   System.out.println(sql);
                   try{
                       jdbcTemplate.execute(sql.toString());
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Table Create!");
                       alert.setHeaderText(null);
                       alert.setContentText("Table create successful!");

                       alert.showAndWait();
                   }catch (Exception ex){

                   }

               }catch (Exception e){
                   System.out.println("Blad dodatia tabeli !!!! w schemaTreeItem");
               }

            }else {
                System.out.println("pusto");
            }
        });


        cm.getItems().addAll(addTable,removeSchema);
        cm2.getItems().add(removeTable);

        build();
    }

    private void build() {
//USUWANIE SCHEMATOW
        this.schemaLabel.setOnMouseClicked(e->{

            if(e.getButton().toString().equals("SECONDARY")){
                cm.show(stackPane, e.getScreenX(), e.getScreenY());
            } else {
                cm.hide();
            }

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
//USUWANIE TABEL
    private void addTableAndShowInStaticView(String tabName) {
        Label n = new Label("Table:  " +tabName);
        removeTable.setOnAction(e->{
            String sql = "Drop table "+tabName;
            try{
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }catch (Exception exception){
                showError();
        }

        });

        n.setOnMouseClicked(ex->{
            if(ex.getButton().toString().equals("SECONDARY")){
                cm2.show(stackPane, ex.getScreenX(), ex.getScreenY());

            } else {
                cm2.hide();
            }


            sql = "select * from pg_tables where tablename = ?";
            tableInfo = jdbcTemplate.queryForObject(sql,new TableInfoMapper(),tabName);
            buildRole();
        });
        this.getChildren().add(new TreeItem(n));
    }



    private void buildRole() {
        tableViewTableInfo = new TableViewTableInfo();
        tableViewTableInfo.build(tableInfo);

        statisticWindow.setValueInProperties(tableViewTableInfo.getTableView());
    }



    public void showError(){
       ErrorWindow errorWindow = new ErrorWindow();
    }

}

