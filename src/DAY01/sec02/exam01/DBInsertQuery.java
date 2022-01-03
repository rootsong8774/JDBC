package DAY01.sec02.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInsertQuery {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "javastudy";
        String pwd = "javastudy";
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection : " + conn);
            String query = "UPDATE PRODUCT SET PRICE = PRICE + 100";
            query += " WHERE PRICE > 4300 ";
            pstmt = conn.prepareStatement(query);

            int result = pstmt.executeUpdate();
            pstmt.close();

            String query2 = "INSERT INTO PRODUCT";
            query2 += " (PRODUCT_NO, PRODUCT_NAME,PRICE)";
            query2 += " VALUES(?,?,?)";
            pstmt = conn.prepareStatement(query2);

            String[][] data;
            data = new String[][]{{"A004", "오렌지쥬스", "6000"}, {"A005", "자몽쥬스", "6000"},
                {"A006", "키위쥬스", "6000"}};

            for (String[] datum : data) {

                pstmt.setString(1, datum[0]);
                pstmt.setString(2, datum[1]);
                pstmt.setString(3, datum[2]);

                pstmt.executeUpdate();
                pstmt.clearParameters();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
