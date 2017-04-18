package com.pl.spring.view.generaWindow.objectWindowItems;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
@Data
public class SqlWindow extends StackPane {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    StatisticWindow statisticWindow;

    private VBox vbox;
    private Button button;
    private TextArea textArea;

    public SqlWindow() {
        setMinSize(520, 220);
        vbox = new VBox();
        button = new Button("Wykonaj");

        button.setOnAction(e-> {
                    TextArea sendTextArea = new TextArea();
                    sendTextArea.setWrapText(true);
                    sendTextArea.setEditable(false);


                    String sql = textArea.getText().toString();
                    try{
                        SqlRowSet s = jdbcTemplate.queryForRowSet(sql);

                        int rowCount =1, error = 1;
                        StringBuffer stringBuffer = new StringBuffer();

                        while (s.next()) {
                            while(error != 0){
                                try {
                                    stringBuffer.append(s.getObject(rowCount).toString() + " | ");
                                    rowCount++;
                                }catch (Exception ex){
                                    error = 0;
                                }
                            }

                            stringBuffer.append("\n");
                            rowCount = 1;
                            error=1;
                        }

                        sendTextArea.setText(stringBuffer.toString());
                        statisticWindow.insertDifferent(sendTextArea);
                    }catch (Exception ex){
                        sendTextArea.setText("Błędne zapytanie SQL!");
                        statisticWindow.insertDifferent(sendTextArea);
                    }

                });

        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);

        vbox.getChildren().add(new Label("Enter querry:"));

        textArea = new TextArea();
        textArea.setWrapText(true);
        vbox.getChildren().addAll(textArea,button);
    }

}