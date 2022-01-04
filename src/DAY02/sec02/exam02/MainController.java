package DAY02.sec02.exam02;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn.setText("The Button");
        textField.setText("The TextField");
        textField.setOnKeyPressed(event -> System.out.println(event.getCode()));
    }

    @FXML
    public void click(ActionEvent event){
        textField.setText("Button Click Event");
    }


}
