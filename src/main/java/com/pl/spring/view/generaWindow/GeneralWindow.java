package com.pl.spring.view.generaWindow;


import com.pl.spring.connection.Connect;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.SqlWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralWindow {

    @Autowired
    Connect connect;

    @Autowired
    StatisticWindow statisticWindow;

    @Autowired
    private SqlWindow sqlWindow;

    @Autowired
    public ObjectWindow objectWindow;

    private Scene scene;
    private Group root;
    private StackPane stack;


    private VBox vbox, vBoxInCenter;
    private HBox hbox, hboxInCenter;
    private Stage primaryStage;
    private Menu fileMenu, viewMenu, helpMenu;
    private MenuItem fileItemAddExit;
    private MenuItem viewItemWindowObject, viewItemWindowSql, viewItemToolsToolbar;

    private MenuItem helpItemAboutMe;
    private Button connectButton,createNewSerwerButton, refreashButon, editObjectButton, copyButton, deleteButton, sqlCommandButton;
    private MenuBar menuBar;


    public void build() {
        root = new Group();
        scene = new Scene(root, 800, 600);
        stack = new StackPane();

        vbox = new VBox(4);
        initializeMenuBar();
        initializeButtonMenu();
        hboxInCenter = new HBox(2);
        vBoxInCenter = new VBox(2);
        loadCenterPane();

        vbox.getChildren().addAll(menuBar, hbox, hboxInCenter);
        stack.getChildren().add(vbox);
        root.getChildren().add(stack);
        primaryStage.setResizable(false);
    }

    private void initializeMenuBar() {
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        fileMenu = new Menu("Plik");
        viewMenu = new Menu("Widok");
        helpMenu = new Menu("Pomoc");

        //<---------------------File Menu Button------------------------------->
        fileItemAddExit = new MenuItem("Wyjście");
        fileItemAddExit.setOnAction(e->{
            System.exit(0);
        });

        viewItemWindowObject = new MenuItem("Okno Obiektu");
        viewItemWindowSql = new MenuItem("Okno SQL");
        viewItemToolsToolbar = new MenuItem("Pasek Narzędzi");

        helpItemAboutMe = new MenuItem("O Programie");
        helpItemAboutMe.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About me");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!\n This program was made for test skills :D");
            alert.showAndWait();
        });

        fileMenu.getItems().addAll(fileItemAddExit);
        viewMenu.getItems().addAll(viewItemWindowObject, viewItemWindowSql, viewItemToolsToolbar);
        helpMenu.getItems().addAll(helpItemAboutMe);

        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);

    }

    private void initializeButtonMenu() {
        hbox = new HBox(6);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        connectButton = new Button("Połącz");
        createNewSerwerButton = new Button("Nowy Server");
        refreashButon = new Button("Odświerz");
        editObjectButton = new Button("Edycja Obiektu");
        copyButton = new Button("Kopiuj");
        deleteButton = new Button("Usuń");
        sqlCommandButton = new Button("Zapytanie SQL");

        hbox.getChildren().addAll(connectButton,createNewSerwerButton, refreashButon, editObjectButton,
    copyButton, deleteButton,sqlCommandButton);

    }

    private void loadCenterPane() {
        hboxInCenter.setStyle("-fx-padding: 10 10 10 15");
        vBoxInCenter.setStyle("-fx-padding: 0 10 0  10");

        vBoxInCenter.getChildren().add(statisticWindow);
        vBoxInCenter.getChildren().add(sqlWindow);

        objectWindow.build();
        hboxInCenter.getChildren().addAll(objectWindow,vBoxInCenter);
    }


    public Scene getScene() {
        return scene;
    }

}

