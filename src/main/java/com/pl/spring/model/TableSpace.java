package com.pl.spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class TableSpace {

    private int oid;
    private String spcname;
    private int spcowner;
    private String spcacl;
    private String spcoptions;

    public String getSpcOwner(){ return String.valueOf(this.spcowner); }
    public String getOid(){ return String.valueOf(this.oid); }


}
