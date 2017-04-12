package com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Set;
import java.util.TreeSet;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleLabel extends Label {

    private JdbcTemplate jdbcTemplate;
    private StatisticWindow statisticWindow;
    private String name;
    private TableView tableView;
    private TableColumn roleName;
    private TableColumn owner;
    private TableColumn note;
    private Set<String> list = new TreeSet<>();
    private ObjectWindow objectWindow;
    private StackPane stackPane;

    private ContextMenu cm;
    private MenuItem refresh, add;


    public RoleLabel(String name, JdbcTemplate jdbcTemplate, StatisticWindow statisticWindow, ObjectWindow objectWindow, StackPane stackPane) {
        this.objectWindow = objectWindow;
        this.name = name;
        this.stackPane =stackPane;
        this.jdbcTemplate = jdbcTemplate;
        this.statisticWindow = statisticWindow;
        this.cm = new ContextMenu();
        refresh = new MenuItem("Odświerz");
        refresh.setOnAction(e->{objectWindow.build();});
        add = new MenuItem("Dodaj Role");



                //!!!!!!!!!!!!!!!!!!!CREATE ROLE WINDOW!!!!!!!!!!!!!!!!!!!!
        add.setOnAction(e->{
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Create Role");
            dialog.setHeaderText("Create yout new Role");

            ButtonType nameRole = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(nameRole, ButtonType.CANCEL);


            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField username = new TextField();
            username.setPromptText("name");
            PasswordField password = new PasswordField();
            password.setPromptText("Password");

            grid.add(new Label("Username:"), 0, 0);
            grid.add(username, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(password, 1, 1);


            dialog.getDialogPane().setContent(grid);
            dialog.showAndWait();

            //http://code.makery.ch/blog/javafx-dialogs-official/   <-tutorial
            //!!!!!!!!!!!!!!!!!!!!NEW ROLE CAN'T BE NULL OR EMPTY
            if(username.getText().isEmpty()
                    ||  username.getText().equals(null)
                    ||  username.getText().equals("")
                    ||  password.getText().isEmpty()
                    ||  password.getText().equals(null)
                    ||  password.getText().equals("")){
                System.out.println("pusty");
            }else{
                System.out.println("name :"+ username.getText() + "  , pass: " + password.getText());
                String sql= "CREATE USER "+username.getText()+" WITH PASSWORD '"+password.getText()+"';";
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

