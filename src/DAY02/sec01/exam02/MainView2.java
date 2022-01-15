package DAY02.sec01.exam02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();

        Button btn = new Button("버튼");
        btn.setPrefSize(100,30);
        btn.setLayoutX(3);
        btn.setLayoutY(5);

        TextField textField = new TextField();
        textField.setPrefSize(200,150);
        textField.setLayoutX(3);
        textField.setLayoutY(50);

        root.getChildren().addAll(btn,textField);

        Scene scene = new Scene(root,300,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(MainView2.class);
    }


}
