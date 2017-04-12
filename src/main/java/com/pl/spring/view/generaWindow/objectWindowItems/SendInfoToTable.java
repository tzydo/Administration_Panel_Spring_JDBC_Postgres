package com.pl.spring.view.generaWindow.objectWindowItems;

public class SendInfoToTable {

    private String value;
    private String property;
    private String note;
    private String roleData;

    public SendInfoToTable(String value, String property, String note) {
        this.value = value;
        this.property = property;
        this.note = note;
    }

    public SendInfoToTable(String value, String property, String note, String roleData) {
        this.value = value;
        this.property = property;
        this.note = note;
        this.roleData = roleData;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRoleData() {
        return roleData;
    }

    public void setRoleData(String roleData) {
        this.roleData = roleData;
    }

}
