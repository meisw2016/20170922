package cn.springcloud.meisw.jpa.util;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.springcloud.meisw.jpa.common.OutputData;

/**
 *  <p> 使用response输出JSON </p>
 *
 * @description :
 * @date : 2019/10/11 17:27
 */
public class ResponseUtils {

	private static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);
	
    /**
     * 使用response输出JSON
     *
     * @param response
     * @param result
     */
    @SuppressWarnings("rawtypes")
	public static void out(ServletResponse response, OutputData result) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(result);
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 响应内容
     * @param httpServletResponse
     * @param msg
     * @param status
     */
    @SuppressWarnings("rawtypes")
	public static void getResponse(HttpServletResponse httpServletResponse, String msg, Integer status){
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            //writer.print(JSONObject.toJSONString(new ApiResult(status,msg,null)));
            OutputData out = new OutputData().returnSuccess();
            out.setStatus(status);
            out.setMessage(msg);
            writer.print(out);
        } catch (IOException e) {
            log.error("响应报错", e.getMessage());
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }

}
