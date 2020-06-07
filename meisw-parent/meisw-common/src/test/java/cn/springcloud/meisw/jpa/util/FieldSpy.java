package cn.springcloud.meisw.jpa.util;

import java.util.ArrayList;
import java.util.List;

public class FieldSpy {
	
	public static void main(String[] args) throws Exception {
		Object[] obj = new Object[4];
		obj[0] = 1L;
		obj[1] = "aaa";
		obj[2] = "bbb";
		obj[3] = "ccc";
		
		Object[] obj1 = new Object[4];
		obj1[0] = 2L;
		obj1[1] = "abc";
		obj1[2] = "bcd";
		obj1[3] = "cde";
		
		List<Object[]> objList = new ArrayList<Object[]>();
		objList.add(obj);
		objList.add(obj1);
		
		
	}
}
