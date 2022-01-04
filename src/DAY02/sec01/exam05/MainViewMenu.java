package DAY02.sec01.exam05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainViewMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");

        BorderPane pane = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Menu 1");
        Menu menu2 = new Menu("Menu 2");
        Menu menu3 = new Menu("Menu 3");

        menuBar.getMenus().addAll(menu1, menu2, menu3);

        MenuItem menuItem11 = new MenuItem("SubMenu11");
        MenuItem menuItem12 = new MenuItem("SubMenu12");
        MenuItem menuItem13 = new MenuItem("SubMenu13");

        Menu menu4 = new Menu("Menu 4");
        MenuItem menuItem21 =  new MenuItem("Submenu 21");
        SeparatorMenuItem separate = new SeparatorMenuItem();
        MenuItem menuItem22 =  new MenuItem("Submenu 22");
        MenuItem menuItem23 =  new MenuItem("Submenu 23");

        menu1.getItems().addAll(menuItem11, menuItem12, menuItem13);
        menu4.getItems().addAll(menuItem21, menuItem22, separate,menuItem23);
        menu2.getItems().add(menu4);

        TextField textField = new TextField();
        textField.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        textField.setLayoutX(50);
//        textField.setLayoutY(50);

        menu1.setOnAction(event -> textField.setText("Menu1 Click event logic region"));
        menuItem11.setOnAction(event -> textField.setText("SubMenu11 Click event logic region"));

        pane.setTop(menuBar);
        pane.setCenter(textField);

        Scene scene = new Scene(pane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
