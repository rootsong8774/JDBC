package Day03.main;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp {


    public static void main(String[] args) {
       /* List<ParameterMap> executeQuery = DBClient.selectList("Select * from product", null);
        for(ParameterMap p: executeQuery){
            System.out.println(p);

        }
        System.out.println("===============================");
        String query = "delete from product where PRODUCT_NO in ('B001', 'B002','B003')";
        DBClient.update(query);
        executeQuery = DBClient.selectList("Select * from product", null);
        for(ParameterMap p: executeQuery){
            System.out.println(p);

        }
        String[][] data = {{"a", "a", "7000"},{"B002", "Apple Juice", "7000"}};
        String query2 = "insert into product values(?,?,?)";

        for(String[] arr:data){
            List<String> list2 = Arrays.asList(arr);
            System.out.println(list2);
            DBClient.insert(query2,list2);
        }
        executeQuery = DBClient.selectList("Select * from product", null);
        for(ParameterMap p: executeQuery){
            System.out.println(p);

        }*/
        Application.launch(MainAppPreLaunch.class);

    }



}
