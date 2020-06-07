package cn.springcloud.meisw.jpa.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年11月19日 下午5:22:14
 * @ClassName MeiswBeanUtils
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月19日
 * @modify by reason:{方法名}:{原因}
 */
public class MeiswBeanUtils {
	
	public static <S, T> List<T> copyProperties(List<S> srcList, Class<T> targetType) {
		List<T> targetList = new ArrayList<T>();
		srcList.stream().forEach(src -> {
			T t = BeanUtils.instantiateClass(targetType);
			BeanUtils.copyProperties(src, t);
			targetList.add(t);
		});
		return targetList;
	}
}
