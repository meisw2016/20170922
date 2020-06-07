package cn.springcloud.meisw.kafka.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springcloud.meisw.kafka.util.ExportExcel;

@Controller
@RequestMapping("/export")
public class ExportExcelController {
	
	@RequestMapping("/excel")
	public String exportExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Map> list = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("1", "张三");
		map.put("2", "10|");
		map.put("3", "北京");
		map.put("4", "党员");
		list.add(map);
		
		map = new HashMap();
		map.put("1", "张三2");
		map.put("2", "102");
		map.put("3", "北京2");
		map.put("4", "党员2");
		list.add(map);
		String[] columnNames = {"姓名","年龄","籍贯","面貌"};
		ExportExcel.exportExcel2("导出excelsheet名称", "标题", columnNames, list, "yyyy-MM-dd HH:mm:ss", response);
		return null;
	}
}
