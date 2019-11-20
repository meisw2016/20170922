package cn.springcloud.boot.ssh.autoinstall.domain;

/**
 * 用于包装Page对象，返回符合前端约定的数据
 * @author yuhq
 *
 */
public class ResultDto<T> {
    private int code;
    private long total;
    private String message;
    private T data;

    public ResultDto() {
        super();
    }

    public ResultDto(T data) {
        super();

        this.data = data;
    }

    public ResultDto(long total, T data) {
        super();
        this.total = total;
        this.data = data;
    }
    
    public ResultDto(int code, long total, String message, T data) {
        super();
        this.code = code;
        this.total = total;
        this.data = data;
        this.message = message;
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

}
