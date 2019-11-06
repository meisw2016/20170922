package cn.springcloud.meisw.jpa.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeiswException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MeiswException.class);
	
	public MeiswException(String message) {
		super(message);
	}
	
	public MeiswException(String message, Exception exception) {
		this(message + exception.getMessage());
		log.error(message, exception);
	}
}
