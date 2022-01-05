package Day03.main;

import java.util.HashMap;
import java.util.List;

public class ParameterMap extends HashMap<String, Object> {

    public String getString(String key) {
        Object object = get(key);
        if (object != null) {
            return object.toString();
        }

        return null;
    }

    public Integer getInteger(String key) {
        Object object = get(key);
        if (object != null) {
            return Integer.parseInt(object.toString());
        }
        return null;
    }

    public List getList(String key) {
        Object object = get(key);
        if (object != null && object instanceof List) {
            return (List) object;
        }
        return null;
    }

    public ParameterMap getParameterMap(String key) {
        Object object = get(key);
        if (object != null) {
            return (ParameterMap) object;
        }
        return null;
    }

    public <T> T getType(String key, Class<T> tClass) {
        Object object = get(key);
        if (object != null && tClass.isAssignableFrom(object.getClass())) {
            return (T) object;
        }
        return null;
    }

}
