package cn.springcloud.book.eureka.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.springcloud.book.eureka.util.parMo.Mo;
import cn.springcloud.book.eureka.util.parMo.Par;


public class DataUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DataUtil.class);
	
	public static void main(String[] args) {
		
//		List<String> x = new ArrayList<String>();
//		x.add("9:00");
//		x.add("9:03");
//		x.add("9:06");
//		x.add("9:09");
//		x.add("9:12");
//		x.add("9:15");
//		x.add("9:18");
//		x.add("9:21");
//		x.add("9:24");
//		x.add("9:27");
//		x.add("9:30");
		
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		map = new HashMap<String,Object>();
		map.put("clsId", "11122");
		map.put("clsName", "esb_status");
		map.put("clsAlias", "esb_status");
		map.put("parId","12658");
		map.put("parName", "link_status");
		map.put("parAlias", "link_status");
		map.put("moId", "1821383");
		map.put("moName", "P2ESB.a1");
		map.put("occurtime", "1579400040000");
		map.put("value", "0.00");
		map.put("msg", "指标默认值：0");
		map.put("compId", "820");
		map.put("lastUpdateTime", "1579400040505");
		map.put("intervalminutes", "1");
		map.put("defaultvalue", "");
		map.put("description", "");
		map.put("compClsDefTreeId", "10295");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("clsId", "11122");
		map.put("clsName", "esb_status");
		map.put("clsAlias", "esb_status");
		map.put("parId","12715");
		map.put("parName", "job_status");
		map.put("parAlias", "job_status");
		map.put("moId", "1821384");
		map.put("moName", "P2ESB.a2");
		map.put("occurtime", "1579400040000");
		map.put("value", "0.00");
		map.put("msg", "指标默认值：0");
		map.put("compId", "820");
		map.put("lastUpdateTime", "1579400040505");
		map.put("intervalminutes", "1");
		map.put("defaultvalue", "");
		map.put("description", "");
		map.put("compClsDefTreeId", "10295");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("clsId", "11122");
		map.put("clsName", "esb_status");
		map.put("clsAlias", "esb_status");
		map.put("parId","12658");
		map.put("parName", "link_status");
		map.put("parAlias", "link_status");
		map.put("moId", "1821384");
		map.put("moName", "P2ESB.a2");
		map.put("occurtime", "1579400040000");
		map.put("value", "0.00");
		map.put("msg", "指标默认值：0");
		map.put("compId", "820");
		map.put("lastUpdateTime", "1579400040505");
		map.put("intervalminutes", "1");
		map.put("defaultvalue", "");
		map.put("description", "");
		map.put("compClsDefTreeId", "10295");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("clsId", "11122");
		map.put("clsName", "esb_status");
		map.put("clsAlias", "esb_status");
		map.put("parId","12715");
		map.put("parName", "job_status");
		map.put("parAlias", "job_status");
		map.put("moId", "1821383");
		map.put("moName", "P2ESB.a1");
		map.put("occurtime", "1579400040000");
		map.put("value", "0.00");
		map.put("msg", "指标默认值：0");
		map.put("compId", "820");
		map.put("lastUpdateTime", "1579400040505");
		map.put("intervalminutes", "1");
		map.put("defaultvalue", "");
		map.put("description", "");
		map.put("compClsDefTreeId", "10295");
		list.add(map);
		
		Mo mo = null;
		List<Mo> moList = new ArrayList<Mo>();
		Par par = null;
		Map<String,Par> parMap = new HashMap<String,Par>();
		//遍历list集合
		for(Map<String,Object> _map:list){
			//得到当前的监控指标
			String parId = (String)_map.get("parId");
			//如果存在当前的指标
			log.info("当前监控指标id为：{}",parId);
			//遍历parMap所有的key
			Set<String> keys = parMap.keySet();
			if(keys.contains((String)_map.get("parId"))){
				//获取当前指标的所有指标对象
				List<Mo> moListTmp = parMap.get(((_map.get("parId")))).getMoList();
				JSONObject json = JSONObject.fromObject(_map);
				mo = new Mo();
				mo.setMoId((String)_map.get("moId"));
				mo.setMoName((String)_map.get("moName"));
				mo.setJson(json);
				moListTmp.add(mo);
				par = new Par();
				par.setParId(parId);
				par.setParName(((String)_map.get("parName")));
				par.setMoList(moListTmp);
				parMap.put(parId, par);
			}else{
				//当前不存在这个指标，直接添加进去
				JSONObject json = JSONObject.fromObject(_map);
				mo = new Mo();
				mo.setMoId((String)_map.get("moId"));
				mo.setMoName((String)_map.get("moName"));
				mo.setJson(json);
				moList = new ArrayList<Mo>();
				moList.add(mo);
				par = new Par();
				par.setParId(parId);
				par.setParName(((String)_map.get("parName")));
				par.setMoList(moList);
				parMap.put(parId, par);
			}
			
			
//			if(parMap.get.containsKey(tmp.get("parId"))){
//				parMap = new HashMap<String,Object>();
//				moMap.put("parId", (String)tmp.get("parId"));
//				moMap.put("parName", (String)tmp.get("parName"));
//				moMap.put("data", JSONObject.fromObject(tmp));
//				moMap.get(tmp.get("parId"));
//				
				 
//			}else{
//				JSONObject tmp1 = JSONObject.fromObject(tmp);
//				moArr.add(tmp1);
//				par.put((String)tmp.get("parId"), moArr);
//			}
			
		}
		JSONObject result = JSONObject.fromObject(parMap);
		log.info("结果：{}",result);
		
	}
}
