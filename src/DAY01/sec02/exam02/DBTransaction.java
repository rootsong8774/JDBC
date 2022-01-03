package DAY01.sec02.exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTransaction {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isSuccess = false;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "javastudy";
        String pwd = "javastudy";
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection : " + conn);

            conn.setAutoCommit(false);

            String query = "";
            query += "select nvl(max(ACCOUNT_NO), ?||'00000')+1 as account_no "
                + " from PRODUCT_PURCHASE where substr(account_no, 1,4)=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "2019");
            pstmt.setString(2, "2019");
            System.out.println("Query 1 : " + query);
            rs = pstmt.executeQuery();
            String accountNo = null;
            if (rs.next()) {
                accountNo = rs.getString("ACCOUNT_NO");
            }
            System.out.println("신규 전표번호 : " + accountNo);

            if (accountNo == null || accountNo.equals("")) {
                throw new Exception("오류 발생 - 전표번호 없음");
            }

            query =
                "insert into PRODUCT_PURCHASE (account_no, SALE_DATE,TOTAL) "
                    + "values (?, sysdate, ?)\n";

            pstmt.close();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, accountNo);
            pstmt.setString(2, "18700");
            System.out.println("Query 2 : " + query);
            int result = pstmt.executeUpdate();

            if (result != 1) {
                throw new Exception("오류 발생 - 품목 구매 삽입 오류");
            }

            query = "insert into PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) "
                + "values (?, ?,?,?,?)";
            pstmt.close();
            pstmt = conn.prepareStatement(query);

            String[][] data = {{accountNo, "1", "A001", "4000", "1"},
                {accountNo, "2", "A002", "4300", "1"}, {accountNo, "3", "A003", "4500", "1"}};

            for (String[] datum : data) {
                pstmt.setString(1, datum[0]);
                pstmt.setString(2, datum[1]);
                pstmt.setString(3, datum[2]);
                pstmt.setString(4, datum[3]);
                pstmt.setString(5, datum[4]);
                pstmt.executeUpdate();
                System.out.println("Query 3 : " + query);
                pstmt.clearParameters();
            }

            conn.commit();
            isSuccess = true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isSuccess) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if (!isSuccess) {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
