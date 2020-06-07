package cn.springcloud.book.eureka.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;



public class BrowerUtil {
	/** 浏览器类型 */
    public static final byte BROWSER_IE         = 1;
    public static final byte BROWSER_FIREFOX    = 2;
    public static final byte BROWSER_MOZILLA    = 3;
    public static final byte BROWSER_OPERA      = 4;
    public static final byte BROWSER_CHROME     = 5;
    public static final byte BROWSER_ANDROID    = 6;
    public static final byte BROWSER_SAFARI     = 7;
    public static final byte BROWSER_IOS        = 8;
    public static final byte BROWSER_EDGE       = 9;
    public static final byte BROWSER_UNKNOWN    = 0;
    
    /**
     * 获得用户浏览器ua
     * 
     * @param request
     * @return byte 浏览器类型
     */
    public static byte getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        ua = ua.toLowerCase();
        if (ua.indexOf("firefox") > 0) {
            return BROWSER_FIREFOX;
        }
        if (ua.indexOf("opera") > 0) {
            return BROWSER_OPERA;
        }
        if (ua.indexOf("msie") > 0 || ua.indexOf("trident") > 0) {
            return BROWSER_IE;
        }
        if (ua.indexOf("chrome") > 0) {
            return BROWSER_CHROME;
        }
        if (ua.indexOf("android") > 0) {
            return BROWSER_ANDROID;
        }
        if (ua.indexOf("ios") > 0) {
            return BROWSER_IOS;
        }
        if (ua.indexOf("edge") > 0) {
            return BROWSER_EDGE;
        }
        if (ua.indexOf("mac os") > 0 || ua.indexOf("safari") > 0) {
            return BROWSER_SAFARI;
        }
        return BROWSER_UNKNOWN;
    }
    
    /**
     * 处理中文乱码的问题
     * 
     * @param request
     * @param pFileName
     * @return String
     * @throws UnsupportedEncodingException
     */
    // IE浏览器，采用URLEncoder编码
    // Safari浏览器，采用ISO编码的中文输出
    // Chrome浏览器，采用Base64编码或ISO编码的中文输出
    // FireFox浏览器，采用Base64或filename*或ISO编码的中文输出
    public static String encodeChineseFileName(HttpServletRequest request, String pFileName)
            throws UnsupportedEncodingException {
        boolean allIsAscii = true;
        if (pFileName != null) {
            for (int i = 0; i < pFileName.length(); i++) {
                if (pFileName.charAt(i) > 255) {
                    allIsAscii = false;
                    break;
                }
            }
        }
        if (allIsAscii) {
            return pFileName;
        } else {
            String filename = "";
            byte userAgent = getUserAgent(request);
            if (userAgent != BROWSER_UNKNOWN && !StringUtils.isBlank(pFileName)) {
                if (userAgent == BROWSER_FIREFOX) {// firefox
                    String encodeFileName = new String(Base64.encodeBase64(pFileName.getBytes("UTF-8")));
                    filename = "=?UTF-8?B?" + encodeFileName + "?=";
                } else if (userAgent == BROWSER_CHROME) {// chrome
                    filename = new String(pFileName.getBytes(), "ISO8859-1");
                } else if (userAgent == BROWSER_SAFARI) {// Safari
                    filename = new String(pFileName.getBytes(), "ISO8859-1");
                } else {// ie
                    filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                    filename = org.apache.commons.lang.StringUtils.replace(filename, "+", "%20");// 替换空格
                }
            } else {
                filename = pFileName;
            }
 
            return filename;
        }
    }
}
