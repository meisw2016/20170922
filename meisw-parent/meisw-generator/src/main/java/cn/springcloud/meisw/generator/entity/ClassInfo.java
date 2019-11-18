package cn.springcloud.meisw.generator.entity;

import lombok.Data;

import java.util.List;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午2:37:18
 * @ClassName ClassInfo
 * @Description class info
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Data
public class ClassInfo {

    private String tableName;
    private String className;
	private String classComment;
	private List<FieldInfo> fieldList;

}