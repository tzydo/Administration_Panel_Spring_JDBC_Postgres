package com.pl.spring.mapper;

import com.pl.spring.model.TableInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableInfoMapper implements RowMapper<TableInfo>{

    public TableInfo mapRow(ResultSet rs, int rowC) throws SQLException {
        TableInfo tableInfo = new TableInfo();

        tableInfo.setSchemaname(rs.getString("schemaname"));
        tableInfo.setTablename(rs.getString("tablename"));
        tableInfo.setTableowner(rs.getString("tableowner"));
        tableInfo.setTablespace(rs.getString("tablespace"));
        tableInfo.setHasindexes(rs.getBoolean("hasindexes"));
        tableInfo.setHasrules(rs.getBoolean("hasrules"));
        tableInfo.setHastriggers(rs.getBoolean("hastriggers"));
        tableInfo.setRowsecurity(rs.getBoolean("rowsecurity"));

        return  tableInfo;
    }

}
