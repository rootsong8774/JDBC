package DAY02.sec01.exam06;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainViewDialog extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dialog");

        TextField textField = new TextField();
        textField.setPrefSize(300, 200);

        Button btn1 = new Button("Alert Dialog");
        btn1.setPrefSize(100, 30);
        btn1.setOnAction(event -> {
            Alert alert = new Alert(AlertType.INFORMATION, "Alert Information");
            alert.setHeaderText("Dialog Header");
            alert.show();
        });

        Button btn2 = new Button("Choice Dialog");
        btn2.setPrefSize(100, 30);
        btn2.setOnAction(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Yes or No");
            alert.setHeaderText("Dialog Header");
            ButtonType btntype1 = new ButtonType("Yes");
            ButtonType btntype2 = new ButtonType("No");
            ButtonType btntype3 = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btntype1, btntype2, btntype3);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btntype1) {
                textField.setText("Choose Yes");
            } else if (result.get() == btntype2) {
                textField.setText("Choose No");
            } else if (result.get() == btntype3) {
                textField.setText("Choose Cancel");
            }

        });

        Button btn3 = new Button("Input Dialog");
        btn3.setPrefSize(100, 30);
        btn3.setOnAction(event -> {
            TextInputDialog alert = new TextInputDialog("Input the values.");
            alert.setHeaderText("Input Dialog Header");
            alert.setContentText("Input Value of alert contents");
            alert.getDialogPane().setPrefSize(450, 200);
            Optional<String> result = alert.showAndWait();
            result.ifPresent(textField::setText);
        });

        FlowPane root = new FlowPane(Orientation.VERTICAL, btn1, btn2, btn3, textField);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
