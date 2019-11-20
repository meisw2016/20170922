package cn.springcloud.boot.ssh.ssh.session;

import cn.com.yusys.yusp.autoinstall.domain.DashboardCmdPair;
import cn.com.yusys.yusp.ssh.cmd.CmdTypeConstants;
import cn.com.yusys.yusp.ssh.common.ChannelTypeConstants;
import cn.com.yusys.yusp.ssh.common.Utils;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * @Description:对session操作的一些简单封装
 * @Author: lynl
 * @Date 2018/3/7
 * @Version: 1.0
 */
public class SimpleSshSession implements AutoCloseable {
	private final Logger log = LoggerFactory.getLogger(SimpleSshSession.class);
	private final int timeout = 3000;
	private Session session;

	public SimpleSshSession(Session session) {
		this.session = session;
	}

	/**
	 * 会话建立连接
	 */
	public void connect() throws JSchException {
		if (!session.isConnected()) {
			session.connect();
		}
	}

	@Override
	public void close() throws Exception {
		if (Objects.nonNull(session))
			session.disconnect();
	}

	public boolean isConnected() {
		return session.isConnected();
	}

	/**
	 * @return 主机公钥
	 */
	public String getPublicKey() {
		String key = "";
		try {
			connect();
			if (session.getHostKey() != null) {
				key = session.getHostKey().getKey();
			}
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return key;
	}

	/**
	 * @param cmd-命令
	 */
	public void runCmd(String cmd) {
		ChannelExec exec = null;
		try {
			connect();
			exec = (ChannelExec) session.openChannel(ChannelTypeConstants.EXEC);
			exec.setCommand(cmd);
			exec.connect(timeout);
			log.info("{} 执行完成", cmd);

		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			if (exec != null) {
				exec.disconnect();
			}
		}
	}

	/**
	 * @param cmd-命令
	 * @return
	 * @throws Exception
	 */
	public String goCmd(String cmd) throws Exception {
		StringBuffer sb = new StringBuffer();
		ChannelExec exec = null;
		try {
			connect();
			exec = (ChannelExec) session.openChannel(ChannelTypeConstants.EXEC);
			exec.setCommand(cmd);
			InputStream is = exec.getInputStream();
			InputStream inputStream = exec.getErrStream();
			exec.connect(timeout);
			int len;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}

			if (StringUtils.isEmpty(sb)) {
				while ((len = inputStream.read(buffer)) != -1) {
					sb.append(new String(buffer, 0, len));
				}
				if (StringUtils.isNotEmpty(sb)) {
					sb.insert(0, "err:");
				}
			}

			log.info("{} 执行完成", cmd);
			exec.disconnect();
		} catch (JSchException | IOException e) {
			throw e;
		} finally {
			if (exec != null) {
				exec.disconnect();
			}
		}

		return sb.toString();
	}

	/**
	 * @param pair-参数对象
	 * @return
	 * @throws Exception
	 */
	public DashboardCmdPair goCmd(DashboardCmdPair pair) throws Exception {
		String cmd = "cd " + pair.getDir() + " && " + pair.getOut();
		String out = goCmd(cmd);
		if (CmdTypeConstants.isCDType(pair.getOut())) {
			pair.setDir(Utils.getNewDir(pair.getDir(), pair.getOut()));
		}
		if (out.startsWith("err:")) {
			pair.setCode(1);
		} else {
			pair.setCode(0);
		}
		pair.setOut(out);
		return pair;
	}

	/**
	 * @param local-本地文件
	 * @param path-目标文件父级路径
	 * @param cover-是否覆盖 true-覆盖 false-不覆盖
	 * @throws FileNotFoundException
	 * @throws JSchException
	 * @throws SftpException
	 */
	public void upload(File local, String path, boolean cover) {

		try {
			InputStream localStream = new FileInputStream(local);
			upload(localStream, local.getName(), path, cover);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param local-本地文件
	 * @param path-目标文件父级路径
	 * @param cover-是否覆盖 true-覆盖 false-不覆盖
	 * @throws FileNotFoundException
	 * @throws JSchException
	 * @throws SftpException
	 */
	public void upload(InputStream localStream, String fileName, String path, boolean cover) {

		ChannelSftp sftp = null;
		try {
			connect();

			SimpleSftpMonitor monitor = new SimpleSftpMonitor();
			sftp = (ChannelSftp) session.openChannel(ChannelTypeConstants.SFTP);
			sftp.connect(timeout);

			// 判断目录是否存在
			SftpATTRS attrs = null;
			try {
				attrs = sftp.stat(path);
			} catch (Exception ex) {
				log.warn("path:{} isn't exists, mkdir it", path);
			}

			if (attrs == null) {
				sftp.mkdir(path);
			}
			sftp.cd(path);

			if (cover) {
				try {
					sftp.rm(fileName);// 删除已有文件
				} catch (Exception ex) {
					log.warn("删除文件失败, Message:{}", ex.getMessage());
				}
			}

			sftp.put(localStream, fileName, monitor, 1);
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (sftp != null) {
				sftp.disconnect();
			}
		}

	}

	/**
	 * @方法名称: remove
	 * @方法描述: 删除文件
	 * @参数与返回说明:
	 * @算法描述:
	 */
	public void remove(String fileName, String path) {
		ChannelSftp sftp = null;
		try {
			connect();

			sftp = (ChannelSftp) session.openChannel(ChannelTypeConstants.SFTP);
			sftp.connect(timeout);
			sftp.cd(path);

			try {
				sftp.rm(fileName);// 删除文件
			} catch (Exception ex) {
				log.warn("删除文件失败, Message:{}", ex.getMessage());
			}
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (sftp != null) {
				sftp.disconnect();
			}
		}
	}

	/**
	 * @方法名称: getFileVOList
	 * @方法描述: 获取指定路径下的文件列表
	 * @参数与返回说明:
	 * @算法描述:
	 */
	public List<ChannelSftp.LsEntry> getFiles(String path) {

		List<ChannelSftp.LsEntry> fileList = null;
		ChannelSftp sftp = null;
		try {
			connect();

			fileList = new ArrayList<ChannelSftp.LsEntry>();
			sftp = (ChannelSftp) session.openChannel(ChannelTypeConstants.SFTP);
			sftp.connect(300000);
			Vector<ChannelSftp.LsEntry> list = sftp.ls(path);
			for (ChannelSftp.LsEntry file : list) {
				if (!file.getAttrs().isDir()) {
					fileList.add(file);
				}
			}
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (sftp != null) {
				sftp.disconnect();
			}
		}

		return fileList;
	}

	/**
	 * @方法名称: downLoadFile
	 * @方法描述: 文件下载
	 * @参数与返回说明:
	 * @算法描述:
	 */
	public void downLoadFile(String srcFile, String srcPath, File outfile) {
		InputStream in = null;
		OutputStream out = null;
		ChannelSftp sftp = null;
		try {
			connect();
			sftp = (ChannelSftp) session.openChannel(ChannelTypeConstants.SFTP);
			sftp.connect(5000000);
			sftp.cd(srcPath);
			in = sftp.get(srcFile);
			out = new FileOutputStream(outfile);
			byte[] buffer = new byte[1024];
			int readLength = 0;
			while ((readLength = in.read(buffer)) != -1) {
				out.write(buffer, 0, readLength);
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (sftp != null) {
				sftp.disconnect();
			}
		}
	}
	
}