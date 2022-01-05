package Day03.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppPreLaunch extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("품목관리");
        primaryStage.show();

    }

}
