package cn.springcloud.boot.ssh.ssh.session;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * @Description:一个简单的sftp上传监控
 * @Author: lynl
 * @Date 2018/3/7
 * @Version: 1.0
 */
public class SimpleSftpMonitor implements SftpProgressMonitor {
	
    private int len = 0;

    @Override
    public void init(int op, String src, String dest, long max) {
        if (op == PUT) {
            System.out.println("开始上传: 源:" + src + ",目标:" + dest + ",总长度:" + max);
        }
    }

    @Override
    public boolean count(long count) {
        len += count;
        System.out.println("进度:" + len);
        return true;
    }

    @Override
    public void end() {
        System.out.println("完成");
    }
    
}