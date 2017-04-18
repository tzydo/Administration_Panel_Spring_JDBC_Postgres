package com.pl.spring.view.generaWindow.objectWindowItems;


import com.pl.spring.model.RoleView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class StatisticWindow extends StackPane {


    private String nameRole;
    private Tab property;
    private TabPane tabPane;
    private TableView<RoleView> tableView;
    private VBox vBox;


    public StatisticWindow(){
        build();
    }

    public void build() {
        this.setMinSize(520, 250);
        this.setStyle("-fx-background-color: #92e2ff;");

        tabPane = new TabPane();
        property = new Tab("Właściwości");
        property.setClosable(false);
        tabPane.getTabs().addAll(property);
        this.getChildren().add(tabPane);
        this.setVisible(true);
    }


    public void insertDifferent(TextArea textArea){
        this.property.setContent(textArea);
        this.setVisible(true);
    }


    public  void setValueInProperties(TableView tableView){

        this.tableView = tableView;
        this.tableView.setEditable(true);
        this.tableView.setVisible(true);
        vBox = new VBox(1);
        vBox.getChildren().add(tableView);
        property.setContent(vBox);
        tabPane.getTabs().add(property);

    }
}
