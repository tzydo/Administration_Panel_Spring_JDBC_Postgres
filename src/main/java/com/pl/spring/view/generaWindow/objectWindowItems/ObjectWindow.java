package com.pl.spring.view.generaWindow.objectWindowItems;

import com.pl.spring.model.User;
import com.pl.spring.view.generaWindow.GeneralWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.DatabaseItem;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.groupItems.GroupItem;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableSpaceItems.TableSpaceItemLabel;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TreeItemInDatabase;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems.RoleItemLabel;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems.RoleLabel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("classpath:dataConfig.property")
public class ObjectWindow extends StackPane{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StatisticWindow statisticWindow;

    @Autowired
    User user;

    @Autowired
    Environment environment;

    private String sql;
    private int sceneX = 250;
    private int sceneY = 470;
    private TreeView tree;
    private TreeItem rootItem;
    private TreeItem serwery,addresDataBase,databaseInAdress, tableSpace,registeredRoleInAdress,registeredGroupInAdress;
    private List roleList,groupList,spaceList;


    public void build() {
        tree = new TreeView<>(); // glowny widok drzewa
        rootItem = new TreeItem<>(new Label("Grupa Serwerów"));


        rootItem.setValue(new Label("Grupa Serwerów"));

        serwery = new TreeItem<>(new Label("Servery"));
        addresDataBase = new TreeItem<>(new Label("PostgresSQL"));

        databaseInAdress = new TreeItem<>(new Label("Bazy Danych"));
        tableSpace = new TreeItem<>(new Label("Przestrzenie Tabel"));



        registeredRoleInAdress = new TreeItem<>(new RoleLabel("Zarejestrowane Role",jdbcTemplate,statisticWindow));

        registeredGroupInAdress = new TreeItem<>(new Label("Grupy Ról"));
        rootItem.setExpanded(true); // widok kolejnego elementu listy
        serwery.setExpanded(true);

        addDatabase(); // dodawanie listy baz danych
        addTableSpace();
        addRoleToTree();
        addGroupToTree();

        tree.setRoot(rootItem);
        addresDataBase.getChildren().addAll(databaseInAdress, tableSpace, registeredGroupInAdress,
                registeredRoleInAdress);
        serwery.getChildren().add(addresDataBase);
        rootItem.getChildren().add(serwery);
        getChildren().add(tree);
        setMargin(this, new Insets(20, 20, 20, 20));
        setMinSize(sceneX, sceneY);
    }


    private void addDatabase() {
        sql = "SELECT datname FROM pg_database WHERE datistemplate = false;";

        List<String>databaseList;
        databaseInAdress.getChildren().clear();
        try {
            databaseList = jdbcTemplate.queryForList(sql,String.class);
            databaseList.forEach(s -> {
                databaseInAdress.getChildren()
                        .add(new TreeItemInDatabase(s,jdbcTemplate, user, environment, new DatabaseItem(s.toString(), this,this),statisticWindow));
            });
        } catch (Exception ex) {
            System.out.println("blad w procesie ładowania schematu baz w drzewie ObjectWindow");

        }
    }


    private void addTableSpace() {
        sql = "SELECT spcname FROM pg_tablespace;";
        spaceList = jdbcTemplate.queryForList(sql,String.class);
        spaceList.forEach(o ->tableSpace.getChildren().add(new TreeItem<>
                (new TableSpaceItemLabel(o.toString(),jdbcTemplate,statisticWindow))));
    }


    private void addRoleToTree() {
        sql = "SELECT rolname FROM pg_roles where rolcanlogin = 't' ORDER BY rolname ASC;";
         roleList = jdbcTemplate.queryForList(sql,String.class);
         roleList.forEach(o -> {
             registeredRoleInAdress.getChildren().addAll(new TreeItem(new RoleItemLabel(o.toString(),jdbcTemplate,statisticWindow)));
         });


    }


    private void addGroupToTree() {
        sql = "SELECT * FROM information_schema.enabled_roles;";
        groupList = jdbcTemplate.queryForList(sql,String.class);
        groupList.forEach(o -> {
            if(!roleList.contains(o.toString())){
                registeredGroupInAdress.getChildren().addAll(new TreeItem<>(new GroupItem(o.toString(),jdbcTemplate,statisticWindow)));
            }
        });

    }
}