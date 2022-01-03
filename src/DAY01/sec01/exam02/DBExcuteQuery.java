package DAY01.sec01.exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBExcuteQuery {

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
            String query = "Select * from product";
            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()){
                for( int i = 0; i< rsmd.getColumnCount(); i++){
                    String columnName = rsmd.getColumnName(i+1);
                    String value = rs.getString(i+1);
                    System.out.print("\t"+ columnName+"["+value +"]");
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
