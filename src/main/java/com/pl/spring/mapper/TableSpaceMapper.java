package com.pl.spring.mapper;

import com.pl.spring.model.TableSpace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableSpaceMapper implements RowMapper<TableSpace>{

    public TableSpace mapRow(ResultSet rs, int rowNum)throws SQLException{
        TableSpace tableSpace = new TableSpace();
        tableSpace.setOid(rs.getInt("oid"));
        tableSpace.setSpcname(rs.getString("spcname"));
        tableSpace.setSpcowner(rs.getInt("spcowner"));
        tableSpace.setSpcacl(rs.getString("spcacl"));
        tableSpace.setSpcoptions(rs.getString("spcoptions"));
        return tableSpace;
    }

}
