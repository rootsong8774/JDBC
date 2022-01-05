package Day03.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductController implements Initializable {

    @FXML
    public TextField productNameSearch;
    @FXML
    public TextField priceSearchStart;
    @FXML
    public TextField priceSearchEnd;
    @FXML
    public Button btnSearch;
    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, String> productNoColumn;
    @FXML
    public TableColumn<Product, String> productNameColumn;
    @FXML
    public TableColumn<Product, Integer> priceColumn;
    @FXML
    public TextField productNoDetail;
    @FXML
    public TextField productNameDetail;
    @FXML
    public TextField priceDetail;
    @FXML
    public Button btnNew;
    @FXML
    public Button btnSave;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productNoColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productNo"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        String query = "select * from product where 1=1";
        List<ParameterMap> selectList = DBClient.selectList(query);
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("resultList", selectList);
        List<ParameterMap> list = parameterMap.getList("resultList");
        List<Product> productList = new ArrayList<>();
        int index = 0;
        if(list != null) {
            for(ParameterMap p:list){
                index+=1;
                if (index==1) continue;
                Product value = new Product();
                value.setProductNo(p.getString("PRODUCT_NO"));
                value.setProductName(p.getString("PRODUCT_NAME"));
                value.setPrice(p.getInteger("PRICE"));
                productList.add(value);
            }
        }
        productTable.getItems().setAll(productList);

    }
}
