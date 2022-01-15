package JDBCProject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProductController implements Initializable {

	@FXML public TextField productNameSearch;
	@FXML public TextField priceSearchStart;
	@FXML public TextField priceSearchEnd;
	@FXML public TextField productNoDetail; 
	@FXML public TextField productNameDetail;
	@FXML public TextField priceDetail;	
	@FXML public TableView<Product> productTable;
	@FXML public TableColumn<Product,String> productNoCol;
	@FXML public TableColumn<Product, String> productNameCol;
	@FXML public TableColumn<Product, Integer> productPriceCol;
	@FXML public Button btnSearch;
	@FXML public Button btnNew;
	@FXML public Button btnSave;
	
	ProductService service = new ProductService();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		init();
		initEvent();
	}

	public void init() {
		productNoCol.setCellValueFactory(new PropertyValueFactory<>("productNo"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		BorderPane root = (BorderPane) productTable.getParent();
		productTable.prefWidthProperty().bind(root.widthProperty().subtract(10));
		productNoCol.prefWidthProperty().bind(productTable.widthProperty().multiply(.2));
		productNameCol.prefWidthProperty().bind(productTable.widthProperty().multiply(.5));
		productPriceCol.prefWidthProperty().bind(productTable.widthProperty().multiply(.3));
		
		setBtnSearch() ;
      		
	}
	
	public void setBtnSearch() {
		
		ParamMap param = new ParamMap();
		System.out.println(this.productNameSearch);
		System.out.println(this.priceSearchStart);
		System.out.println(this.priceSearchEnd);
		param.put("productNameSearch", productNameSearch.getText());
		param.put("priceSearchStart", priceSearchStart.getText());
		param.put("priceSearchEnd", priceSearchEnd.getText());
		System.out.println("param : "+ param);
		
		service.selectProductList(param);
		
		List<ParamMap> list = param.getList("resultList");
		List<Product> productList = new ArrayList<>();
		initDetail() ;
		int index = 0;
		if(list != null) {
			for(ParamMap p:list) {
				index+=1;
				if (index ==1) continue;
				Product v = new Product();
				v.setProductNo(p.getString("PRODUCT_NO"));
				v.setProductName(p.getString("PRODUCT_NAME"));
				v.setPrice(p.getInteger("PRICE"));
				productList.add(v);
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
			(obervale, oldValue, newValue) -> {
				initDetail();
				if(newValue!=null) {
					productNoDetail.setText(newValue.getProductNo());
					productNameDetail.setText(newValue.getProductName());
					priceDetail.setText(""+newValue.getPrice());
				}

			});
		btnSearch.setOnAction(arg0 -> setBtnSearch());
		
		btnNew.setOnAction(arg0 -> {
			productTable.getSelectionModel().clearSelection();
			initDetail();
		});
		btnSave.setOnAction(arg0 -> {
			String productName = productNameDetail.getText();
			String price = priceDetail.getText();
			if(productName.trim().equals("") || productName == null ||
					price.trim().equals("")|| price == null) {
				String msg = "폼목명 또는 단가는 필수 입력 항목 입니다.";
				new Alert(AlertType.ERROR,msg).show();
				return;
			}
			ParamMap param = new ParamMap();
			param.put("productNo", productNoDetail.getText());
			param.put("productName", productNameDetail.getText());
			param.put("price", priceDetail.getText());
			service.saveProduct(param);
			String productNo = param.getString("productNo");
			setBtnSearch();
			ObservableList<Product> items = productTable.getItems();
			for(Product v: items) {
				if(v.getProductNo().equals(productNo)) {
					productTable.getSelectionModel().select(v);
					productTable.scrollTo(v);
					break;
				}
			}

		});
	}
	
}
