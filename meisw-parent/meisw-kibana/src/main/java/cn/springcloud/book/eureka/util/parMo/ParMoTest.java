package cn.springcloud.book.eureka.util.parMo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ParMoTest {
	
	public static void main(String[] args) {
		qianduanParam();
		test1();
		
    }
	
	public static void test1(){
		//一个监控指标下的多个监控对象
				List<Mo> moList = new ArrayList<Mo>();
			    //监控对象
				Mo mo = new Mo();
				mo.setMoId("moId1");
				mo.setMoName("moName1");
				mo.setJson(null);
				moList.add(mo);
				
				mo = new Mo();
				mo.setMoId("moId2");
				mo.setMoName("moName2");
				mo.setJson(null);
				moList.add(mo);
				
				Par par = new Par();
				par.setParId("parId1");
				par.setParName("parName1");
				par.setMoList(moList);
				
				//多个监控指标
				Map<String,Par> parMap = new HashMap<String,Par>();
				parMap.put("parId1", par);
				parMap.put("parId2", par);
				
				JSONObject result = JSONObject.fromObject(parMap);
				System.out.println(result);
	}
	
	
	public static void qianduanParam(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		/**
		 * 单个分类
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parId", "parId1");
		map.put("parName", "parName1");
		map.put("clsId", "clsId1");
		map.put("infoAppCompId", "infoAppcompId1");
		map.put("starTime", "2020-02-20 08:00");
		map.put("endTime", "2020-02-22 17:00");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("parId", "parId2");
		map.put("parName", "parName2");
		map.put("clsId", "clsId2");
		map.put("infoAppCompId", "infoAppcompId2");
		map.put("starTime", "2020-02-20 08:00");
		map.put("endTime", "2020-02-22 17:00");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("parId", "parId3");
		map.put("parName", "parName3");
		map.put("clsId", "clsId3");
		map.put("infoAppCompId", "infoAppcompId3");
		map.put("starTime", "2020-02-20 08:00");
		map.put("endTime", "2020-02-22 17:00");
		list.add(map);
		JSONArray arr = JSONArray.fromObject(list);
		System.out.println(arr.toString());
	}
}
