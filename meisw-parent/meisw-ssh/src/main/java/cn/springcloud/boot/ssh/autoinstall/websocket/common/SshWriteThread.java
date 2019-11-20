package cn.springcloud.boot.ssh.autoinstall.websocket.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;

public class SshWriteThread implements Runnable {

    // 定义一个flag,来停止线程
    private boolean isStop = false;

    // 输入流数据
    private InputStream in;

    // 用于输出数据
    private Set<Session> sessionSet = new CopyOnWriteArraySet<>();

    private static final String ENCODING = "UTF-8";

    public SshWriteThread(InputStream in, Session websocketSession) {
        super();
        this.in = in;
        this.sessionSet.add(websocketSession);
    }

    /**
     * 停止线程
     */
    public void stopThread() {
        this.isStop = true;
    }

    @Override
    public void run() {
        // 线程运行中 且 session不为空 且 session是打开状态
        while (!isStop) {
            writeToWeb(in);
        }
    }

    public void removeSession(Session websocketSession) {
        sessionSet.remove(websocketSession);
        if (sessionSet.isEmpty()) {
            this.stopThread();
        }
    }

    public void addSession(Session websocketSession) {
        sessionSet.add(websocketSession);
    }

    public int getSessionNum() {
        return sessionSet.size();
    }

    private void writeToWeb(InputStream in) {
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

                String line = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1), ENCODING);
                // 写数据到客户端
                Iterator<Session> iterator = sessionSet.iterator();
                while (iterator.hasNext()) {
                    Session session = iterator.next();
                    if (session != null && session.isOpen()) {
                        session.getBasicRemote().sendText(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
