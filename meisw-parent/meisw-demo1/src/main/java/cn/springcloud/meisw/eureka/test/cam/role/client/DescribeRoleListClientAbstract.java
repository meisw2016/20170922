package cn.springcloud.meisw.eureka.test.cam.role.client;

import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;

public abstract class DescribeRoleListClientAbstract extends AbstractClient {
	
	public DescribeRoleListClientAbstract(String endpoint, String version, Credential credential, String region) {
		super(region, region, credential, region);
	}
	
}
