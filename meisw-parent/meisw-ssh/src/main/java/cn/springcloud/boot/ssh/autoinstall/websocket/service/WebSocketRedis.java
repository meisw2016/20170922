package cn.com.yusys.yusp.autoinstall.websocket.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.autoinstall.domain.HostDomain;
import cn.com.yusys.yusp.autoinstall.repository.mapper.HostMapper;
import cn.com.yusys.yusp.autoinstall.websocket.common.SshClient;

@ServerEndpoint(value = "/websocket/redis/{params}")
@Component
public class WebSocketRedis {

	private static Log logger = LogFactory.getLog(WebSocketRedis.class);

	private static HostMapper hostMapper;
	
	@Autowired
	public void setHostMapper(HostMapper hostMapper) {
		WebSocketRedis.hostMapper = hostMapper;
	}

	// ssh客户端
	private SshClient client;

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// concurrent包的线程安全Set,用来存放每个客户端对应的WebSocket对象
	private static CopyOnWriteArraySet<WebSocketRedis> webSocketSSHServerSet = new CopyOnWriteArraySet<WebSocketRedis>();

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param websocketSession
	 */
	@OnOpen
	public void onOpen(Session websocketSession, @PathParam("params") String params) {

		System.out.println("params====>" + params);
		Map<String, String> paramsMap = parseURLParam2Map(params);
		System.out.println(paramsMap);
		String hostIp = paramsMap.get("ansible_server");
		String operation = paramsMap.get("operationId");

		if (StringUtils.isNotEmpty(hostIp) && hostIp != "192.168.251.151") {
			HostDomain host = hostMapper.queryByHostIP(hostIp);
			if (host == null ) {
				throw new RuntimeException("hostIP并未在管控平台配置，非法IP!!");
			}

			this.client = new SshClient(host, websocketSession);
			this.client.connect();

			// 加入Set
			webSocketSSHServerSet.add(this);
			// 在线数加1
			addOnlineCount();

			logger.info("有新窗口开始监听,当前在线client数为" + getOnlineCount());

			// 执行一些命令
			switch (operation) {
			case "install":
				this.client.write("cd yusp-autoinstall/yusp-autoinstall-shell\r");
				this.client.write("sh addons.sh install redis\r");
				break;
			case "uninstall":
				this.client.write("cd yusp-autoinstall/yusp-autoinstall-shell\r");
				this.client.write("sh addons.sh uninstall redis\r");
				break;
			case "start":
				this.client.write("service redis start\r");
				break;
			case "stop":
				this.client.write("service redis stop\r");
				break;
			case "status":
				this.client.write("service redis status\r");
				break;
			default:
				break;
			}
		} else {
			throw new RuntimeException("ip地址不能为空或此IP为测试服务器，不能进行redis相关操作，请更换ip后再试");
		}
	}

	/**
	 * 连接关闭调用的方法
	 * 
	 * @param websocketSession
	 */
	@OnClose
	public void onClose(Session websocketSession) {

		// 从Set中删除
		webSocketSSHServerSet.remove(this);
		// 在线数减1
		subOnlineCount();

		logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		// 关闭连接
		if (this.client != null) {
			this.client.disconnect();
		}
	}

	@OnMessage
	public void onMessage(String message, Session websocketSession) {
		logger.info("收到来自窗口的信息:" + message);
		try {
			// 当客户端不为空的情况
			if (client != null) {
				// 写入前台传递过来的命令，发送到目标服务器上
				client.write(message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				// websocketSession.sendMessage(new TextMessage("An error occured, websocket is
				// closed."));
				websocketSession.getBasicRemote().sendText("An error occured, websocket is closed.");
				websocketSession.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 发生错误
	 * 
	 * @param websocketSession
	 * @param error
	 */
	@OnError
	public void onError(Session websocketSession, Throwable error) {
		logger.error("发生错误");
		error.printStackTrace();
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketRedis.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketRedis.onlineCount--;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 *
	 * @param URL url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> parseURLParam2Map(String strUrlParam) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

}
