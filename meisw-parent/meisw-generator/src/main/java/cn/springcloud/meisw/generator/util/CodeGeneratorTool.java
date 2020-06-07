package cn.springcloud.meisw.generator.util;




import java.io.IOException;

import cn.springcloud.meisw.generator.entity.ClassInfo;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午2:39:01
 * @ClassName CodeGeneratorTool
 * @Description 自动生成代码工具类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
public class CodeGeneratorTool {

	/**
	 * process Table Into ClassInfo
	 *
	 * @param tableSql
	 * @return
	 */
	public static ClassInfo processTableIntoClassInfo(String tableSql, boolean isUnderLineToCamelCase) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql, isUnderLineToCamelCase);
	}

}