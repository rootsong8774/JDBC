package DAY02.sec04.exam01;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        TableView<Product> tableView = new TableView<>();
        root.setCenter(tableView);
        TableColumn<Product, String> tc1 = new TableColumn<>("ProductNo");
        TableColumn<Product, String> tc2 = new TableColumn<>("ProductName");
        TableColumn<Product, Integer> tc3 = new TableColumn<>("Price");

        tc1.setCellValueFactory(new PropertyValueFactory<>("productNo"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("price"));

        tc1.setStyle("-fx-alignment:CENTER;");
        tc2.setStyle("-fx-alignment:CENTER-LEFT;");
        tc3.setStyle("-fx-alignment:CENTER;");

        tc1.prefWidthProperty().bind(tableView.widthProperty().divide(100).multiply(20));
        tc2.prefWidthProperty().bind(tableView.widthProperty().divide(100).multiply(50));
        tc3.prefWidthProperty().bind(tableView.widthProperty().divide(100).multiply(30));

        tableView.getColumns().setAll(tc1, tc2, tc3);

        Product v1 = new Product("A001", "Americano", 4000);
        Product v2 = new Product("A002", "Cafe Mocha", 5000);
        Product v3 = new Product("A003", "Cappuccino", 6000);
        Product v4 = new Product("A004", "Cafe Latte", 5000);

        ObservableList<Product> observableList = FXCollections.observableArrayList(v1, v2, v3, v4);
        tableView.setItems(observableList);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
