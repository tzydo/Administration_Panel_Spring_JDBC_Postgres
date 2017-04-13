package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableSpaceItems;

import com.pl.spring.mapper.TableSpaceMapper;
import com.pl.spring.model.TableSpace;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.tableViewToStatisticWindow.TableViewSpaceTableItemList;
import javafx.scene.control.Label;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@NoArgsConstructor
public class TableSpaceItemLabel extends Label {

    private String name;
    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private TableSpace tableSpace;
    private TableViewSpaceTableItemList tableViewSpaceTableItemList;

    public TableSpaceItemLabel(String name, JdbcTemplate jdbcTemplate, StatisticWindow statisticWindow){
        this.name = name;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.setText(name);

        this.setOnMouseClicked(e->{
            String sql = "select oid,* from pg_tablespace where spcname = ?";
            tableSpace = jdbcTemplate.queryForObject(sql,new TableSpaceMapper(),this.name);
            buildRole();
        });
    }

    private void buildRole() {
        tableViewSpaceTableItemList = new TableViewSpaceTableItemList();
        tableViewSpaceTableItemList.build(tableSpace);

        statisticWindow.setValueInProperties(tableViewSpaceTableItemList.getTableView());
    }

}

