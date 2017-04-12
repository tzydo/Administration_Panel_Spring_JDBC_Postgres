package com.pl.spring.SQL;

import com.pl.spring.connection.Connect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SqlQuerry {

    @Autowired
    Connect connect;



    private Statement statement;
    private ResultSet resulSet = null;
    private ResultSetMetaData megaData;
    private int columnNumber;


    public void deleteItemFromTreeDatabase(String nameDatabase) {
        useQuery(dropDatabase(nameDatabase));
    }

    private String dropDatabase(String name) {
        return ("DROP DATABASE " + name + ";");
    }

    public String reConnectQuerry(String name) {
        String querry = "psql -d t23 -U  postgres -W;";
        //querry += name;

        return querry;
    }

    public void useQuery(String query) {
        try {
            this.statement.executeQuery(query);
            System.out.println("querry done");
        } catch (NullPointerException | SQLException e) {
            System.out.println("error in query useQuery");
        }
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!POPRAWIC I
    // REFAKTOR!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public String getVersionServer() {
        String version = "SHOW server_version;";

        return getOneLineQuery(version);
    }

    public String getOneLineQuery(String query) {

        String result = "";
        try {
            this.resulSet = this.statement.executeQuery(query);
        } catch (NullPointerException | SQLException e) {
            System.out.println("error in getOnlineQuery!");
        }
        try {
            while (this.resulSet.next()) {

                result = this.resulSet.getString(1);
                System.out.println(result);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(result.equals(null)|| result.equals(""))
            result = "";

        return result;
    }

    public String getSchemaItem() {
        return "SELECT nspname FROM pg_catalog.pg_namespace WHERE nspname != 'pg_toast' AND nspname != 'pg_temp_1' AND nspname!= 'pg_toast_temp_1' AND nspname != 'pg_catalog' AND nspname != 'information_schema';";
    }


    public ArrayList<String> addItemToTee(String querry) {
        ArrayList<String> list = new ArrayList<>();

        try {
            resulSet = this.statement.executeQuery(querry);
            while (resulSet.next()) {
                System.out.println(resulSet.getString(1));

                list.add(resulSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("blad metody addItemToTree w SqlQuerry !");
        }

        return list;
    }
    public void setStatment(Statement statment) {
        this.statement = statment;
    }


    //wczytywanie querry wieloliniowego

    public ResultSet initialize(String querry) {
        setQuerry(querry); // ustawianie zapytania do bazy
        checkColumn(); // sprawdzanie czy sa kolumny
        setColumnNumber(); // wczytanie ilosci kolumn
        return resulSet;
    }



    private void setQuerry(String querry) {
        try {
            resulSet = statement.executeQuery(querry);
        } catch (SQLException e) {
            System.out.println("blad zapytania");
        }
    }

    private void checkColumn() {
        try {
            this.megaData = resulSet.getMetaData();
        } catch (SQLException e) {
            System.out.println("blad  czytania ilosci kolumn");
        }
    }

    private void setColumnNumber() {
        try {
            columnNumber = megaData.getColumnCount();
        } catch (Exception e) {
            System.out.println("blad ilosci kolumn");
        }
    }




    //<--------------------------------CHANGE PASSWORD-----------------------------------

    public void changePassword(String user, String password){
        String query = "ALTER USER "+ user+" with password '"+ password+"';";
        getOneLineQuery(query);
    }
}
