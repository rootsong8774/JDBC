package JDBCProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

	public static List<String[]> executeQuery(String query, String... params){
		List<String[]> resultList = new ArrayList<>();
		if(query == null) return resultList;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String url="jdbc:oracle:thin:@localhost:1521/orclXDB";
		String user="javastudy";
		String pwd="javastudy";

		try {
			conn = DriverManager.getConnection(url, user, pwd);
			pstmt = conn.prepareStatement(query);
			pstmt.clearParameters();
			if(params != null) {
				int index = 0;
				for(String param : params)
				    pstmt.setString(++index, param);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			String[] header = new String[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++)
				header[i] = rsmd.getColumnName(i+1);
			resultList.add(header);
			while(rs.next()) {
				String[] data = new String[rsmd.getColumnCount()];
				for(int i = 0; i < rsmd.getColumnCount(); i++)
					data[i] = rs.getString(i+1);
				resultList.add(data);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {if(rs!=null) rs.close();} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {if(pstmt != null) pstmt.close();}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {if(conn != null) conn.close();}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultList;
		
	}
	public static int executeUpdate(String query, String... params) {
		int resultCount = -1; 
		if(query == null) return resultCount;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="javastudy";
		String pwd="javastudy";
		try {
		conn = DriverManager.getConnection(url, user, pwd);
		pstmt = conn.prepareStatement(query);
		pstmt.clearParameters();
		if(params != null) {
			int index = 0;
			for(String param : params)
			    pstmt.setString(++index, param);
		}
		
			resultCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {if(rs!=null) rs.close();} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {if(pstmt != null) pstmt.close();}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {if(conn != null) conn.close();}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultCount;
		
	}
}
