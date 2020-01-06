//package cn.springcloud.meisw.eureka.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.management.ManagementFactory;
//import java.lang.management.RuntimeMXBean;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.lang.StringUtils;
//
//public class ProcessUtil {
//	
//	// 通过获取当前运行主机的pidName，截取获得他的pid
//	public static String getCurrentPid() throws Exception {
//		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
//		String pidName = runtime.getName();// 5296@dell-PC
//		String pid = pidName.substring(0, pidName.indexOf("@"));
//		return pid;
//	}
//	
//	// 通过Pid获取PidName
//	public static String getPidNameByPid(String pid) throws Exception {
//		String pidName = null;
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String[] array = (String[])null;
//		try {
//			Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV /FI \"PID EQ " + pid + "\"");
//			is = p.getInputStream(); // "javaw.exe","3856","Console","1","72,292
//			                         // K"从这个进程中获取对应的PidName
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			while ((line = br.readLine()) != null) {
//				if (line.indexOf(pid) != -1) {
//					array = line.split(",");
//					line = array[0].replaceAll("\"", "");
//					line = line.replaceAll(".exe", "");// 考虑pidName后缀为exe或者EXE
//					line = line.replaceAll(".exe".toUpperCase(), "");
//					pidName = line;
//				}
//			}
//		} catch (IOException localIOException) {
//			throw new Exception("获取进程名称出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		return pidName;
//	}
//	
//	// 根据Pid获取当前进程的CPU
//	public static String getCPUByPID(String pid) throws Exception {
//		if (pid == null) {
//			return null;
//		}
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String[] array = (String[])null;
//		try {
//			Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV /FI \"PID EQ " + pid + "\"");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			while ((line = br.readLine()) != null) {
//				if (!"".equals(line)) {
//					array = line.split("\",\"");
//					line = array[3].replaceAll("\"", "");
//					return line;
//				}
//			}
//		} catch (Exception localException) {
//			throw new Exception("获取进程CPU信息出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		if (br != null) {
//			br.close();
//		}
//		if (ir != null) {
//			ir.close();
//		}
//		if (is != null) {
//			is.close();
//		}
//		return null;
//	}
//	
//	// 根据Pid获取当前进程的memory
//	public static String getMemByPID(String pid) throws Exception {
//		if (pid == null) {
//			return null;
//		}
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String[] array = (String[])null;
//		try {
//			Process p = Runtime.getRuntime().exec(" TASKLIST /NH /FO CSV /FI \"PID EQ " + pid + "\"");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			while ((line = br.readLine()) != null) {
//				if (!"".equals(line)) {
//					array = line.split("\",\"");
//					line = array[4].replaceAll("\"", "");
//					return line;
//				}
//			}
//		} catch (IOException localIOException) {
//			throw new Exception("获取进程内存信息出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		if (br != null) {
//			br.close();
//		}
//		if (ir != null) {
//			ir.close();
//		}
//		if (is != null) {
//			is.close();
//		}
//		return null;
//	}
//	
//	// 根据Pid将进程干掉
//	public static void killProcessByPid(String pid) throws Exception {
//		Runtime.getRuntime().exec("taskkill /F /PID " + pid);
//	}
//	
//	// 根据PidName将进程干掉
//	public static void killProcessByPidName(String pidName) throws Exception {
//		Runtime.getRuntime().exec("taskkill /F /IM " + pidName + ".exe");
//	}
//	
//	// 根据PidName获取当前的Pid的list集合
//	public static List<String> getPIDListByPidName(String pidName) throws Exception {
//		List<String> pidList = new ArrayList<>();
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String[] array = (String[])null;
//		try {
//			String imageName = pidName + ".exe";
//			Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV /FI \"IMAGENAME EQ " + imageName + "\"");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			while ((line = br.readLine()) != null) {
//				if (line.indexOf(imageName) != -1) {
//					array = line.split(",");
//					line = array[1].replaceAll("\"", "");
//					pidList.add(line);
//				}
//			}
//		} catch (IOException localIOException) {
//			throw new Exception("获取进程ID出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		return pidList;
//	}
//	
//	// 获取当前系统的所有的PidName
//	public static Set<String> getCurrOsAllPidNameSet() throws Exception {
//		Set<String> pidNameSet = new HashSet<>();
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String[] array = (String[])null;
//		try {
//			Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			while ((line = br.readLine()) != null) {
//				array = line.split(",");
//				line = array[0].replaceAll("\"", "");
//				line = line.replaceAll(".exe", "");
//				line = line.replaceAll(".exe".toUpperCase(), "");
//				if (StringUtils.isNotBlank(line)) {
//					pidNameSet.add(line);
//				}
//			}
//		} catch (IOException localIOException) {
//			throw new Exception("获取系统所有进程名出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		return pidNameSet;
//	}
//	
//	// 判断当前pid是否退出，判断根据pid查询的内存是否为空来决定
//	public static boolean isExitPid(String pid) throws Exception {
//		return getMemByPID(pid) != null;
//	}
//	
//	// 对启动路径和运行参数的拼接
//	public static String getCommandFormatStr(String proPath) {// 这样的思路就可以控制俩种参数的输入
//		return getCommandFormatStr(proPath, null);
//	}
//	
//	public static String getCommandFormatStr(String proPath, String runArgs) {
//		StringBuffer command = new StringBuffer();
//		command.append("\"");
//		command.append(proPath);
//		command.append("\"");
//		if (StringUtils.isNotBlank(runArgs)) {
//			command.append(" ").append(runArgs);
//		}
//		return command.toString();
//	}
//	
//	// 执行完相应的命令行就退出cmd
//	private static String getCommandByCmd(String cmdStr) {
//		StringBuffer command = new StringBuffer();
//		command.append("cmd /C ");
//		command.append(cmdStr);
//		return command.toString();
//	}
//	
//	// 获取当前环境的Java_Home
//	private static String getJavaHome() throws Exception {
//		String javaHome = System.getenv("JAVA_HOME");
//		javaHome = javaHome == null ? System.getProperty("java.home") : javaHome;
//		return javaHome;
//	}
//	
//	// 退出Java_Home
//	public static boolean existJavaHome() throws Exception {// 对于这个方法在产品阶段用于判断是否具有JAVA_HOME
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		try {
//			Process p = Runtime.getRuntime().exec("cmd   /c   echo   %JAVA_HOME% ");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			if ((line = br.readLine()) != null) {
//				if (line.indexOf("%JAVA_HOME%") != -1) {
//					return false;
//				}
//				return true;
//			}
//		} catch (IOException localIOException) {
//			throw new Exception("获取JAVA_HOME信息出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		if (br != null) {
//			br.close();
//		}
//		if (ir != null) {
//			ir.close();
//		}
//		if (is != null) {
//			is.close();
//		}
//		return false;
//	}
//	
//	// 通过cmd打开对应的文件
//	public static void openDir(String fileDir) throws Exception {// 打开cmd，执行explorer
//		Runtime.getRuntime().exec("cmd /c start explorer " + fileDir);// explorer.exe是Windows的程序管理器或者文件资源管理器
//	}
//	
//	// 根据当前的Pid获取当前进程的端口
//	public static Map<String, List<String>> getPortByPID(String pid) throws Exception {
//		if (pid == null) {
//			return null;
//		}
//		InputStream is = null;
//		InputStreamReader ir = null;
//		BufferedReader br = null;
//		String line = null;
//		String TCP_TYPE = "TCP";
//		String UDP_TYPE = "UDP";
//		String LISTENING_STATE_TYPE = "LISTENING";// 状态值
//		Map<String, List<String>> portMap = new HashMap<>();
//		List<String> tcpPortList = new ArrayList<>();
//		List<String> udpPortList = new ArrayList<>();
//		portMap.put(TCP_TYPE, tcpPortList);
//		portMap.put(UDP_TYPE, udpPortList);
//		String[] array = (String[])null;
//		try {
//			Process p = Runtime.getRuntime().exec("netstat /ano");
//			is = p.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			do {
//				if (line.indexOf(pid) != -1) {
//					line = line.replaceFirst("\\s+", "");
//					if (line.indexOf(TCP_TYPE) != -1) {
//						if (line.indexOf(LISTENING_STATE_TYPE) != -1) {
//							array = line.split("\\s+");
//							String port = array[1].split(":")[1];
//							tcpPortList.add(port);
//						}
//					} else {
//						array = line.split("\\s+");
//						String port = array[1].split(":")[1];
//						udpPortList.add(port);
//					}
//				}
//				if ((line = br.readLine()) == null) {
//					break;
//				}
//			} while (pid != null);
//		} catch (IOException localIOException) {
//			throw new Exception("获取进程端口信息出错！");
//		} finally {
//			if (br != null) {
//				br.close();
//			}
//			if (ir != null) {
//				ir.close();
//			}
//			if (is != null) {
//				is.close();
//			}
//		}
//		return portMap;
//	}
//	
//	public static void main(String[] args) throws Exception {
//		System.out.println(getCurrentPid());
//		// System.out.print(getPortByPID("5116"));
//		System.out.println(getMemByPID("5116"));
//		System.out.println(getJavaHome());
//		System.out.println(existJavaHome());
//		String property = System.getProperty("java.home");
//		System.out.println(property);// E:\Programs\jdk\jre
//		Set<String> set = getCurrOsAllPidNameSet();
//		for (String string : set) {
//			System.out.println(string);
//		}
//	}
//	
//	/**
//	 * 获取task信息
//	 * @return
//	 */
//	public static Map<Integer,String> getTaskInfo(){
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
//		return map;
//	}
//}
