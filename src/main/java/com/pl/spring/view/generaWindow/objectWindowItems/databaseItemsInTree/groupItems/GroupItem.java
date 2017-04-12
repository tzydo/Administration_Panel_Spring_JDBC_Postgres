package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.groupItems;

import com.pl.spring.mapper.Pg_rolesMapper;
import com.pl.spring.model.Pg_roles;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.TableViewToStatisticWindow.TableViewListItem;
import javafx.scene.control.Label;
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
    private Pg_roles pg_roles;
    private TableViewListItem tableViewListItem;

    public GroupItem(String name, JdbcTemplate jdbcTemplate, StatisticWindow statisticWindow){
        super(name);
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;


        this.setOnMouseClicked(e->{
            String sql = "SELECT * FROM pg_roles where rolname = ?";
            pg_roles = jdbcTemplate.queryForObject(sql,new Pg_rolesMapper(),name);
            buildRole();
        });
    }

    private void buildRole() {
        tableViewListItem = new TableViewListItem();
        tableViewListItem.build(pg_roles);

            statisticWindow.setValueInProperties(tableViewListItem.getTableView());


    }

//        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent t) {
//
//
//
//                if (t.getButton() == MouseButton.SECONDARY) {
//                    System.out.println("click");
//
////     cm.show(root, t.getScreenX(), t.getScreenY());
////                } else {
////                    cm.hide();
//                }
//
//            }
//
//        });
    }
