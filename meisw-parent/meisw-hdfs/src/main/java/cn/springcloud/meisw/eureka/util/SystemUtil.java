//package cn.springcloud.meisw.eureka.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Field;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sun.jna.Platform;
//public class SystemUtil {
//	
//	private static final Logger log = LoggerFactory.getLogger(SystemUtil.class);
//	
//	public static String getProcessId(Process process) {
//		/*String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
//		        + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
//		Process proc = Runtime.getRuntime().exec(procCmd);
//		proc.getOutputStream();*/
//		 long pid = -1;
//         Field field = null;
//         if (Platform.isWindows()) {
//             try {
//                 field = process.getClass().getDeclaredField("handle");
//                 field.setAccessible(true);
//                 pid = Kernel32.INSTANCE.GetProcessId((Long) field.get(process));
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         } else if (Platform.isLinux() || Platform.isAIX()) {
//             try {
//                 Class<?> clazz = Class.forName("java.lang.UNIXProcess");
//                 field = clazz.getDeclaredField("pid");
//                 field.setAccessible(true);
//                 pid = (Integer) field.get(process);
//             } catch (Throwable e) {
//                 e.printStackTrace();
//             }
//         }
//         return String.valueOf(pid);
//	}
//	
//	/**
//     * 关闭Linux进程
//     * @param pid 进程的PID
//     */
//    public static boolean killProcessByPid(String pid){
//        if (StringUtils.isEmpty(pid) || "-1".equals(pid)) {
//            throw new RuntimeException("Pid ==" + pid);
//        }
//        Process process = null;
//        BufferedReader reader =null;
//        String command = "";
//        boolean result = false;
//        if (Platform.isWindows()) {
//            command = "cmd.exe /c taskkill /PID " + pid + " /F /T ";
//        } else if (Platform.isLinux() || Platform.isAIX()) {
//            command = "kill -9 " + pid;
//        }
//        try{
//            //杀掉进程
//            process = Runtime.getRuntime().exec(command);
//            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
//            String line = null;
//            while((line = reader.readLine())!=null){
//                log.info("kill PID return info -----> "+line);
//            }
//            result = true;
//        }catch(Exception e){
//            log.info("杀进程出错：", e);
//            result = false;
//        }finally{
//            if(process!=null){
//                process.destroy();
//            }
//            if(reader!=null){
//                try {
//                    reader.close();
//                } catch (IOException e) {
//
//                }
//            }
//        }
//        return result;
//    }
//    
//    public static void main(String[] args) {
//    	boolean flag = killProcessByPid("4");
//    	log.info("flag :{}",flag);
//    }
//}
