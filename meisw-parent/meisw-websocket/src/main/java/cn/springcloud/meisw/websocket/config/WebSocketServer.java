package cn.springcloud.meisw.websocket.config;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
	
	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	/**
	 * 静态变量，用来记录当前在线连续数，应该把它设计成线程安全的
	 */
	private static int onlineCount = 0;
	/**
	 * concurrent包的线程安全set，用来存储客户端对应的MyWebSocket对象
	 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;
	/**
	 * 接收sid
	 */
	private String sid = "";
	
	/**
	 * 连接建立成功调用的方法
	 * @author meisw 2019年11月18日 下午5:09:07
	 * @Method: onOpen 
	 * @Description: TODO
	 * @param session
	 * @param sid 
	 * @throws
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.session = session;
		webSocketSet.add(this);//加入set中
		addOnlineCount();//在线人数加1
		log.info("有新窗口开始监听：{},当前在线人数为：{}",sid,getOnlineCount());
		this.sid = sid;
		try {
			sendMessage("连接成功");
		} catch (IOException e) {
			log.error("消息发送服务异常：{}",e);
		}
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);//从set中删除
		subOnlineCount();//在线数减1
		log.info("有一连接关闭!当前在线人数为：{}",getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		log.info("收到来自窗口：{}"+sid+"的信息：{}",sid,message);
		/**群发消息*/
		for(WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				log.error("发送消息服务异常：{}",e);
			}
		}
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		log.error("发送错误!");
		error.printStackTrace();
	}
	
	/**
	 * 实现服务器主动推送
	 * @author meisw 2019年11月18日 下午5:17:08
	 * @Method: sendMessage 
	 * @Description: TODO
	 * @param message
	 * @throws IOException 
	 * @throws
	 */
	public void sendMessage(String message)throws IOException{
		this.session.getBasicRemote().sendText(message);
	}
	
	/**
	 * 群发自定义消息
	 * @author meisw 2019年11月18日 下午5:59:44
	 * @Method: sendInfo 
	 * @Description: TODO
	 * @param message
	 * @param sid
	 * @throws IOException 
	 * @throws
	 */
	public static void sendInfo(String message,@PathParam("sid")String sid)throws IOException{
		log.info("推送消息到窗口：{},推送内容：{}",sid,message);
		for(WebSocketServer item:webSocketSet) {
			/**这里可以设定只推送给这个sid的,为null则全部推送*/
			try {
				if(sid == null) {
					item.sendMessage(message);
				}else if(item.sid.equals(sid)) {
					item.sendMessage(message);
				}
			}catch(IOException e) {
				continue;
			}
		}
	}
	
	/**
	 * 在线数加1 @author meisw 2019年11月18日 下午5:04:16 @Method: addOnlineCount @Description: TODO @throws
	 */
	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}
	
	/**
	 * 获取当前在线数 @author meisw 2019年11月18日 下午5:05:04 @Method: getOnlineCount @Description: TODO @return @throws
	 */
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	
	/**
	 * 下线人数减1 @author meisw 2019年11月18日 下午5:05:56 @Method: subOnlineCount @Description: TODO @throws
	 */
	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
	
	public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
		return webSocketSet;
	}
}
