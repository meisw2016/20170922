package cn.springcloud.boot.ssh.autoinstall.repository.mapper;

import java.util.List;

import cn.com.yusys.yusp.autoinstall.domain.HostDomain;

public interface HostMapper {

	List<HostDomain> queryAllHosts();

	List<HostDomain> queryByClusterId(String clusterId);

	HostDomain queryByHostName(String hostName);

	HostDomain queryByHostIP(String hostIP);

	int insertHostDomain(HostDomain hostDomain);

	int updateHostDomain(HostDomain hostDomain);

	int deleteByHostName(String hostName);

	int deleteHostClusterInfoByHostId(String hostId);

	int insertHostClusterInfos(String relId, String hostId, String clusterId);

}