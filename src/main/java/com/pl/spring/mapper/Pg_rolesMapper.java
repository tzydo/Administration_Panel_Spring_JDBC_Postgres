package com.pl.spring.mapper;

import com.pl.spring.model.Pg_roles;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pg_rolesMapper implements RowMapper<Pg_roles>{

    public Pg_roles mapRow(ResultSet rs, int rowNum) throws SQLException{
        Pg_roles pg_roles = new Pg_roles();
        pg_roles.setRolname(rs.getString("rolname"));
        pg_roles.setRolsuper(rs.getBoolean("rolsuper"));
        pg_roles.setRolinherit(rs.getBoolean("rolinherit"));
        pg_roles.setRolcreaterole(rs.getBoolean("rolcreaterole"));
        pg_roles.setRolcreatedb(rs.getBoolean("rolcreatedb"));
        pg_roles.setRolcanlogin(rs.getBoolean("rolcanlogin"));
        pg_roles.setRolreplication(rs.getBoolean("rolreplication"));
        pg_roles.setRolconnlimit(rs.getInt("rolconnlimit"));
        pg_roles.setRolpassword(rs.getString("rolpassword"));
        pg_roles.setRolvaliduntil(rs.getString("rolvaliduntil"));
        pg_roles.setRolbypassrls(rs.getBoolean("rolbypassrls"));
        pg_roles.setRolconfig(rs.getString("rolconfig"));
        pg_roles.setOid(rs.getInt("oid"));

        return pg_roles;
    };
}
