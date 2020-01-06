package cn.springcloud.meisw.eureka.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * HDFS操作工具类
 * <p></p>
 * @author meisw 2020年1月6日 下午4:43:27
 * @ClassName HDFSUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月6日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class HDFSUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HDFSUtil.class);
	
	public static final String CROSS_PLATFORM = "mapreduce.app-submission.cross-platform";
	public static final String FS_HDFS_IMPL_PARAM = "fs.hdfs.impl";
	public static final String FS_HDFS_IMPL = "org.apache.hadoop.hdfs.DistributedFileSystem";
	
	/*@Value("${hdfs.address}")
	private String defaultFs;
	
	@Value("${hdfs.user}")
	private String hdfsname;
	
	@Value("${hdfs.nameservices}")
	private String nameservices;
	
	@Value("${hdfs.ha_namenodes_ns1}")
	private String ha_namenodes_nsl;
	
	@Value("${namenode_rpc_address_ns1_nn1}")
	private String namenode_rpc_address_ns1_nn1;
	
	@Value("${hdfs.client_failover_proxy_provider_ns1}")
	private String client_failover_proxy_provider_ns1;
	
	@Value("${hdfs.hadoopHome}")
	private String hadoop_tmp_dir;*/
	
	@Autowired
	private HDFSConstans hdfsConstans; 
	
	private Configuration configuration;
	
	/**
	 * 获取HDFS配置信息
	 * @author meisw 2020年1月6日 下午5:19:23
	 * @Method: getConfiguration 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	private  Configuration getConfiguration() {
		if(configuration == null) {
			configuration = new Configuration();
		}
		configuration.set("fs.defaultFs", hdfsConstans.getDefaultFs());//此次会是NULL值，因为静态方法优先与全局变量加载
		configuration.set("dfs.nameservices", hdfsConstans.getNameservices());
		String[] nn1s = hdfsConstans.getNamenode_rpc_address_ns1_nn1().split(",");
		log.info("nn1s的长度是{}",nn1s.length);
		for(int i=0;i<nn1s.length;i++) {
			configuration.set("dfs.namenode.rpc-address."+hdfsConstans.getNameservices()+"."+nn1s[i].substring(0,3),nn1s[i].substring(4)) ;
		}
		configuration.set("dfs.client.failover.proxy.provider."+hdfsConstans.getNameservices(), hdfsConstans.getClient_failover_proxy_provider_ns1());
		configuration.set("hadoop.home.dir", hdfsConstans.getHadoop_tmp_dir());
		configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
		return configuration;
	}
	
	/**
	 * HDFS文件上传服务
	 * @author meisw 2020年1月6日 下午5:26:22
	 * @Method: updateHDFS 
	 * @Description: TODO
	 * @param srcPath	源文件[要上传的文件]
	 * @param dstPath	目标路径[文件上传的位置目录]
	 * @param hdfsUrl
	 * @param user
	 * @throws Exception 
	 * @throws
	 */
	public void updateHDFS(String srcPath,String dstPath,String hdfsUrl,String user)throws Exception{
		Configuration  conf = getConfiguration();
		conf.set("fs.defaultFs", hdfsUrl);
		conf.set(HDFSUtil.FS_HDFS_IMPL_PARAM, HDFSUtil.FS_HDFS_IMPL);
		FileSystem fs = null;
		try {
			fs = FileSystem.get(new URI(hdfsUrl),conf,user);
			Path src = new Path(srcPath);
			Path dst = new Path(dstPath);
			fs.copyFromLocalFile(src, dst);
		}catch(Exception e) {
			log.error("HDFS上传文件失败!:{}",e);
		}finally {
			if(fs!=null) {
				fs.close();
			}
		}
	}
	
	/**
	 * HDFS文件下载
	 * @author meisw 2020年1月6日 下午5:29:27
	 * @Method: downFileFromHDFS 
	 * @Description: TODO
	 * @param hadoopHome
	 * @param hdfsUrl
	 * @param localFName
	 * @param hdfsFName
	 * @param user
	 * @throws Exception 
	 * @throws
	 */
	public void downFileFromHDFS(String hadoopHome,String hdfsUrl,String localFName,String hdfsFName,String user)throws Exception{
		Configuration conf = getConfiguration();
		conf.set("fs.defaultFs", hdfsUrl);
		conf.set(HDFSUtil.FS_HDFS_IMPL_PARAM, HDFSUtil.FS_HDFS_IMPL);
		conf.set("hadoop.home.dir", hadoopHome);
		String hdfsFPath = hdfsUrl + hdfsFName;
		FileSystem fs = null;
		FSDataInputStream outHDFS = null;
		OutputStream inLocal = null;
		try {
			fs = FileSystem.get(new URI(hdfsUrl).create(hdfsFPath),conf);//创建文件系统对象
			outHDFS = fs.open(new Path(hdfsFPath));//从HDFS读出文件流
			inLocal = new FileOutputStream(localFName);//写入本地文件
			IOUtils.copyBytes(outHDFS, inLocal, 1024*1024*10,true);
			fs.close();
			outHDFS.close();
			inLocal.close();
		}catch(Exception e) {
			log.error("HDFS文件下载失败:{}",e);
		}finally {
			if(fs != null) {
				fs.close();
			}
			if(outHDFS!= null) {
				outHDFS.close();
			}
			if(inLocal!=null) {
				inLocal.close();
			}
		}
	}
	
	/**
	 * 创建目录
	 * @author meisw 2020年1月6日 下午6:38:22
	 * @Method: mkdir 
	 * @Description: TODO
	 * @param hadoopHome	客户端hadoopHome目录 eg:D:/install/hadoop-2.7.1
	 * @param hdfsUrl	hdfsURL地址 eg:hdfs://localhost:9000
	 * @param user	hdfs用户 eg: appmon
	 * @param path	要创建的目录 eg:D:/install/msw/beats
	 * @throws Exception 
	 * @throws
	 */
	public void mkdir(String hadoopHome,String hdfsUrl,String user,String path)throws Exception{
		Configuration conf = getConfiguration();
		conf.set(HDFSUtil.FS_HDFS_IMPL_PARAM, HDFSUtil.FS_HDFS_IMPL);
		conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
		conf.set("hadoop.home.dir", hadoopHome);
		FileSystem fs = null;
		try {
			fs = FileSystem.get(new URI(hdfsUrl),conf,user);
			/*** 创建目录 **/
			fs.mkdirs(new Path(path));
		}catch(Exception e) {
			log.error("HDFS创建目录失败：{}",e);
		}
	}
	
	/**
	 * HDFS目录列表
	 * @author meisw 2020年1月6日 下午6:50:56
	 * @Method: listFile 
	 * @Description: TODO
	 * @param hadoopHome
	 * @param hdfsUrl
	 * @param user
	 * @param path
	 * @throws Exception 
	 * @throws
	 */
	public void listFile(String hadoopHome,String hdfsUrl,String user,String path)throws Exception{
		Configuration conf = getConfiguration();
		conf.set(HDFSUtil.FS_HDFS_IMPL_PARAM, HDFSUtil.FS_HDFS_IMPL);
		conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
		/** 手动为Configuration对象设置fs.default.name属性值,文件的备份为1 **/
		conf.set("dfs.replication", "1");
		FileSystem fs;
		RemoteIterator<LocatedFileStatus> listFiles;
		try {
			fs = FileSystem.get(new URI(hdfsUrl),conf,user);
			listFiles = fs.listFiles(new Path("/agent/agent"), true);
			while(listFiles.hasNext()) {
				LocatedFileStatus fileStatus = listFiles.next();
				log.info(fileStatus.getPath().getName());
				log.info("fileStatus.getBlockSize:{}",fileStatus.getBlockSize());
				log.info("permission:{}",fileStatus.getPermission());
				log.info("file length:{}",fileStatus.getLen());
				BlockLocation[] blockLocations = fileStatus.getBlockLocations();
				for(BlockLocation bl : blockLocations) {
					log.info("block-length:{},block-offset:{}",bl.getLength(),bl.getOffset());
				}
				log.info("******************** 文件列表分割线 *****************************");
			}
		}catch(Exception e) {
			log.error("HDFS目录列表异常：{}",e);
		}
	}
}
