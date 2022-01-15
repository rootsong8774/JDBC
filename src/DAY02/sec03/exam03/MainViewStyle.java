package DAY02.sec03.exam03;

import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewStyle extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("RootLayout.fxml");
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(resource));
        Scene scene = new Scene(root);

        String urlStr = Objects.requireNonNull(getClass().getResource("Design.css")).toExternalForm();
        scene.getStylesheets().add(urlStr);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(MainViewStyle.class);
    }
}
