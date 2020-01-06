package cn.springcloud.meisw.eureka.util;

import java.io.Serializable;

/**
 * Created by meisw on 2017/9/26.
 * @param <E>泛型用于适应业务数据类型
 */
public class OutputData<E> implements Serializable {
	
	/**
	 * 处理成功的状态常量
	 */
	public static final Integer SUCCESS = 200;
	
	/**
	 * 处理错误时的状态常量
	 */
	public static final Integer FAIL = 500;
	/**
	 * 处理错误时的信息常量
	 */
	public static final String FAIL_MESSAGE = "处理失败";
	/**
	 * 处理成功时的错误信息常量
	 */
	public static final String SUCCESS_MESSAGE = "处理成功";
	
	/**
	 * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 状态码
	 */
	private Integer status;
	/**
	 * 必要的提示信息
	 */
	private String message;
	
	/**
	 * 业务数据
	 */
	private E data;
	
	/**
	 * 缺省构造
	 */
	public OutputData() {
	}
	
	/**
	 * 用于设置业务数据构造方法
	 * @param data
	 */
	public OutputData(E data) {
		this.data = data;
	}
	
	/**
	 * 用于设置状态、错误信息、业务数据的构造方法
	 * @param status
	 * @param message
	 * @param data
	 */
	public OutputData(Integer status, String message, E data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 获取当前状态
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 设置状态
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取信息
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置信息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取当前业务数据
	 * @return
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * 设置业务数据
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * 处理成功方法 staus=200 成功信息为默认的成功信息"处理成功"
	 * @return 成功OutputData对象
	 */
	public OutputData returnSuccess() {
		this.status = SUCCESS;
		if (this.message == null) {
			this.message = SUCCESS_MESSAGE;
		}
		return this;
	}
	
	/**
	 *
	 * 处理成功方法 status=200 成功信息为入参message
	 * @param data 成功数据
	 * @return 成功OutputData对象
	 */
	public OutputData returnSuccess(E data) {
		this.status = SUCCESS;
		this.message = SUCCESS_MESSAGE;
		this.data = data;
		return this;
	}
	
	/**
	 *
	 * 处理成功方法 status=200 成功信息为入参message
	 * @param message 成功信息
	 * @return 成功OutputData对象
	 */
	public OutputData returnSuccess(String message) {
		this.status = SUCCESS;
		this.message = message;
		return this;
	}
	
	/**
	 *
	 * 处理成功方法 status=200 成功信息为入参message
	 * @param message 成功信息
	 * @param data 成功数据
	 * @return 成功OutputData对象
	 */
	public OutputData returnSuccess(E data, String message) {
		this.status = SUCCESS;
		this.data = data;
		this.message = message;
		return this;
	}
	
	/**
	 * 处理失败的方法 status=500 失败信息为默认信息“处理失败”
	 * @return 失败OutputData对象
	 */
	public OutputData returnFail() {
		this.status = FAIL;
		if (this.message == null) {
			this.message = FAIL_MESSAGE;
		}
		return this;
	}
	
	/**
	 * 处理失败的方法 status=500 失败信息传入的失败信息
	 * @param data 失败数据
	 * @return 失败OutputData对象
	 */
	public OutputData returnFail(E data) {
		this.status = FAIL;
		this.message = message;
		return this;
	}
	
	/**
	 * 处理失败的方法 status=500 失败信息传入的失败信息
	 * @param message 失败信息
	 * @return 失败OutputData对象
	 */
	public OutputData returnFail(String message) {
		this.status = FAIL;
		this.message = message;
		return this;
	}
	
	/**
	 * 处理失败的方法 status=500 失败信息传入的失败信息
	 * @param message 失败信息
	 * @param data 失败数据
	 * @return 失败OutputData对象
	 */
	public OutputData returnFail(E data, String message) {
		this.status = FAIL;
		this.data = data;
		this.message = message;
		return this;
	}
	
	@Override
	public String toString() {
		return "OutputData{" + "status=" + status + ", message='" + message + '\'' + ", data=" + data + '}';
	}
}
