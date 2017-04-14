package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.groupItems;

import com.pl.spring.mapper.Pg_rolesMapper;
import com.pl.spring.model.Pg_roles;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.ErrorWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow.TableViewListItem;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupItem extends Label{

    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private ObjectWindow objectWindow;
    private StackPane stackPane;
    private Pg_roles pg_roles;
    private TableViewListItem tableViewListItem;
    private ContextMenu cm;
    private MenuItem delete;


    public GroupItem(String name,
                     JdbcTemplate jdbcTemplate,
                     StatisticWindow statisticWindow,
                     StackPane stackPane,
                     ObjectWindow ObjectWindow){
        super(name);
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.objectWindow = ObjectWindow;
        this.stackPane = stackPane;

        this.setOnMouseClicked(e->{
            String sql = "SELECT * FROM pg_roles where rolname = ?";
            pg_roles = jdbcTemplate.queryForObject(sql,new Pg_rolesMapper(),name);
            buildRole();
        });


        delete = new MenuItem("usuń");
        this.cm = new ContextMenu();
        this.cm.getItems().addAll(delete);


        //USUNIECIE ZAREJESTROWANEJ ROLI
        addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(stackPane, t.getScreenX(), t.getScreenY());
                    cm.setOnAction(e->{
                        String sql = "DROP GROUP IF EXISTS "+name + ";";
                        try{
                            jdbcTemplate.execute(sql);
                            objectWindow.build();
                        }catch (Exception ex){
                            ErrorWindow errorWindow = new ErrorWindow();
                        }


                    });
                } else {
                    cm.hide();
                }

            }

        });



    }

    private void buildRole() {
        tableViewListItem = new TableViewListItem();
        tableViewListItem.build(pg_roles);

        statisticWindow.setValueInProperties(tableViewListItem.getTableView());
    }
}
