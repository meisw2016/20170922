package cn.springcloud.meisw.kafka.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DateUtil {
	
	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	    String t1 = "2020-02-19 16:13";
//	    Date date = sdf.parse(t1);
//	    System.out.println(date.getTime());
//	    System.out.print(String.valueOf(date.getTime()).length());
		
		test();
		
    }
	
	public static void test(){
		JSONArray moList = new JSONArray();
		JSONObject par = new JSONObject();
		JSONArray parList = new JSONArray();
		par.put("parName", "session_cur1");
		par.put("parAlias", "指标1");
		par.put("session_cur1", "3");
		parList.add(par);
		
		par = new JSONObject();
		par.put("parName", "session_cur");
		par.put("parAlias", "指标2");
		par.put("session_cur", "13");
		parList.add(par);
		
		JSONObject mo = new JSONObject();
		mo.put("moName", "指标对象1");
		mo.put("parList", parList);
		
		moList.add(mo);
		
		
		par = new JSONObject();
		parList = new JSONArray();
		par.put("parName", "session_cur2");
		par.put("parAlias", "指标2");
		
		
		par.put("session_cur1", "23");
		parList.add(par);
		
		par = new JSONObject();
		par.put("parName", "session_cur3");
		par.put("parAlias", "指标3");
		par.put("session_cur", "33");
		parList.add(par);
		
		mo = new JSONObject();
		mo.put("moName", "指标对象2");
		mo.put("parList", parList);
		
		moList.add(mo);
		System.out.println(moList);
	}
}
