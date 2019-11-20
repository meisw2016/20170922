package cn.springcloud.boot.ssh.ssh.session;

import cn.com.yusys.yusp.autoinstall.exception.DashboardSessionException;
import cn.com.yusys.yusp.ssh.common.ConnectionInfo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 构建Ssh会话工厂
 */
public class SshSessionFactory {
	
    //private static final JSch jSch = new JSch();
    private static final Logger logger = LoggerFactory.getLogger(SshSessionFactory.class);
    
    /**
     * 根据key存储对应的会话
     */
    /*private static Map<String, SimpleSshSession> sessionMap = new ConcurrentHashMap<>();
    private static SshSessionFactory instance = null;*/

    /**
     * 私有构造器
     */
    private SshSessionFactory() {
    }
    
    private final static class SshSessionFactoryHolder {
        private final static SshSessionFactory _INSTANCE = new SshSessionFactory();
    }
    
    private final static SshSessionFactory _instance = SshSessionFactoryHolder._INSTANCE;
    
    public final static SshSessionFactory getInstance() {
        return _instance;
    }
    
    public final SimpleSshSession getSession(ConnectionInfo ci) throws DashboardSessionException {
        /*if (sessionMap.containsKey(ci.getUri())) {
            SimpleSshSession session = sessionMap.get(ci.getUri());
            if (!session.isConnected()) {
                
            }
        } else {
            
        }
        return sessionMap.get(ci.getUri());*/
        if (Objects.isNull(ci)) {
            throw new DashboardSessionException("主机信息不可为空!");
        }
        Session session = null;
        try {
            session = new JSch().getSession(ci.getUserName(), ci.getUri());
            session.setConfig("PreferredAuthentications","password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(ci.getPassword());
        } catch (JSchException e) {
            logger.error(e.getMessage(), e);
        }
        return new SimpleSshSession(session);
    }
    

    /**
     * @return 单实例
     */
   /* public synchronized static SshSessionFactory getInstance(ConnectionInfo ci) {
        if (instance == null) {
            instance = new SshSessionFactory();
        }
        if (sessionMap.containsKey(ci.getHostName())) {
            log.warn("你要添加的主机:{}已存在", ci.getHostName());
        } else {
            SimpleSshSession session = getSession(ci);
            sessionMap.put(ci.getHostName(), session);
        }
        return instance;
    }*/
    

    /**
     * @param ci -连接信息
     * @return 根据对应的连接信息获取对应的会话
     */
   /* private static SimpleSshSession getSession(ConnectionInfo ci) {
        Session session = null;
        try {
            session = jSch.getSession(ci.getUserName(), ci.getUri());
            session.setConfig("PreferredAuthentications","password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(ci.getPassword());
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return new SimpleSshSession(session);
    }

    *//**
     * @param hostName-主机名
     * @return 根据主机名返回对应会话
     *//*
    public SimpleSshSession getSession(String hostName) {
        return sessionMap.get(hostName);
    }

    public Map<String, SimpleSshSession> getSessionMap() {
        return sessionMap;
    }*/
}
