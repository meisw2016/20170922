package cn.springcloud.meisw.jpa.common;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;

/**
 * Clob转换
 * <p></p>
 * @author meisw 2019年11月20日 上午9:33:17
 * @ClassName ClobConverter
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月20日
 * @modify by reason:{方法名}:{原因}
 */
public class ClobConverter {
	/**
	 * 将String转成Clob,静态方法
	 * @author meisw 2019年11月20日 上午9:34:54
	 * @Method: string2Clob 
	 * @Description: TODO
	 * @param str	字段
	 * @return 	clob对象,如果出现错误,返回NULL
	 * @throws
	 */
	public static Clob string2Clob(String str) {
		if(str == null) {
			return null;
		}else {
			try {
				java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
				return c;
			}catch(Exception e) {
				return null;
			}
		}
	}
	
	/**
	 * 将Clob转成String,静态方法
	 * @author meisw 2019年11月20日 上午9:39:57
	 * @Method: clob2String 
	 * @Description: TODO
	 * @param clob	字段
	 * @return 	内容字符串，如果出现错误，返回NULL
	 * @throws
	 */
	public static String clob2String(Clob clob) {
		if(clob == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];//每次获取60k
			int i = 0;
			while((i = clobStream.read(b))!= -1) {
				sb.append(b,0,i);
			}
		}catch(Exception e) {
			sb = null;
		}finally {
			if(clobStream != null) {
				try {
					clobStream.close();
				} catch (IOException e) {
				}
			}
		}
		if(sb == null) {
			return null;
		}else {
			return sb.toString();
		}
	}
}
