package JDBCProject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*Run-Edit Configuration-VM Options 로 다음을 추가:--module-path $PROJECT_DIR$/javafx-sdk-11.0.1/lib/ --add-modules=javafx.controls,javafx.fxml */
public class MainApp extends Application {

    public static void main(String[] args) {
//		List<String[]> executeQuery = DatabaseUtil.executeQuery("select * from product", null);
//		for(String[] arr: executeQuery)
//			System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(executeQuery.get(1)));
        List<ParamMap> executeQuery = DatabaseClient.selectList("select * from product", null);
        assert executeQuery != null;
        for (ParamMap p : executeQuery) {
            System.out.println(p);
        }
        System.out.println("=======================");
        String query = "delete from product where product_no in ('B001', 'B002', 'B003')";
        DatabaseClient.update(query);
        executeQuery = DatabaseClient.selectList("select * from product", null);
        assert executeQuery != null;
        for (ParamMap p : executeQuery) {
            System.out.println(p);
        }
        String[][] data = {{"B001", "오렌지쥬스", "7000"}, {"B002", "키위쥬스", "7000"},
            {"B003", "자몽쥬스", "7000"}};
        String query2 = "insert into product values(?,?,?)";
        for (String[] arr : data) {
            List<String> list2 = Arrays.asList(arr);
            // System.out.println(list2.get(0) +":"+list2.get(0).getBytes().length);

            DatabaseClient.insert(query2, list2);
        }
        System.out.println("=======================");
        executeQuery = DatabaseClient.selectList("select * from product", null);
        assert executeQuery != null;
        for (ParamMap p : executeQuery) {
            System.out.println(p);
        }

        Application.launch(MainApp.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("MainLayout.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("품목관리");
        stage.show();
    }

}
