package Day03.main;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();
    public void selectProductList(ParameterMap parameterMap) {
        productDAO.selectProductList(parameterMap);
    }

    public void saveProduct(ParameterMap parameterMap) {
        String productNo = parameterMap.getString("productNo");
        if(productNo == null || productNo.trim().equals("")){
            ParameterMap selectOne = productDAO.selectNextProductNo(parameterMap);
            productNo = selectOne.getString("PRODUCT_NO");
            parameterMap.put("productNo",productNo);
            productDAO.insertProduct(parameterMap);
        }
        else {
            productDAO.updateProduct(parameterMap);
        }
    }

}
