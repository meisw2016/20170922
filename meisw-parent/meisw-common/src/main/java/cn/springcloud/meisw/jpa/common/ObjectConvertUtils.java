package cn.springcloud.meisw.jpa.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.springcloud.meisw.jpa.wy.dto.DomainRequest;

public class ObjectConvertUtils {
	
	public static <T> List<T> objectToBean(List<Object[]> objList,Class<T> clz)throws Exception{
		if(objList == null || objList.size() ==0) {
			return null;
		}
		Class<?>[] cz = null;
		Constructor<?>[] cons = clz.getConstructors();
		for(Constructor<?> ct : cons) {
			
			
			Class<?>[] clazz = ct.getParameterTypes();
			if(objList.get(0).length == clazz.length) {
				cz = clazz;
				break;
			}
		}
		
		List<T> list = new ArrayList<T>();
		for(Object[] obj : objList) {
			Constructor<T> cr = clz.getConstructor(cz);
			list.add(cr.newInstance(obj));
		}
 		return list;
	}
	
	public static void main(String[] args) {
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
		
		try {
			List<DomainRequest> list = ObjectConvertUtils.objectToBean(objList, DomainRequest.class);
			for(DomainRequest request:list) {
				System.out.println(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
