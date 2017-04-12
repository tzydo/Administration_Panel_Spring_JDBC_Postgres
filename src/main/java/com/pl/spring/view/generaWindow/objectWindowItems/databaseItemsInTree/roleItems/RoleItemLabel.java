package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems;


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
public class RoleItemLabel extends Label {

    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private String name;
    private Pg_roles pg_roles;
    private TableViewListItem tableViewListItem;


    public RoleItemLabel(String name, JdbcTemplate jdbcTemplate, StatisticWindow statisticWindow) {
        this.name = name;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.setText(name);
        this.setOnMouseClicked(e->{
            String sql = "SELECT * FROM pg_roles where rolname = ?";
            pg_roles = jdbcTemplate.queryForObject(sql,new Pg_rolesMapper(),this.name);
            buildRole();
        });
    }

    private void buildRole() {
        tableViewListItem = new TableViewListItem();
        tableViewListItem.build(pg_roles);

        statisticWindow.setValueInProperties(tableViewListItem.getTableView());

    }

}

