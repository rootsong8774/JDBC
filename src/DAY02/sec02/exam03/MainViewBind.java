package DAY02.sec02.exam03;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainViewBind extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(20);

        TextField textField = new TextField();
        textField.prefWidth(200);
        flowPane.getChildren().add(textField);

        Label label = new Label("Change the text");
        label.setPrefWidth(200);
        flowPane.getChildren().add(label);

        label.textProperty().bind(textField.textProperty());


        Scene scene = new Scene(flowPane, 450, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(MainViewBind.class);
    }
}
