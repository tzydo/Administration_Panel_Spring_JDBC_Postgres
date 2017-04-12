package com.pl.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Pg_roles {

    public String rolname;
    public Boolean rolsuper;
    public Boolean rolinherit;
    public Boolean rolcreaterole;
    public Boolean rolcreatedb;
    public Boolean rolcanlogin;
    public Boolean rolreplication;
    public int rolconnlimit;
    public String rolpassword;
    public String rolvaliduntil;
    public Boolean rolbypassrls;
    public String  rolconfig;
    public int oid;


    public String  getRolconnlimit(){
        return String.valueOf(rolconnlimit);
    }

    public String getOid(){
        return String.valueOf(oid);
    }
}
