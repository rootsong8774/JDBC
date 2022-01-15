package DAY02.sec02.exam02;

import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("RootLayout.fxml");
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(resource));
        Scene scene = new Scene(root);

        Button btn = (Button) scene.lookup("#btn");
        System.out.println("btn : " + btn);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(MainView.class);
    }
}
