package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow;

import com.pl.spring.model.Pg_roles;
import com.pl.spring.model.RoleView;
import com.pl.spring.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableViewListItem {

    private TableView<RoleView> tableView;
    private TableColumn<RoleView, String> property , value;
    private Pg_roles pg_roles;

    private List<TableModel> tableModel;


    public void build(Pg_roles pg_roles){
        this.pg_roles = pg_roles;
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
                new RoleView("rolname",pg_roles.getRolname()),
                new RoleView("rolsuper",pg_roles.getRolsuper().toString()),
                new RoleView("rolinherit",pg_roles.getRolinherit().toString()),
                new RoleView("rolcreaterole",pg_roles.getRolcreaterole().toString()),
                new RoleView("rolcreatedb",pg_roles.getRolcreatedb().toString()),
                new RoleView("rolcanlogin",pg_roles.getRolcanlogin().toString()),
                new RoleView("rolreplication",pg_roles.getRolreplication().toString()),
                new RoleView("rolconnlimit",pg_roles.getRolconnlimit()),
                new RoleView("rolbypassrls",pg_roles.getRolbypassrls().toString()),
                new RoleView("oid",pg_roles.getOid()));


        tableView.getItems().removeAll();
        tableView.getColumns().removeAll();
        tableView.setItems(list);
        tableView.getColumns().addAll(property,value);
    }

    public TableView<RoleView> getTableView(){return this.tableView;}
}
