package cn.springcloud.boot.ssh.autoinstall.websocket.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.autoinstall.domain.HostDomain;
import cn.com.yusys.yusp.autoinstall.repository.mapper.HostMapper;
import cn.com.yusys.yusp.autoinstall.websocket.common.SshClient;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/terminal/{ip}")
@Component
public class WebTerminalService {

	private static HostMapper hostMapper;
	
	@Autowired
	public void setHostMapper(HostMapper hostMapper) {
		WebTerminalService.hostMapper = hostMapper;
	}

	private SshClient client;

	@OnOpen
	public void onOpen(Session websocketSession, @PathParam("ip") String ip) {
		if (StringUtils.isNotEmpty(ip)) {
			HostDomain host = hostMapper.queryByHostIP(ip);
			if (host == null) {
				return;
			}
			this.client = new SshClient(host, websocketSession);
			this.client.connect();
		}
	}

	@OnMessage
	public void onMessage(String message, Session websocketSession) {
		if (this.client != null) {
			this.client.write(message);
		}
	}

	@OnClose
	public void onClose(Session websocketSession) {
		if (this.client != null) {
			this.client.disconnect();
		}
	}

	@OnError
	public void onError(Session websocketSession, Throwable error) {
		error.printStackTrace();
	}

}