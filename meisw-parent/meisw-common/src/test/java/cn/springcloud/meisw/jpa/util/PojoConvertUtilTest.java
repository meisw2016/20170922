package cn.springcloud.meisw.jpa.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.springcloud.meisw.jpa.common.PojoConvertUtil;
import cn.springcloud.meisw.jpa.wy.dto.DomainRequest;

public class PojoConvertUtilTest {
	
	public static void main(String[] args) {
		Object[] obj = new Object[4];
		BigDecimal b = new BigDecimal(1L); 
		obj[0] = b;
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
		
		List<DomainRequest> list = PojoConvertUtil.convertPojos(objList, DomainRequest.class);
		for(DomainRequest request:list) {
			System.out.println(request);
		}
	}
}
