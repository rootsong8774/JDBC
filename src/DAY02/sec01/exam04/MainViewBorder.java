package DAY02.sec01.exam04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainViewBorder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        BorderPane root = new BorderPane();

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        Button btn3 = new Button("Button 3");
        Button btn4 = new Button("Button 4");
        Button btn5 = new Button("Button 5");

        btn1.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btn2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btn3.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btn4.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btn5.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        root.setTop(btn1);
        root.setLeft(btn2);
        root.setRight(btn3);
        root.setBottom(btn4);
        root.setCenter(btn5);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
        Application.launch(MainViewBorder.class);
    }
}
