package cn.springcloud.boot.ssh.autoinstall.softwareManage.domain;

/**
 * 响应DTO.
 *
 * @author yuhq
 * @since 1.0.0
 */
public class ResultDto<T> {
	private static final String PAGE_CLASS = "com.github.pagehelper.Page";
	private static final String TOTAL_FIELD = "total";
	private int code;
	private long total;
	private String message;
	private String level;
	private T data;

	public ResultDto() {
    	super();
	}

	/**
	 * @description 移除page依赖。使用反射方法获取data中total作为总数
	 * @param data
	 */
	public ResultDto(T data) {
		this.data = data;
		String value = StringUtil.toString(BeanUtil.getObjectFieldValue(data, PAGE_CLASS, TOTAL_FIELD));
		if (StringUtil.isNumeric(value)) {
			this.total = Long.parseLong(value);
		}
	}

	public ResultDto(long total, T data) {
		this.total = total;
		this.data = data;
	}

	public ResultDto(int code, String message, String level) {
		this.code = code;
		this.message = message;
		this.level = level;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ResultDto [code=" + code + ", total=" + total + ", message=" + message + ", level=" + level + ", data="
				+ data + "]";
	}
}
