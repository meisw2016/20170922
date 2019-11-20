package cn.springcloud.boot.ssh.autoinstall.softwareManage.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * String util
 * 
 * @author tangxb
 * @since 2.1.1
 */
public class StringUtil {
    
    public static final char CHAR_ZERROR = '0';
    
    private static final String CHAR_SPACE = " ";
    
    /**
     * 方向：左
     */
    public final static int LEFT = 0;

    /**
     * 方向：居中
     */
    public final static int CENTER = 1;

    /**
     * 方向：右
     */
    public final static int RIGHT = 2;
    
    private static Pattern UNDERLINE_TO_CAMELHUMP_PATTERN = Pattern.compile("_[a-z]");
    
    private StringUtil() {}
    
    /**
     * 判断字符串是否为空 
     * @param cs
     * @return 为空返回true；
     */
    public final static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }
    
    /**
     * 判断字符串是否不为空
     * @param cs
     * @return
     */
    public final static boolean isNotEmpty(final CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }
    
    /**
     * 判断字符串是否为空 或者为" "
     * @param cs
     * @return 为空 或者为" "返回true；
     */
    public final static boolean isBlank(final CharSequence cs) {
        return StringUtils.isBlank(cs);
    }
    
    /**
     * 判断字符串是否不为空 或者不为" "
     * @param cs
     * @return 不为空 或者不为" "返回true；
     */
    public final static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }
    
    /**
     * 判断字符串是否为空或者为null字符串
     * @param cs
     * @return
     */
    public final static boolean isStrEmpty(final CharSequence cs) {
        return isEmpty(cs) || "null".equals(cs);
    }
    
    /**
     * 判断字符串是否不为空或者不为null字符串
     * @param cs
     * @return
     */
    public final static boolean isStrNotEmpty(final CharSequence cs) {
        return isNotEmpty(cs) && !"null".equals(cs);
    }
    
    /**
     * 安全的截取字符串
     * <pre>
     * StringUtil.substring(null, 1)      = ""
     * StringUtil.substring("bob", -1)    = ""
     * StringUtil.substring("bob", 4)     = "bob"
     * StringUtil.substring("bob", 2)     = "bo"
     * </pre>
     * @param str 待截取的字符串
     * @param len 截取的长度
     * @return
     */
    public final static String substring(String str, int len) {
        return substring(str, 0, len);
    }
    
    /**
     * 安全的截取字符串
     * @param str
     * @param startIdx
     * @param len
     * @return
     */
    public final static String substring(String str, int startIdx, int len) {
        if (len <= 0 || startIdx < 0 || isEmpty(str))
            return "";
        if (len > str.length() || len - startIdx > str.length()) {
            return str;
        }
        return str.substring(startIdx, startIdx + len);
    }
    
    /**
     * 安全的分割字符串，当传入的str为空时，返回长度为0的字符串
     * @param str
     * @param regex
     * @return
     */
    public final static String[] split(String str, String regex) {
        if (isEmpty(str))
            return new String[0];
        return str.split(regex, -1);
    }
    
    /**
     * 将对象转化为字符串， 对象为空返回"", 不为空返回对象的toString方法值
     * @param object
     * @return
     */
    public final static String replaceObjNull(Object object) {
        return Objects.nonNull(object) ? object.toString() : "";
    }
    
    public final static String replaceNullByObj(Object object) {
        return replaceObjNull(object);
    }
    
    /**
     * 将字符串数组合并成一个以 str 分隔的字符串, 如果数组中存在null，则该字段不返回
     * @param strArray
     * @param str
     * @return
     */
    public final static String concat(String[] strArray, String str) {
        return concat(strArray, str, false);
    }
    
    /**
     * 将字符串数组合并成一个以 str 分隔的字符串, 如果数组中存在null, 根据isReturnNull参数判断是否返回
     * @param strArray
     * @param str
     * @param isReturnNull
     * @return
     */
    public final static String concat(String[] strArray, String str, boolean isReturnNull) {
        if (Objects.nonNull(strArray) && strArray.length > 0) {
            str = replaceObjNull(str);
            StringBuffer sb = new StringBuffer(strArray.length * 8);
            for (int i = 0; i < strArray.length; i++) {
                if (isEmpty(strArray[i])) {
                    if (isReturnNull) {
                        sb.append(replaceObjNull(strArray[i])).append(str);
                    }
                } else {
                    sb.append(strArray[i]).append(str);
                }
            }
            String returnStr = sb.toString();
            return substring(returnStr, returnStr.length() - 1);
        }
        return "";
    }
    
    /**
     * 将字符串集合合并成一个以 str 分隔的字符串, 如果数组中存在null，则该字段不返回
     * @param collections
     * @param str
     * @return
     */
    public final static String concat(Collection<String> collections, String str) {
        return concat(collections, str, false);
    }
    
    /**
     * 将字符串集合合并成一个以 str 分隔的字符串, 如果数组中存在null, 根据isReturnNull参数判断是否返回
     * @param collections
     * @param str
     * @param isReturnNull
     * @return
     */
    public final static String concat(Collection<String> collections, String str, boolean isReturnNull) {
        if (Objects.isNull(collections)) return "";
        String[] strArray = collections.toArray(new String[collections.size()]);
        return concat(strArray, str, isReturnNull);
    }
    
    /**
     * 以指定的字符和长度生成一个该字符的指定长度的字符串。
     * @param c 指定的字符
     * @param length 指定长度
     * @return
     */
    public final static String fill(char c, int length) {
        return fill(Character.toString(c), length);
    }
    
    /**
     * 以指定的字符和长度生成一个该字符的指定长度的字符串。
     * @param cs 指定的字符
     * @param length 指定长度
     * @return
     */
    public final static String fill(CharSequence cs, int length) {
        return fill(cs, length, true);
    }
    
    /**
     * 以指定的字符和长度生成一个该字符的指定长度的字符串。
     * @param cs 指定的字符
     * @param length 指定长度
     * @param isCutout 超过长度后是否截取 true截取false不截取
     * @return
     */
    public final static String fill(CharSequence cs, int length, boolean isCutout) {
        if (length < 0) return "";
        int actLength = (int) Math.ceil((double)length / cs.length());
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < actLength; i++) {
            sb.append(cs);
        }
        return isCutout ? substring(sb.toString(), length) : sb.toString();
    }
    
    /**
     * 
     * 填充字符串,源串右对齐
     * @param source 源串
     * @param cIn 填充字符
     * @param len 所需长度
     * @return 填充后的字符串
     */
    public final static String fill(String source, char c, int len) {
        return fill(source, c, len, RIGHT);
    }

    /**
     * <p>
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * </p>
     * 自定义填充字符 <BR>
     * @param source 源串
     * @param c 填充字符
     * @param len 所需长度
     * @param align 对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public final static String fill(String source, char c, int len, int align) {
        return fill(source, Character.toString(c), len, align);
    }

    /**
     * 
     * 填充字符串, 源字符串右对齐，填充字符为空格
     * @param source 源串
     * @param len 所需长度
     * @return 填充后的字符串
     */
    public final static String fill(String source, int len) {
        return fill(source, len, RIGHT);
    }


    /**
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * 
     * @param source 源串
     * @param len 所需长度
     * @param align 对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public final static String fill(String source, int len, int align) {
        return fill(source, CHAR_SPACE, len, align);
    }

    /**
     * <p>
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * </p>
     * 自定义填充字符串 <BR>
     * 
     * @param source 源串，如果为空则作任何改变
     * @param str 填充字符串，如果为空则返回源字符串
     * @param len 所需长度
     * @param align 对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public final static String fill(String source, String str, int length, int align) {

        if (source == null || str == null) {
            return source;
        }
        if (source.length() > length) {
            return source;
        }
        
        StringBuffer buffer = new StringBuffer();
        String fillStr = fill(str, length - source.getBytes().length);
        switch (align) {
            case LEFT : buffer.append(fillStr).append(source); break;
            case CENTER : 
                int centerIdx = source.length() / 2;
                buffer.append(substring(source, centerIdx)).append(fillStr).append(substring(source, centerIdx, source.length() - centerIdx));
                break;
            case RIGHT : buffer.append(source).append(fillStr); break;
            default : buffer.append(source); break;
        }
        return buffer.toString();
    }
    
    
    /**
     * 判断两字符串是否相等，不忽略大小判断，如果str1和str2均为null，返回true
     * @param str1
     * @param str2
     * @return
     */
    public final static boolean equals(String str1, String str2) {
        return equals(str1, str2, false);
    }
    
    /**
     * 判断两字符串是否相等，根据传入的ignoreCase来判断是否忽略大小写判断，如果str1和str2均为null，返回true
     * @param str1
     * @param str2
     * @param ignoreCase true:忽略 false:不忽略
     * @return
     */
    public final static boolean equals(String str1, String str2, boolean ignoreCase) {
        if (str1 == null && str2 == null)
            return true;
        if (str1 != null) {
            return ignoreCase ? str1.equalsIgnoreCase(str2) : str1.equals(str2);
        }
        return false;
    }
    
    /**
     * 判断字符串数组是否存在传入的字符串
     * @param strArray
     * @param str
     * @return
     */
    public final static boolean contains(String[] strArray, String str) {
        return contains(strArray, str, false);
    }
    
    /**
     * 判断字符串数组是否存在传入的字符串, 根据ignoreCase参数判断是否忽略大小判断
     * @param strArray
     * @param str
     * @param ignoreCase true:忽略 false:不忽略
     * @return
     */
    public final static boolean contains(String[] strArray, String str, boolean ignoreCase) {
        if (Objects.nonNull(strArray) && strArray.length > 0) {
            return Arrays.stream(strArray).anyMatch(s -> equals(s, str, ignoreCase));
        } else {
            return Objects.isNull(str);
        }
    }
    
    /**
     * 判断字符串集合是否存在传入的字符串
     * @param collections
     * @param str
     * @return
     */
    public final static boolean contains(Collection<String> collections, String str) {
        return collections.contains(str);
    }
    
    /**
     * 判断字符串集合是否存在传入的字符串, 根据ignoreCase参数判断是否忽略大小判断
     * @param collections
     * @param str
     * @param ignoreCase true:忽略 false:不忽略
     * @return
     */
    public final static boolean contains(Collection<String> collections, String str, boolean ignoreCase) {
        if (Objects.isNull(collections)) return false;
        if (ignoreCase) {
            String[] strArray = collections.toArray(new String[collections.size()]);
            return contains(strArray, str, true);
        } else {
            return collections.contains(str);
        }
    }
    
    /**
     * 判断字符是否为双字节字符，如中文
     * 
     * @param c
     * @return
     */
    public final static boolean isDoubleByte(char c) {
        return !((c >>> 8) == 0);
    }
    
    /**
     * 功能描述：判断输入的字符串是否为纯汉字
     * 
     * 
     * @param str
     *            传入的字符窜
     * @return 如果是纯汉字返回true,否则返回false
     */
    public final static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }
    
    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    /**
     * 功能描述：返回指定字节长度的字符串，中文默认占2位字符
     * @param str String 字符串
     * @param length int 指定长度
     * @return String 返回的字符串
     */
    public final static String toFixLength(String str, int length) {
        if (str == null || length <= 0) 
            return "";
        if (str.getBytes().length <= length) 
            return str;
        
        StringBuffer buff = new StringBuffer();

        int index = 0;
        char c;
        length -= 3;
        while (length > 0) {
            c = str.charAt(index);
            length = length - (c < 128 ? 1 : 2);
            buff.append(c);
            index++;
        }
        buff.append("...");
        return buff.toString();
    }
    
    /**
     * 判断是不是合法字符 c 要判断的字符
     * @param str
     * @return
     */
    public final static boolean isLetter(String str) {
        if (str == null || str.length() < 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\w\\.-_]*");
        return pattern.matcher(str).matches();
    }
    
    /**
     * 判断输入的字符串是否符合Email样式.
     * @param email
     * @return
     */
    public final static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }
    
    /**
     * 安全的去除首尾空格
     * @param str
     * @return
     */
    public final static String trim(String str) {
        return (isEmpty(str)) ? str : str.trim();
    }
    
    /**
     * 转换大写
     * @param str
     * @return
     */
    public final static String upperCase(final String str) {
        return StringUtils.upperCase(str);
    }
    
    /**
     * 转换小写
     * @param str
     * @return
     */
    public final static String lowerCase(final String str) {
        return StringUtils.lowerCase(str);
    }
    
    /**
     * 将byte[]数组转换成大写字符串,十六进制：01中文(30 31 D6 D0)转换后为：3031D6D0
     * @param data 字节数组
     * @return 转换后字符串
     */
    public final static String byte2String(byte[] data) {
        return byte2String(data, false);
    }

    /**
     * 将byte[]数组转换成字符串：显示信息表示不变
     * @param data 字节数组
     * @param lowercase 是否转换成小写字符串
     * @return 转换后字符串
     */
    public final static String byte2String(byte[] data, boolean lowerCase) {
        if (data == null) {
            return null;
        }
        
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.length; i++){
            String tmpStr = Integer.toHexString(data[i] & 0xff);
            tmpStr = StringUtils.leftPad(tmpStr, 2, CHAR_ZERROR);
            buffer.append(tmpStr);
        }
        
        return lowerCase ? buffer.toString() : upperCase(buffer.toString());
        
    }
    
    /**
     * 将16进制字符串(带0x和转义字符)转化为字节数组：将十六进制数转化为byte表示<br>
     * 消除字节数组转义字符和0x的字节 如:0x610x620x63\\r\\naa,转换成:97 98 99 13 10 97 97
     * @param strData 源字符串
     * @return 字节数组
     * @exception NumberFormatException 16进制表示不合法
     */
    public final static byte[] escString2Byte(String strData) throws NumberFormatException {
        byte[] srcData = strData.getBytes();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int dataLength = srcData.length;
        for (int i = 0; i < dataLength; i++) {
            if (srcData[i] == CHAR_ZERROR && dataLength >= i + 4 && (srcData[i + 1] == 'x' || srcData[i + 1] == 'X')) {
                baos.write((byte) Integer.parseInt(new String(srcData, i + 2, 2), 16));
                i += 3;
            } else if (srcData[i] == '\\') {
                if ((i + 1) < dataLength) {
                    switch (srcData[i + 1]) {
                        case 'r' : baos.write('\r'); break;
                        case 't' : baos.write('\t'); break;
                        case 'n' : baos.write('\n'); break;
                        case 'b' : baos.write('\b'); break;
                        case 'f' : baos.write('\f'); break;
                        case '\'' : baos.write('\''); break;
                        case '\"' : baos.write('\"'); break;
                        case '\\' : baos.write('\\'); break;
                        case '0' : baos.write('\0'); break;
                        default : throw new IllegalArgumentException("字符串不合法：["+ strData +"]");
                    }
                    i++;
                } else {
                    throw new IllegalArgumentException("字符串不合法：["+ strData +"]");
                }
            } else {
                baos.write(srcData[i]);
            }
        }
        return baos.toByteArray();
    }

    /**
     * 获取异常的所有信息
     * @param e
     * @return
     * @throws IOException
     */
    public final static String exception2String(Exception e) {
        if (e == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(os));
        byte[] msg = os.toByteArray();
        try {
            os.close();
        } catch (IOException e1) {}
        return new String(msg);
    }

    /**
     * 获得指定长度的由指定字符重复组成的字符串
     * 
     * @param c 填充字符
     * @param length 所需字符串长度
     * @return 填充指定字符后的字符串
     */
    public final static String getBlankStr(char c, int length) {
        return getBlankStr(Character.toString(c), length);
    }

    /**
     * 获得指定长度的由空格重复组成的字符串
     * 
     * @param length 所需字符串长度
     * @return 填充空格后的字符串
     */
    public final static String getBlankStr(int length) {
        return getBlankStr(CHAR_SPACE, length);
    }

    /**
     * 获得指定长度的由指定字符串重复组成的字符串 <BR>
     * 若填充字符串为多字节的话，结果字符串长度可能比需要的要 <b>长 </B>
     * 
     * @param str 填充字符串，如果为null或者为空则默认为空格
     * @param length 所需字符串长度
     * @return 填充指定字符串后的字符串
     */
    public final static String getBlankStr(String str, int length) {
        return getBlankStr(str, length, false);
    }
    
    /**
     * 获得指定长度的由指定字符串重复组成的字符串 <BR>
     * 若填充字符串为多字节的话，结果字符串长度可能比需要的要 <b>长 </B>
     * 
     * @param str 填充字符串，如果为null或者为空则默认为空格
     * @param length 所需字符串长度
     * @param isCutout 超出长度后是否需要截取 true 截取 false不截取
     * @return 填充指定字符串后的字符串
     */
    public final static String getBlankStr(String str, int length, boolean isCutout) {
        if (isEmpty(str)) {
            str = CHAR_SPACE;
        }
        return fill(str, length, isCutout);
    }

    /**
     * 获取对象的字符串
     * 
     * @param obj 指定对象
     * @return 对象对应的字符串
     */
    public final static String toString(Object obj) {
        return Objects.isNull(obj) ? (String) obj : obj.getClass().isArray() ? obj instanceof byte[] ? new String((byte[]) obj) : Arrays.toString((Object[]) obj) : obj.toString();
    }
    
    /**
     * 获取UUID
     * @return
     */
    public final static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    
    /**
     * 替换\n\t\r
     * @param str
     * @return
     */
    public final static String replaceNRT(String str) {
        return isNotEmpty(str) ? Pattern.compile("\n*|\t|\r").matcher(str.replaceAll("  ", "")).replaceAll("") : null;
    }
    
    /**
     * 驼峰转下划线 是在一个字符串的每个大写字母前面加一个_ ，字符串的首字母除外 然后把整个串以小写输出 例如：cusId-->cus_id
     * @param str
     * @return
     */
    public final static String hump2Underline(String str) {
        if (isEmpty(str)) return str;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                buffer.append("_");
            } 
            buffer.append(Character.valueOf(str.charAt(i)));
        }

        String rtnStr = lowerCase(buffer.toString());
        return rtnStr.startsWith("_") ? rtnStr.substring(1) : rtnStr;
    }
    
    /**
     * 将"_"删除，并将"_"之后的字母改为大写，如：cus_id-->cusId (首位是"_"乎略)
     * @param str
     * @return
     */
    public final static String underline2Hump(String str) {
        if (isEmpty(str)) return str;
        StringBuffer tmpStr = new StringBuffer("");
        tmpStr.append(str.substring(0,1));
        str = str.substring(1);
        while(str.indexOf("_")>0){
            tmpStr.append(str.substring(0,str.indexOf("_")));
            //判断截取后最后一位是否是"_"
            if(str.substring(str.indexOf("_")).equals("_"))
                str = "";
            else
                str = str.substring(str.indexOf("_") + 1, str.indexOf("_") + 2).toUpperCase() + str.substring(str.indexOf("_") + 2);
        }
        tmpStr.append(str);

        return tmpStr.toString();
    }
    
    /**
     * 返回两位数据字串 *
     * 
     * @param sz int
     * @return String
     */
    public final static String rightFillZero(int sz) {
        return (sz < 10 ? ("0" + String.valueOf(sz)) : String.valueOf(sz));
    }
    
    /**
     * 字符串转码
     * @param str 字符串
     * @param srcCharset 原字符集
     * @param targetCharset 目标字符集
     * @return
     */
    public final static String stringEncode(String str, String srcCharset, String targetCharset) {
        if (isEmpty(str)) return str;
        try {
            return new String(str.getBytes(srcCharset), targetCharset);
        } catch (Exception e) {}
        return str;
    }
    
    /**
     * 字符串转换为Map对象
     * @param str 待转换字符串，eg. aaa=111,bbb=222
     * @return Map对象
     */
    public final static Map<String, String> string2Map(String mapStr) {
        Map<String, String> map = new HashMap<String, String>();
        String[] entrys = split(mapStr, ",");
        for (String entry : entrys) {
            String[] strs = split(trim(entry), "=");
            if (strs.length > 1) {
                map.put(strs[0].trim(), strs[1].trim());
            }
        }
        return map;
    }

    /**
     * 将特殊字符转义
     * @param str
     * @return
     */
    public final static byte[] escapeStringToCharArray(byte[] str) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for (int i = 0; i < str.length; i++) {
            switch (str[i]) {
                case '\n' : bout.write('\\'); bout.write('n'); break;
                case '\t' : bout.write('\\'); bout.write('t'); break;
                case '\r' : bout.write('\\'); bout.write('r'); break;
                case '\f' : bout.write('\\'); bout.write('f'); break;
                case '\0' : bout.write('\\'); bout.write('0'); break;
                case '"' : bout.write('\\'); bout.write('"'); break;
                case '\b' : bout.write('\\'); bout.write('b'); break;
                case '\\' : bout.write('\\'); bout.write('\\'); break;
                default : bout.write(str[i]); break;
            }
        }
        return bout.toByteArray();
    }
    
    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    /**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = UNDERLINE_TO_CAMELHUMP_PATTERN.matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }
    
    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }
}
