package JDBCProject;

public class ProductService {
	ProductDao dao = new ProductDao();
	public void selectProductList(ParamMap param) {
		dao.selectProductList(param);
	}
	public void saveProduct(ParamMap param) {
		String productNo = param.getString("productNo");
		if(productNo == null || productNo.trim().equals("")) {
			ParamMap selectOn = dao.selectNextProductNo(param);
			productNo = selectOn.getString("PRODUCT_NO");
			param.put("productNo", productNo);
			dao.insertProduct(param);
		}
		else {
			dao.updateProduct(param);
		}
	}
}
