package cn.springcloud.meisw.jpa.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.springcloud.meisw.jpa.common.ObjectConvertUtils;
import cn.springcloud.meisw.jpa.wy.dto.DomainRequest;

public class ObjectConvertUtil {
	public static <T> List<T> objectToBean(List<Object[]> objList,Class<T> clz)throws Exception{
		if(objList == null || objList.size() ==0) {
			return null;
		}
		Class<?>[] cz = null;
		
		Field[] fields = clz.getDeclaredFields();
		for(Field curField:fields) {
			curField.setAccessible(true);
			Class<?> curFieldType = curField.getType();
			Type genericType =curField.getGenericType();
			if(null == genericType) {
				continue;
			}
			if(genericType instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType)genericType;
				//得到泛型里的class类型对象
				Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
				List<Object[]> objListNew = new ArrayList<Object[]>();
				Object actualType = actualTypeArgument.newInstance();
				//objListNew.add(actualType);
			}
		}
		
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
		
		try {
			List<DomainRequest> list = ObjectConvertUtil.objectToBean(objList, DomainRequest.class);
			for(DomainRequest request:list) {
				System.out.println(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
