package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.groupItems;


import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@NoArgsConstructor
public class GroupLabel extends Label{


    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private String name;
    private ObjectWindow objectWindow;
    private StackPane stackPane;
    private ContextMenu cm;
    private MenuItem refresh, add;

    public GroupLabel(String name,
                      JdbcTemplate jdbcTemplate,
                      StatisticWindow statisticWindow,
                      ObjectWindow objectWindow,
                      StackPane stackPane) {


        this.objectWindow = objectWindow;
        this.name = name;
        this.stackPane = stackPane;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.cm = new ContextMenu();
        refresh = new MenuItem("Odświerz");
        refresh.setOnAction(e -> {
            objectWindow.build();
        });
        add = new MenuItem("Dodaj Grupe");



        //!!!!!!!!!!!!!!!!!!!CREATE ROLE WINDOW!!!!!!!!!!!!!!!!!!!!
        add.setOnAction(e->{
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Create Group Role");
            dialog.setHeaderText("Create your new Group");

            ButtonType buttonName = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonName, ButtonType.CANCEL);


            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField groupName = new TextField();
            groupName.setPromptText("name");

            grid.add(new Label("Group name:"), 0, 0);
            grid.add(groupName, 1, 0);

            dialog.getDialogPane().setContent(grid);
            dialog.showAndWait();

            //http://code.makery.ch/blog/javafx-dialogs-official/   <-tutorial
            //!!!!!!!!!!!!!!!!!!!!NEW Group BE NULL OR EMPTY
            if(groupName.getText().isEmpty()
                    ||  groupName.getText().equals(null)
                    ||  groupName.getText().equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ groupName.getText());
                String sql= "create group "+groupName.getText()+";";
                jdbcTemplate.execute(sql);
                objectWindow.build();
            }
        });



        this.cm.getItems().addAll(refresh, add);
            this.setText(name);


        addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(stackPane, t.getScreenX(), t.getScreenY());
                } else {
                    cm.hide();
                }

            }

        });
}

}
