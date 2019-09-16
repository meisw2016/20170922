package cn.springcloud.book.eureka.automation.util;

import java.io.Serializable;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年9月16日 下午4:50:40
 * @ClassName OutputData
 * @Description 泛型用于业务数据类型
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年9月16日
 * @modify by reason:{方法名}:{原因}
 */
public class OutputData<E> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Integer SUCCESS = 200;
	private static final Integer FAIL = 500;
	private static final String FAIL_MESSAGE = "处理失败";
	private static final String SUCCESS_MESSAGE = "处理成功";
	private Integer status;
	private String message;
	private E data;
	
	public OutputData() {
	}
	
	public OutputData(E data) {
		this.data = data;
	}
	
	public OutputData(Integer status, String message, E data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnSuccess() {
		this.status = SUCCESS;
		if (this.message == null) {
			this.message = SUCCESS_MESSAGE;
		}
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnSuccess(E data) {
		this.status = SUCCESS;
		this.message = SUCCESS_MESSAGE;
		this.data = data;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnSuccess(String message) {
		this.status = SUCCESS;
		this.message = message;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnSuccess(E data, String message) {
		this.status = SUCCESS;
		this.data = data;
		this.message = message;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnFail() {
		this.status = FAIL;
		if (this.message == null) {
			this.message = FAIL_MESSAGE;
		}
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnFail(String message) {
		this.status = FAIL;
		this.message = message;
		return this;
	}
	
//	@SuppressWarnings("rawtypes")
//	public OutputData returnFail(E data) {
//		this.status = FAIL;
//		this.message = FAIL_MESSAGE;
//		this.data = data;
//		return this;
//	}
	
	@SuppressWarnings("rawtypes")
	public OutputData returnFail(E data, String message) {
		this.status = FAIL;
		this.data = data;
		this.message = message;
		return this;
	}
	
	@Override
	public String toString() {
		return "OutputData [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
}
