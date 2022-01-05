package Day03.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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

    ProductService productService = new ProductService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        initEvent();
    }


    public void init() {
        productNoColumn.setCellValueFactory(new PropertyValueFactory<>("productNo"));
        productNameColumn.setCellValueFactory(
            new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        BorderPane root = (BorderPane) productTable.getParent();
        productTable.prefWidthProperty().bind(root.widthProperty());
        productNoColumn.prefWidthProperty().bind(productTable.widthProperty().multiply(.2));
        productNameColumn.prefWidthProperty().bind(productTable.widthProperty().multiply(.5));
        priceColumn.prefWidthProperty().bind(productTable.widthProperty().multiply(.3));

        String query = "select * from product where 1=1";
        List<ParameterMap> selectList = DBClient.selectList(query);
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("resultList", selectList);
        List<ParameterMap> list = parameterMap.getList("resultList");
        List<Product> productList = new ArrayList<>();
        int index = 0;
        if (list != null) {
            for (ParameterMap p : list) {
                index += 1;
                if (index == 1) {
                    continue;
                }
                Product value = new Product();
                value.setProductNo(p.getString("PRODUCT_NO"));
                value.setProductName(p.getString("PRODUCT_NAME"));
                value.setPrice(p.getInteger("PRICE"));
                productList.add(value);
            }
        }
        productTable.getItems().setAll(productList);

    }

    public void setBtnSearch() {
        ParameterMap parameterMap = new ParameterMap();
        System.out.println(this.productNameSearch);
        System.out.println(this.priceSearchStart);
        System.out.println(this.priceSearchEnd);
        parameterMap.put("productNameSearch", productNameSearch.getText());
        parameterMap.put("priceSearchStart", priceSearchStart.getText());
        parameterMap.put("priceSearchEnd", priceSearchEnd.getText());

        productService.selectProductList(parameterMap);

        List<ParameterMap> list = parameterMap.getList("resultList");
        List<Product> productList = new ArrayList<>();
        initDetail();
        int index = 0;
        if (list != null) {
            for (ParameterMap p : list) {
                index += 1;
                if (index == 1) {
                    continue;
                }
                Product value = new Product();
                value.setProductNo(p.getString("PRODUCT_NO"));
                value.setProductName(p.getString("PRODUCT_NAME"));
                value.setPrice(p.getInteger("PRICE"));
                productList.add(value);
            }
        }
        productTable.getItems().setAll(productList);
    }

    public void initDetail() {
        this.productNoDetail.setText("");
        this.productNameDetail.setText("");
        this.priceDetail.setText("");

    }

    public void initEvent() {

        productTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                initDetail();
                if (newValue != null) {
                    productNoDetail.setText(newValue.getProductNo());
                    productNameDetail.setText(newValue.getProductName());
                    priceDetail.setText("" + newValue.getPrice());
                }
            });
        btnSearch.setOnAction(event -> setBtnSearch());

        btnNew.setOnAction(event -> {
            productTable.getSelectionModel().clearSelection();
            initDetail();
        });

        btnSave.setOnAction(event -> {
            String productName = productNameDetail.getText();
            String price = priceDetail.getText();
            if (productName.trim().equals("") || price.trim().equals("")) {
                String msg = "품목명 또는 단가는 필수 입력 항목입니다.";
                new Alert(AlertType.ERROR, msg).show();
                return;
            }
            ParameterMap parameterMap = new ParameterMap();

            parameterMap.put("productNo", productNoDetail.getText());
            parameterMap.put("productName",productNameDetail.getText());
            parameterMap.put("price",priceDetail.getText());

            productService.saveProduct(parameterMap);
            String productNo = parameterMap.getString("productNo");
            setBtnSearch();
            ObservableList<Product> items = productTable.getItems();
            for(Product v: items){
                if(v.getProductNo().equals(productNo)){
                    productTable.getSelectionModel().select(v);
                    productTable.scrollTo(v);
                    break;
                }
            }

        });

    }


}
