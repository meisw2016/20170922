package cn.springcloud.boot.ssh.autoinstall.websocket.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.jcraft.jsch.Session;

import cn.springcloud.boot.ssh.autoinstall.domain.HostDomain;



public class SshClient {

    // 服务器信息
    private HostDomain hostDomain;

    // ssh 端口号
    private int port;

    // 与web客户端连接的websocket会话
    private javax.websocket.Session websocketSession;

    // ssh2Connection
    private Connection conn = null;

    // ssh2Session
    private Session ssh2Session = null;

    private SshWriteThread writeThread = null;
    
    private BufferedWriter out = null;

    public SshClient(HostDomain hostDomain, javax.websocket.Session websocketSession) {
        this(hostDomain, 22, websocketSession);
    }

    public SshClient(HostDomain hostDomain, int port, javax.websocket.Session websocketSession) {
        super();
        this.hostDomain = hostDomain;
        this.port = port;
        this.websocketSession = websocketSession;
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

                // 向客户端写数据
                startWriterThread();

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
     * 开启线程,新建一个websocket连接，接收服务器端的数据到webclient
     */
    private void startWriterThread() {
        // 启动多线程，来获取我们运行的结果
        // 第一个参数输入流
        // 第二个参数，输出流，这个直接输出的是控制台
        writeThread = new SshWriteThread(ssh2Session.getStdout(), websocketSession);
        new Thread(writeThread).start();
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

        if (writeThread != null) {
            writeThread.stopThread();
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

    public void removeSession(javax.websocket.Session websocketSession) {
        if (writeThread != null) {
            writeThread.removeSession(websocketSession);

        }
    }

    public void addSession(javax.websocket.Session websocketSession) {
        if (writeThread != null) {
            writeThread.addSession(websocketSession);
        }
    }

    public int getSessionNum() {
        if (writeThread != null) {
            return writeThread.getSessionNum();
        }
        return 0;
    }
}
