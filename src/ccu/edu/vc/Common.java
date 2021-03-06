package ccu.edu.vc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Common {
	//����QueryString
	public static Map<String, String[]> getParamsMap(String queryString, String enc) {  
	    Map<String, String[]> paramsMap = new HashMap<String, String[]>();  
	    if (queryString != null && queryString.length() > 0) {  
	      int ampersandIndex, lastAmpersandIndex = 0;  
	      String subStr, param, value;  
	      String[] paramPair, values, newValues;  
	      do {  
	        ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;  
	        if (ampersandIndex > 0) {  
	          subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);  
	          lastAmpersandIndex = ampersandIndex;  
	        } else {  
	          subStr = queryString.substring(lastAmpersandIndex);  
	        }  
	        paramPair = subStr.split("=");  
	        param = paramPair[0];  
	        value = paramPair.length == 1 ? "" : paramPair[1];  
	        try {  
	          value = URLDecoder.decode(value, enc);  
	        } catch (UnsupportedEncodingException ignored) {  
	        }  
	        if (paramsMap.containsKey(param)) {  
	          values = paramsMap.get(param);  
	          int len = values.length;  
	          newValues = new String[len + 1];  
	          System.arraycopy(values, 0, newValues, 0, len);  
	          newValues[len] = value;  
	        } else {  
	          newValues = new String[] { value };  
	        }  
	        paramsMap.put(param, newValues);  
	      } while (ampersandIndex > 0);  
	    }  
	    return paramsMap;  
	  }  


}
