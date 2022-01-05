package Day03.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    public static List<String[]> executeQuery(String query, String... params) {

        List<String[]> resultList = new ArrayList<>();
        if (query == null) {
            return resultList;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "javastudy";
        String pwd = "javastudy";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.clearParameters();
            if (params != null) {
                int index = 0;
                for (String param : params) {
                    preparedStatement.setString(++index, pwd);
                }

            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            String[] header = new String[resultSetMetaData.getColumnCount()];
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                header[i] = resultSetMetaData.getColumnName(i + 1);
            }
            resultList.add(header);

            while (resultSet.next()) {
                String[] data = new String[resultSetMetaData.getColumnCount()];
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    data[i] = resultSet.getString(i + 1);
                }
                resultList.add(data);

            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {

                conn.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;


    }


    public static int executeUpdate(String query, String... params) {

        int resultCount = -1;
        if (query == null) {
            return resultCount;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "javastudy";
        String pwd = "javastudy";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.clearParameters();
            if (params != null) {
                int index = 0;
                for (String param : params) {
                    preparedStatement.setString(++index, pwd);
                }

            }
            resultCount = preparedStatement.executeUpdate();
            if (conn != null) {

                conn.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultCount;


    }
}
