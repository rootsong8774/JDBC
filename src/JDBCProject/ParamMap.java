package JDBCProject;

import java.util.HashMap;
import java.util.List;

public class ParamMap extends HashMap<String, Object> {
	public String getString(String key) {
		Object object = get(key);
		if(object != null) {return object.toString();}
		return null;
	}
	public Integer getInteger(String key) {
		Object object = get(key);
		if(object != null) {return Integer.parseInt(object.toString());}
		return null;
	}
	public List getList(String key) {
		Object object = get(key);
		if(object != null && object instanceof List) {
			return (List)(object);}
		return null;
	}
    public ParamMap getParamMap(String key) {
    	Object object = get(key);
		if(object != null && object instanceof ParamMap) {
			return (ParamMap)(object);}
    	return null;
    }
    public <T> T getType(String key, Class<T> clazz) {
    	Object object = get(key);
    	if(object != null && clazz.isAssignableFrom(object.getClass())) {
    		return (T) object;
    	}
    	return null;
    }
    
}
