package cn.springcloud.boot.ssh.autoinstall.websocket.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import ch.ethz.ssh2.Connection;
import com.jcraft.jsch.Session;

import cn.springcloud.boot.ssh.autoinstall.domain.HostDomain;




public class SshClient4SoftwareManage {

    // 服务器信息
    private HostDomain hostDomain;

    // ssh 端口号
    private int port;
    
    private boolean flag = false;

    public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// ssh2Connection
    private Connection conn = null;

    // ssh2Session
    private Session ssh2Session = null;
    
    private BufferedWriter out = null;
    
    private Map<String, String> softwareManageMap = new HashMap<String, String>();

    public Map<String, String> getSoftwareManageMap() {
		return softwareManageMap;
	}

	public void setSoftwareManageMap(Map<String, String> softwareManageMap) {
		this.softwareManageMap = softwareManageMap;
	}

	public SshClient4SoftwareManage(HostDomain hostDomain, int port) {
        super();
        this.hostDomain = hostDomain;
        this.port = port;
    }

    public Session getSsh2Session() {
		return ssh2Session;
	}

	public void setSsh2Session(Session ssh2Session) {
		this.ssh2Session = ssh2Session;
	}

	/**
     * 建立ssh连接
     *
     * @return boolean
     */
    public boolean connect() {
        try {
            String ip = this.hostDomain.getIp();
            String userName = this.hostDomain.getUserName();
            String password = this.hostDomain.getPassword();

            conn = new Connection(ip, this.port);
            conn.connect();

            boolean isAuthenticated = conn.authenticateWithPassword(userName, password);

            if (isAuthenticated) {
                // 打开连接
                ssh2Session = conn.openSession();

                ssh2Session.requestPTY("xterm", 175, 45, 0, 0, null);

                // 启动shell
                ssh2Session.startShell();

                // 输出流
                out = new BufferedWriter(new OutputStreamWriter(ssh2Session.getStdin(), "utf-8"));

                return true;
            } else {
                System.out.println("用户名和密码校验出错，连接服务器失败...");
                this.disconnect();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.disconnect();
            return false;
        }
    }

    /**
     * 关闭连接
     */
    public void disconnect() {

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ssh2Session != null) {
            ssh2Session.close();
        }

        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 写数据到服务器端，让机器执行命令
     *
     * @param cmd
     * @return boolean
     */
    public boolean write(String cmd) {
        try {
            this.out.write(cmd);
            this.out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public void writeToMap(InputStream in) {
        try {
            // 定义一个缓存
            // 一个UDP的用户数据报的数据字段长度为8192字节
            byte[] buff = new byte[8192];

            int len;
            StringBuffer sb = new StringBuffer();
            while ((len = in.read(buff)) > 0) {
                // 设定从0开始
                sb.setLength(0);

                // 读缓冲区里的数据，进行补码
                for (int i = 0; i < len; i++) {
                    // 进行补码操作
                    char c = (char) (buff[i] & 0xff);
                    sb.append(c);
                }

                String line = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1), "UTF-8");
                
                if(line.contains("=")) {
                	this.softwareManageMap.putAll(parseHostInfoString2Map(line));
                	System.out.println(this.getSoftwareManageMap());
                	if(line.contains("osinfoend")) {
                		this.setFlag(true);
                	}
                	break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Map<String, String> parseHostInfoString2Map(String hostInfo) {
		Map<String, String> result = new HashMap<String, String>();

		String[] arrSplit = null;

		if (hostInfo == null) {
			return result;
		}
		// 每个键值为一组
		arrSplit = hostInfo.split("[\n]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				if(arrSplitEqual[0].indexOf("[") > 0) {
					//去掉颜色字符串
					result.put(arrSplitEqual[0].trim().substring(6), arrSplitEqual[1].trim());
				} else {
					result.put(arrSplitEqual[0].trim(), arrSplitEqual[1].trim());
				}
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					result.put(arrSplitEqual[0].trim(), "");
				}
			}
		}
		return result;
	}
    
    public static void main(String[] args) throws InterruptedException {
    	HostDomain hostDomain = new HostDomain();
    	hostDomain.setIp("192.168.251.151");
    	hostDomain.setUserName("root");
    	hostDomain.setPassword("yusys.com.cn");

		SshClient4SoftwareManage client = new SshClient4SoftwareManage(hostDomain, 22);
		client.connect();
		client.write("cd yusp-autoinstall/yusp-autoinstall-shell/tools\r");
		client.write("chmod 777 YUSP\r");
		client.write("sh osinfo.sh rabbitmq\r");
		client.write("cat osinfo\r");
		while(!client.isFlag()) {
			client.writeToMap(client.getSsh2Session().getStdout());
		}
		
		client.write("rm -rf osinfo\r");
		client.disconnect();
	}
}
