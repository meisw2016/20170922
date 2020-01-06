package cn.springcloud.meisw.eureka.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HDFSConstans {
	
	@Value("${hdfs.address}")
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
	private String hadoop_tmp_dir;
	
	public String getDefaultFs() {
		return defaultFs;
	}
	
	public void setDefaultFs(String defaultFs) {
		this.defaultFs = defaultFs;
	}
	
	public String getHdfsname() {
		return hdfsname;
	}
	
	public void setHdfsname(String hdfsname) {
		this.hdfsname = hdfsname;
	}
	
	public String getNameservices() {
		return nameservices;
	}
	
	public void setNameservices(String nameservices) {
		this.nameservices = nameservices;
	}
	
	public String getHa_namenodes_nsl() {
		return ha_namenodes_nsl;
	}
	
	public void setHa_namenodes_nsl(String ha_namenodes_nsl) {
		this.ha_namenodes_nsl = ha_namenodes_nsl;
	}
	
	public String getNamenode_rpc_address_ns1_nn1() {
		return namenode_rpc_address_ns1_nn1;
	}
	
	public void setNamenode_rpc_address_ns1_nn1(String namenode_rpc_address_ns1_nn1) {
		this.namenode_rpc_address_ns1_nn1 = namenode_rpc_address_ns1_nn1;
	}
	
	public String getClient_failover_proxy_provider_ns1() {
		return client_failover_proxy_provider_ns1;
	}
	
	public void setClient_failover_proxy_provider_ns1(String client_failover_proxy_provider_ns1) {
		this.client_failover_proxy_provider_ns1 = client_failover_proxy_provider_ns1;
	}
	
	public String getHadoop_tmp_dir() {
		return hadoop_tmp_dir;
	}
	
	public void setHadoop_tmp_dir(String hadoop_tmp_dir) {
		this.hadoop_tmp_dir = hadoop_tmp_dir;
	}
	
}
