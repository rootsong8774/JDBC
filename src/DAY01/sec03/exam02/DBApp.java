package DAY01.sec03.exam02;

import DAY01.sec03.exam01.DatabaseUtil;
import java.util.Arrays;
import java.util.List;

public class DBApp {

    public static void main(String[] args) {
        List<String[]> executeQuery = DatabaseUtil.executeQuery("select * from product", null);
        for (String[] arr : executeQuery) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("=======================");

        String query2 = "update product set price = price + ? where price <=?";
        int resultCount = DatabaseUtil.executeUpdate(query2, "100", "6000");
        System.out.println("=======Update : " + resultCount + "=======");

        String query3 = "delete from product where product_no in ('B001','B002','B003')";
        resultCount = DatabaseUtil.executeUpdate(query3);
        System.out.println("=======Delete : " + resultCount + "=======");

        String query4 = "insert into product(product_no, product_name,price) "
            + "values (?,?,?)";
        String[][] data = {{"B001", "오렌지쥬스", "6000"}, {"B002", "자몽쥬스", "6000"},
            {"B003", "키위쥬스", "6000"}};
        for (String[] arr : data) {
            DatabaseUtil.executeUpdate(query4, arr);
        }
        executeQuery = DatabaseUtil.executeQuery("select * from product", null);
        for (String[] arr : executeQuery) {
            System.out.println(Arrays.toString(arr));
        }

    }

}
