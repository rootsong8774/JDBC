package Day03.main;

import java.util.ArrayList;
import java.util.List;

public class DBClient {

    public static ParameterMap selectOne(String query, List<String> param) {
        System.out.println("Query: " + query);
        System.out.println("Parameter: " + param);
        if (query == null) {
            return null;
        }
        String[] arr = null;
        if (param != null) {
            arr = new String[param.size()];
            param.toArray(arr);
        }
        List<String[]> executeQuery = DBUtil.executeQuery(query, arr);
        ParameterMap parameterMap = new ParameterMap();
        if (executeQuery != null && executeQuery.size() == 2) {
            String[] headers = executeQuery.get(0);
            String[] data = executeQuery.get(1);
            for (int i = 0; i < headers.length; i++) {
                parameterMap.put(headers[i], data[i]);
            }
            return parameterMap;
        }
        return null;
    }

    public static ParameterMap selectOne(String query) {

        return selectOne(query, null);
    }

    public static List<ParameterMap> selectList(String query, List<String> param) {

        System.out.println("Query: " + query);
        System.out.println("Parameter: " + param);
        if (query == null) {
            return null;
        }
        String[] arr = null;
        if (param != null) {
            arr = new String[param.size()];
            param.toArray(arr);
        }
        List<String[]> executeQuery = DBUtil.executeQuery(query, arr);

        if (executeQuery != null) {
            String[] headers = executeQuery.get(0);
            List<ParameterMap> list = new ArrayList<>();
            for (int i = 0; i < executeQuery.size(); i++) {
                ParameterMap parameterMap = new ParameterMap();
                String[] data = executeQuery.get(i);
                for (int j = 0; j < headers.length; j++) {
                    parameterMap.put(headers[j], data[j]);

                }
                list.add(parameterMap);

            }

            return list;
        }
        return null;
    }

    public static List<ParameterMap> selectList(String query) {

        return selectList(query, null);
    }

    public static int insert(String query, List<String> param) {

       return write(query, param);
    }

    public static int insert(String query) {
        return write(query, null);
    }

    public static int update(String query, List<String> param) {
        return write(query, param);

    }

    public static int update(String query) {
        return write(query, null);
    }

    public static int delete(String query, List<String> param) {
        return write(query, param);
    }

    public static int delete(String query) {
        return write(query, null);
    }

    private static int write(String query, List<String> param) {
        System.out.println("Query: " + query);
        System.out.println("Parameter: " + param);
        if (query == null) {
            return -1;
        }
        String[] arr = null;
        if (param != null) {
            arr = new String[param.size()];
            param.toArray(arr);
        }

        return DBUtil.executeUpdate(query,arr);

    }


}
