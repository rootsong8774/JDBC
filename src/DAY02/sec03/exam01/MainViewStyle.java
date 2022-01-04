package DAY02.sec03.exam01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainViewStyle extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        TextField textField = new TextField();
        borderPane.setBottom(textField);
        textField.setText("CSS settings: Font size 14pt, Font Color: Red");

        textField.setStyle("-fx-font-size: 14pt; -fx-text-fill: red;");
        borderPane.setStyle("-fx-background-color: Pink");
        primaryStage.setScene(new Scene(borderPane, 400, 100));
        primaryStage.show();
    }
}
