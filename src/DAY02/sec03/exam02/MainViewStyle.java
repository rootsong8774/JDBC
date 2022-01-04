package DAY02.sec03.exam02;

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
        textField.setText("CSS Settings: Font-size: 14pt, Font-Color: Red");
        borderPane.setBottom(textField);

        Scene scene = new Scene(borderPane, 400, 100);
        String urlStr = getClass().getResource("Design.css").toExternalForm();
        scene.getStylesheets().add(urlStr);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
