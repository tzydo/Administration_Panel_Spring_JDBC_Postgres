package com.pl.spring.model;
import javafx.beans.property.SimpleStringProperty;

public class RoleView{


//WYMAGANE RECZNE GETTERY I SETERY BO BEZ TEGO NIE WIDZI DANYCH W TABELI
    private SimpleStringProperty property;
    private SimpleStringProperty value;

    public RoleView(String property, String value){
        this.property = new SimpleStringProperty(property);
        this.value = new SimpleStringProperty(value);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getProperty() {
        return property.get();
    }

    public SimpleStringProperty propertyProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property.set(property);
    }



}
