package com.pl.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {

    private String schemaname;
    private String tablename;
    private String tableowner;
    private String tablespace;
    private Boolean hasindexes;
    private Boolean hasrules;
    private Boolean hastriggers;
    private Boolean rowsecurity;
}
