package cn.springcloud.book.eureka.automation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p></p>
 * @author meisw 2019年9月16日 下午4:47:51
 * @ClassName LogException
 * @Description 异常信息类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年9月16日
 * @modify by reason:{方法名}:{原因}
 */
public class LogException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LogException.class);
	
	public LogException(String message) {
		super(message);
	}
	
	public LogException(String message,Exception exception) {
		this(message+exception.getMessage());
		log.error(message,exception);
	}
}
