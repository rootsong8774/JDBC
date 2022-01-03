package DAY01.sec01.exam03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class QueryWithParameter {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "javastudy";
        String pwd = "javastudy";
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection : " + conn);
            String query = "";
            query += "select To_char(pp.SALE_DATE, 'YYYY-MM-DD') as SALE_DATE,\n"
                + "       A.PRODUCT_NO,\n"
                + "       A.PRODUCT_NAME,\n"
                + "       ppd.PRICE,\n"
                + "       ppd.QTY,\n"
                + "       ppd.PRICE * ppd.QTY                 as TOTAL_AMOUNT\n"
                + "from product A\n"
                + "         join PRODUCT_PURCHASE_DETAIL PPD on A.PRODUCT_NO = PPD.PRODUCT_NO\n"
                + "         join PRODUCT_PURCHASE PP on PP.ACCOUNT_NO = PPD.ACCOUNT_NO\n"
                + "where PP.SALE_DATE <= To_date(20190102, 'YYYYMMDD')\n"
                + "  and PP.TOTAL >= 9000\n"
                + "  and PP.SALE_DATE >= To_date(20190101, 'YYYYMMDD')";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    String value = rs.getString(i + 1);
                    System.out.print("\t" + columnName + "[" + value + "]");
                }
                System.out.println();
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
