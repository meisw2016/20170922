package cn.springcloud.meisw.kafka.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;



public class Test {
	public static void main(String[] args) {
	    JSONArray arr = new JSONArray();
	    JSONObject obj = new JSONObject();
	    obj.put("2019-12-29 22:00", "3");
	    arr.add(obj);
	    obj.put("2020-12-30 12:00", "9");
	    arr.add(obj);
	    
	    String result [][] = null;
	    String keys[] = null;
	    List<String> keysList = new ArrayList<String>();
	    for(int i=0;i<arr.size();i++){
	    	JSONObject jsonObject = arr.getJSONObject(i);
	    	Map<String,Object> map = jsonObject.getInnerMap();
	    	Iterator<String> iter = map.keySet().iterator();
	    	if(i==0){
	    		while(iter.hasNext()){
	    			String key = iter.next();
	    			keysList.add(key);
	    		}
	    		keys = keysList.toArray(new String[keysList.size()]);
	    		result = new String[arr.size()+1][keys.length];
	    		result[0] = keys;
	    	}
	    	for(int j=0;j<keys.length;j++){
	    		result[i+1][j]=map.get(keys[j]).toString();
	    	}
	    }
	    System.out.println(result);
    }
}
