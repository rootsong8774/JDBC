package Day03.main;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void selectProductList(ParameterMap parameterMap) {
        String query = "select * from product where 1=1";
        List<String> list = new ArrayList<>();
        String productNameSearch = parameterMap.getString("productNameSearch");
        if (productNameSearch != null && !productNameSearch.trim().equals("")) {
            query += " and product_Name like '%'|| ? || '%' ";
            System.out.println("productNameSearch = " + productNameSearch);
            list.add(productNameSearch);
        }

        String priceSearchStart = parameterMap.getString("priceSearchStart");
        if (priceSearchStart != null && !priceSearchStart.trim().equals("")) {
            query += " and price>= ?";
            System.out.println("priceSearchStart = " + priceSearchStart);
            list.add(priceSearchStart);
        }

        String priceSearchEnd = parameterMap.getString("priceSearchEnd");
        if (priceSearchEnd != null && !priceSearchEnd.trim().equals("")) {
            query += " and price<= ?";
            System.out.println("priceSearchEnd = " + priceSearchEnd);
            list.add(priceSearchEnd);
        }
        List<ParameterMap> selectList = DBClient.selectList(query, list);
        parameterMap.put("resultList", selectList);

    }

    public ParameterMap selectNextProductNo(ParameterMap parameterMap) {
        String query = "select 'A' || lpad(Max(to_number(substr(product_no,2)))+1,3,'0') product_no from product";
        return DBClient.selectOne(query);
    }


    public void insertProduct(ParameterMap parameterMap) {
        String query = "insert into product(PRODUCT_NO,PRODUCT_NAME,PRICE) ";
        query += " values(?,?,?)";
        List<String> list = new ArrayList<>();
        list.add(parameterMap.getString("productNo"));
        list.add(parameterMap.getString("productName"));
        list.add(parameterMap.getString("price"));
        DBClient.insert(query, list);
    }

    public void updateProduct(ParameterMap parameterMap) {
        String query = "update product set product_name = ?, price = ? ";
        query += " where product_no = ?";
        List<String> list = new ArrayList<>();
        list.add(parameterMap.getString("productName"));
        list.add(parameterMap.getString("price"));
        list.add(parameterMap.getString("productNo"));
        DBClient.update(query, list);

    }


}
