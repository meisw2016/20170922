package cn.springcloud.book.eureka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurlUtil {
	public static String[] cmds = {"curl", "-H", "Host: www.baidu.com", "-H", "Cache-Control: max-age=0", "--compressed", "https://www.chineseconverter.com/zh-cn/convert/chinese-stroke-order-tool"};
	 
    public static String execCurl(String[] cmds){
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();
 
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }
    
    public static void test1() {
    	String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"
				+ "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
				+ "总时间%{time_total}","http://www.baidu.com"};
		ProcessBuilder pb=new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br=null;
			String line=null;
			
			br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line=br.readLine())!=null){
					System.out.println("\t"+line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
//		String result = execCurl(cmds);
//		System.out.println(result);
    	
    	test1();
	}
}
