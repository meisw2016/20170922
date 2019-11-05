package cn.springcloud.meisw.jpa.conf.caffeine;

import java.io.Serializable;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月1日 下午3:06:50
 * @ClassName CacheMessage
 * @Description redis消息发布/订阅，传输的消息类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月1日
 * @modify by reason:{方法名}:{原因}
 */
public class CacheMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cacheName;
	private Object key;
	
	public String getCacheName() {
		return cacheName;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public Object getKey() {
		return key;
	}
	
	public void setKey(Object key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "CacheMessage [cacheName=" + cacheName + ", key=" + key + "]";
	}

	public CacheMessage(String cacheName, Object key) {
		super();
		this.cacheName = cacheName;
		this.key = key;
	}

	public CacheMessage() {
		super();
	}
	
	
}
