//package cn.springcloud.meisw.eureka.util;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.GeneralSecurityException;
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
//
///**
// * http请求工具类
// * <p></p>
// * @author meisw 2019年6月25日 上午9:04:18
// * @ClassName HttpUtil
// * @Description http请求工具类
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: {修改人} 2019年6月25日
// * @modify by reason:{方法名}:{原因}
// */
//public class HttpUtil {
//	
//	public static void main(String[] args) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("X-TC-Action", "DescribeInstances");
//		map.put("X-TC-Region", "ap-guangzhou");
//		map.put("X-TC-Timestamp", "1529223702");
//		map.put("X-TC-Version", "2017-03-12");
//		map.put("Authorization", "TC3-HMAC-SHA256 Credential=AKIDEXAMPLE/2018-05-30/cvm/tc3_request, SignedHeaders=content-type;host, Signature=582c400e06b5924a6f2b5d7d672d79c15b13162d9279b0855cfba6789a8edb4c");
//		
//	}
//	
//	public static HashMap<String, String> sign(Map<String, String> paramValues,
//			String secret) {
//		return sign(paramValues, null, secret);
//	}
// 
//	/**
//	 * @param paramValues
//	 * @param ignoreParamNames
//	 * @param secret
//	 * @return
//	 */
//	public static HashMap<String, String> sign(Map<String, String> paramValues,
//			List<String> ignoreParamNames, String secret) {
//		try {
//			HashMap<String, String> signMap = new HashMap<String, String>();
//			StringBuilder sb = new StringBuilder();
//			List<String> paramNames = new ArrayList<String>(paramValues.size());
//			paramNames.addAll(paramValues.keySet());
//			if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
//				for (String ignoreParamName : ignoreParamNames) {
//					paramNames.remove(ignoreParamName);
//				}
//			}
//			/**
//			 * 对参数排序(按参数名做字典序升序排列)[PHP中可使用ksort函数排序]
//			 */
//			Collections.sort(paramNames);
//			sb.append(secret);
//			for (String paramName : paramNames) {
//				sb.append(paramName).append(paramValues.get(paramName));
//			}
//			sb.append(secret);
//			byte[] md5Digest = getMD5Digest(sb.toString());
//			String sign = byte2hex(md5Digest);
//			signMap.put("appParam", sb.toString());
//			signMap.put("appSign", sign);
//			return signMap;
//		} catch (IOException e) {
//			throw new RuntimeException("加密签名计算错误", e);
//		}
//		
//	}
// 
//	public static String utf8Encoding(String value, String sourceCharsetName) {
//		try {
//			return new String(value.getBytes(sourceCharsetName), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new IllegalArgumentException(e);
//		}
//	}
// 
// 
// 
//	private static byte[] getMD5Digest(String data) throws IOException {
//		byte[] bytes = null;
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			bytes = md.digest(data.getBytes("UTF-8"));
//		} catch (GeneralSecurityException gse) {
//			throw new IOException(gse);
//		}
//		return bytes;
//	}
// 
// 
//	private static String byte2hex(byte[] bytes) {
//		StringBuilder sign = new StringBuilder();
//		for (int i = 0; i < bytes.length; i++) {
//			String hex = Integer.toHexString(bytes[i] & 0xFF);
//			if (hex.length() == 1) {
//				sign.append("0");
//			}
//			//sign.append(hex.toUpperCase());
//			sign.append(hex.toLowerCase());
//		}
//		return sign.toString();
//	}
//	public static String getSign(Map<String, String> params,String secret)
//	{
//		String ret="";
//		StringBuilder sb = new StringBuilder();
//		Set<String> keyset=params.keySet();
//		TreeSet<String> sortSet=new TreeSet<String>();
//		sortSet.addAll(keyset);
//		Iterator<String> it=sortSet.iterator();
//		sb.append(secret);
//		while(it.hasNext())
//		{
//			String key=it.next();
//			String value=params.get(key);
//			sb.append(key).append(value);
//		}
//		sb.append(secret);
//		byte[] md5Digest;
//		try {
//			md5Digest = getMD5Digest(sb.toString());
//			ret = byte2hex(md5Digest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}
//}
