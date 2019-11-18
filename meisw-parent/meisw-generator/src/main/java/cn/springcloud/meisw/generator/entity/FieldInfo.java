package cn.springcloud.meisw.generator.entity;

import lombok.Data;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午2:37:33
 * @ClassName FieldInfo
 * @Description field info
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Data
public class FieldInfo {

    private String columnName;
    private String fieldName;
    private String fieldClass;
    private String fieldComment;

}
