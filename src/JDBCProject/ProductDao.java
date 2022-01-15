package JDBCProject;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public void selectProductList(ParamMap param) {
        String query = "select * from product where 1=1";
        List<String> list = new ArrayList<>();
        String productNameSearch = param.getString("productNameSearch");
        if (!(productNameSearch == null || productNameSearch.trim().equals(""))) {
            query += " and product_name like '%' || ? || '%'";
            System.out.println("productNameSearch = " + productNameSearch);
            list.add(productNameSearch);
        }
        String priceSearchStart = param.getString("priceSearchStart");
        if (!(priceSearchStart == null || priceSearchStart.trim().equals(""))) {
            query += " and price >= ?";
            System.out.println("priceSearchStart = " + priceSearchStart);
            list.add(priceSearchStart);
        }
        String priceSearchEnd = param.getString("priceSearchEnd");
        if (!(priceSearchEnd == null || priceSearchEnd.trim().equals(""))) {
            query += " and price <= ?";
            System.out.println("priceSearchEnd = " + priceSearchEnd);
            list.add(priceSearchEnd);
        }
        List<ParamMap> selectList = DatabaseClient.selectList(query, list);
        param.put("resultList", selectList);
    }

    public ParamMap selectNextProductNo(ParamMap param) {
        String query = "select 'A'|| lpad(max(to_number(substr(product_no,2)))+1,3,'0') product_no from product ";
        return DatabaseClient.selectOne(query);
    }

    public void insertProduct(ParamMap param) {
        String query = "insert into product(product_no, product_name, price)";
        query += " values(?,?,?)";
        List<String> list = new ArrayList<>();
        list.add(param.getString("productNo"));
        list.add(param.getString("productName"));
        list.add(param.getString("price"));
        DatabaseClient.insert(query, list);
    }

    public void updateProduct(ParamMap param) {
        String query = "update  product set product_name= ?, price = ?";
        query += " where product_no = ?";
        List<String> list = new ArrayList<>();
        list.add(param.getString("productNo"));
        list.add(param.getString("productName"));
        list.add(param.getString("price"));
        DatabaseClient.update(query, list);
    }


}
