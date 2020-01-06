//package cn.springcloud.boot.eureka;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.management.ManagementFactory;
//import java.lang.management.RuntimeMXBean;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.junit.Test;
//
//import cn.springcloud.meisw.eureka.util.ProcessUtil;
//
//public class ProcessUtilTest {
//	
//	/**
//	 * 获取当前系统的所有PidName
//	 * @throws Exception
//	 */
//	@Test
//	public void test() throws Exception {
//		Set<String> set = ProcessUtil.getCurrOsAllPidNameSet();
//		for (String str : set) {
//			System.out.print("[pidName=" + str + "] ");
//			// String pid = ProcessUtil.getPidNameByPid(st)
//			String cpu = ProcessUtil.getCPUByPID(str);
//			System.out.println(" cpu=" + cpu);
//		}
//		
//	}
//	
//	@Test
//	public void test2() {
//		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
//		String name = runtime.getName();
//		System.out.println("当前进程的标识为：" + name);
//		int index = name.indexOf("@");
//		if (index != -1) {
//			int pid = Integer.parseInt(name.substring(0, index));
//			System.out.println("当前进程的PID为：" + pid);
//		}
//		try {
//			// 这里休息60秒，是为了在windows管理器查看该应用程序的进程PID
//			Thread.sleep(60 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void test3() throws IOException {
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		Process p = Runtime.getRuntime().exec("tasklist /v");
//		is = p.getInputStream();
//		ir = new InputStreamReader(is);
//		br = new BufferedReader(ir);
//		String result = "";
//		while ((line = br.readLine()) != null) {
//			result += line;
//		}
//		System.out.println(result);
//	}
//	
//	@Test
//	public void test4() {
//		int count = 0;
//		BufferedReader br = null;
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		
//		try {
//			Process proc = Runtime.getRuntime().exec("tasklist");
//			br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
//			// System.out.println("正在运行的进程信息:");
//			String line = null;
//			
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//				
//				map.put(++count, line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null)
//					br.close();
//				System.out.println("当前进程数：" + count);
//				System.out.println("当前进程：" + map.size());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}
//	
//	@Test
//	public void test6(){
//		Map<Integer,String> map = ProcessUtil.getTaskInfo();
//		for(Map.Entry<Integer, String> entry : map.entrySet()){
//		    Integer mapKey = entry.getKey();
//		    String mapValue = entry.getValue();
//		    System.out.println(mapKey+":"+mapValue);
//		}
//	}
//}
