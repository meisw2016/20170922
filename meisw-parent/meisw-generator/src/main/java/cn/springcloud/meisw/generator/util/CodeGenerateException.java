package cn.springcloud.meisw.generator.util;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午2:38:35
 * @ClassName CodeGenerateException
 * @Description 自动生成代码异常
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
public class CodeGenerateException extends RuntimeException {
    private static final long serialVersionUID = 42L;
    public CodeGenerateException() {
        super();
    }

    public CodeGenerateException(String msg) {
        super(msg);
    }

    public CodeGenerateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CodeGenerateException(Throwable cause) {
        super(cause);
    }

    public CodeGenerateException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
