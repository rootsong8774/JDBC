package JDBCProject;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClient {
     public static ParamMap selectOne(String query, List<String> param) {
    	 System.out.println("쿼리:"+query);
    	 System.out.println("파라미터:"+param);
    	 if(query == null) return null;
    	 String[] arr = null;
    	 if(param !=null) {
    		 arr = new String[param.size()];
    		 param.toArray(arr);
    	 }
    	 List<String[]> executeQuery = DatabaseUtil.executeQuery(query, arr);
    	 ParamMap paramMap = new ParamMap();
    	 if(executeQuery.size() == 2) {
    		 String[] headers = executeQuery.get(0);
    		 String[] data = executeQuery.get(1);
    		 for(int i = 0; i <headers.length; i++)
    			 paramMap.put(headers[i], data[i]);
    		 return paramMap;
    	 }
    	 return null;
     }
     public static ParamMap selectOne(String query) {
    	 return selectOne(query, null);
     }
     public static List<ParamMap> selectList(String query, List<String> param) {
    	 System.out.println("쿼리:"+query);
    	 System.out.println("파라미터:"+param);
    	 if(query == null) return null;
    	 String[] arr = null;
    	 if(param !=null) {
    		 arr = new String[param.size()];
    		 param.toArray(arr);
    	 }
    	 List<String[]> executeQuery = DatabaseUtil.executeQuery(query, arr);
    	 if(executeQuery != null  ) {
    		 String[] headers = executeQuery.get(0);
    		 List<ParamMap> list = new ArrayList<>();
			 for (String[] strings : executeQuery) {
				 ParamMap paramMap = new ParamMap();
				 for (int j = 0; j < headers.length; j++)
					 paramMap.put(headers[j], strings[j]);
				 list.add(paramMap);
			 }
    		 return list;
    	 }
    	 return null;
     }
     public static List<ParamMap> selectList(String query) {
    	 return selectList(query,null);
     }
     public static int insert(String query, List<String> param ) {
    	 return write(query, param);
     }
     public static int insert(String query) {
    	 return write(query, null);
     }
     public static int update(String query, List<String> param ) {
    	 return write(query, param);
     }
     public static int update(String query) {
    	 return write(query, null);
     }
     public static int delete(String query, List<String> param ) {
    	 return write(query, param);
     }
     public static int delete(String query) {
    	 return write(query, null);
     }
     private static int write(String query, List<String> param ) {
    	 System.out.println("쿼리:"+query);
    	 System.out.println("파라미터:"+param);
    	 if(query == null) return -1;
    	 String[] arr = null;
    	 if(param !=null) {
    		 arr = new String[param.size()];
    		 param.toArray(arr);
    	 }    	 
    	 return DatabaseUtil.executeUpdate(query, arr);
     }
     
     
}
