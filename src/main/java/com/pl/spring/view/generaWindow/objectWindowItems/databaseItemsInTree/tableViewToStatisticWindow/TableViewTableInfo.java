package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow;


import com.pl.spring.model.RoleView;
import com.pl.spring.model.TableInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableViewTableInfo {

    private TableView<RoleView> tableView;
    private TableColumn<RoleView, String> property , value;


    public void build(TableInfo tableInfo){

        tableView = new TableView();
        tableView.setMaxSize(520, 250);

        property = new TableColumn("property");
        property.setMinWidth(250);
        property.setCellValueFactory(
                new PropertyValueFactory<>("property"));

        value = new TableColumn("value");
        value.setMinWidth(250);
        value.setCellValueFactory(
                new PropertyValueFactory<>("value"));


        ObservableList<RoleView> list = FXCollections.observableArrayList(
                new RoleView("schemaname",tableInfo.getSchemaname()),
                new RoleView("tablename",tableInfo.getTablename()),
                new RoleView("tableowner",tableInfo.getTableowner()),
                new RoleView("tablespace",tableInfo.getTablespace()),
                new RoleView("hasindexes",tableInfo.getHasindexes().toString()),
                new RoleView("hasrules",tableInfo.getHasrules().toString()),
                new RoleView("hastriggers",tableInfo.getHastriggers().toString()),
                new RoleView("rowsecurity",tableInfo.getRowsecurity().toString()));


        tableView.getItems().removeAll();
        tableView.getColumns().removeAll();
        tableView.setItems(list);
        tableView.getColumns().addAll(property,value);
    }

    public TableView<RoleView> getTableView(){return this.tableView;}
}