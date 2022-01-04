package DAY02.sec01.exam03;

import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainViewEvent extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("메인화면");
        Pane root = new Pane();

        Button btn = new Button("버튼");
        btn.setPrefSize(100, 30);
        btn.setLayoutX(10);
        btn.setLayoutY(10);

        TextField textField = new TextField();
        textField.setPrefSize(200, 30);
        textField.setLayoutX(10);
        textField.setLayoutY(60);

        root.getChildren().addAll(btn, textField);

        btn.setOnAction(event -> {
            Date time = new Date();
            textField.setText(time.toString());
        });

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
