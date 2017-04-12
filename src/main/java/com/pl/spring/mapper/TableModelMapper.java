package com.pl.spring.mapper;

import com.pl.spring.model.TableModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableModelMapper implements RowMapper<TableModel>{
    public TableModel mapRow(ResultSet rs, int rowNum)throws SQLException {

        TableModel tableModel = new TableModel();
        tableModel.setTablename(rs.getString("tablename"));
        tableModel.setTableowner(rs.getString("tableowner"));

        return tableModel;
    }
}
