package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TableViewToStatisticWindow;

import com.pl.spring.model.Pg_roles;
import com.pl.spring.model.RoleView;
import com.pl.spring.model.TableSpace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewSpaceTableItemList {
    private TableView<RoleView> tableView;
    private TableColumn<RoleView, String> property , value;
    private TableSpace tableSpace;


    public void build(TableSpace tableSpace){
        this.tableSpace = tableSpace;
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
                new RoleView("spcname",tableSpace.getSpcname()),
                new RoleView("spcowner",tableSpace.getSpcOwner()),
                new RoleView("oid",tableSpace.getOid()),
                new RoleView("spcacl",tableSpace.getSpcacl()),
                new RoleView("spcoptions",tableSpace.getSpcoptions()));

        tableView.setItems(list);
        tableView.getColumns().addAll(property,value);
    }


    public TableView<RoleView> getTableView(){return this.tableView;}
}
