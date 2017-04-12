package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TableViewToStatisticWindow;

import com.pl.spring.model.RoleView;
import com.pl.spring.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TableViewTableModel {

    private TableView<RoleView> tableView;
    private TableColumn<RoleView, String> property , value;
    private List<TableModel> tableModel;


    public void build(List<TableModel> tableModel) {
        this.tableModel = tableModel;
        tableView = new TableView();
        tableView.setMaxSize(520, 250);

        property = new TableColumn("tablename");
        property.setMinWidth(250);
        property.setCellValueFactory(
                new PropertyValueFactory<>("property"));

        value = new TableColumn("tableowner");
        value.setMinWidth(250);
        value.setCellValueFactory(
                new PropertyValueFactory<>("value"));


        ObservableList<RoleView> list = FXCollections.observableArrayList();

        tableModel.forEach(e->{
            list.add(new RoleView(e.getTablename(),e.getTableowner()));
        });



        tableView.setItems(list);
        tableView.getColumns().addAll(property,value);

    }


    public TableView<RoleView> getTableView(){return this.tableView;}

}
