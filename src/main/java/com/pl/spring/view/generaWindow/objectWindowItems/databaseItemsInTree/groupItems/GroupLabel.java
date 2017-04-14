package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.groupItems;


import com.pl.spring.createWindows.CreateGroupRoleWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
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
            CreateGroupRoleWindow createRoleWindow = new CreateGroupRoleWindow();
            createRoleWindow.getDialog().showAndWait();
            String groupName = createRoleWindow.getGroupName().getText().toString();
            //http://code.makery.ch/blog/javafx-dialogs-official/   <-tutorial
            //!!!!!!!!!!!!!!!!!!!!NEW Group BE NULL OR EMPTY
            if(groupName.isEmpty()
                    ||  groupName.equals(null)
                    ||  groupName.equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ groupName);
                String sql= "create group "+groupName+";";
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
