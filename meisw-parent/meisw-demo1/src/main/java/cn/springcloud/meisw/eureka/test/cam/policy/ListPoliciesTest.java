package cn.springcloud.meisw.eureka.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.ListPoliciesRequest;
import com.tencentcloudapi.cam.v20190116.models.ListPoliciesResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
/**
 * 
 * <p></p>
 * @author meisw 2019年6月26日 下午2:13:19
 * @ClassName ListPoliciesTest
 * @Description 查询策略列表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class ListPoliciesTest {
	
	private static final Logger log = LoggerFactory.getLogger(ListPoliciesTest.class);
	
	public static void main(String[] args) {
		try {
			/**
			 * 1.实例化认证对象
			 */
			Credential cred = new Credential("AKIDLSKwKpi8yZXGjwquZzBIAnBTXO1VE50m", "ZzJTPBi5Gm8FAtY4QcOxIOJ9pKZeVCmB");
			/**
			 * 2.实例化一个http选项
			 */
			HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("GET"); // post请求(默认为post请求)
            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)
            httpProfile.setEndpoint("cam.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            
            ListPoliciesRequest req = new ListPoliciesRequest();
            CamClient client = new CamClient(cred,"ap-shanghai",clientProfile);
            ListPoliciesResponse resp = client.ListPolicies(req);
            log.info("查询策略列表服务："+ListPoliciesResponse.toJsonString(resp));
            /**
             * {
	"TotalNum": 252,
	"List": [{
		"PolicyId": 1,
		"PolicyName": "AdministratorAccess",
		"AddTime": "2016-06-02 19:40:09",
		"Type": 2,
		"Description": "该策略允许您管理账户内所有用户及其权限、财务相关的信息、云服务资产。",
		"CreateMode": 2,
		"Attachments": 1,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 400411,
		"PolicyName": "ReadOnlyAccess",
		"AddTime": "2017-09-01 15:57:54",
		"Type": 2,
		"Description": "该策略允许您只读访问账户内所有支持接口级鉴权或资源级鉴权的云服务资产。",
		"CreateMode": 2,
		"Attachments": 1,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 2,
		"PolicyName": "QCloudResourceFullAccess",
		"AddTime": "2016-06-02 19:40:23",
		"Type": 2,
		"Description": "该策略允许您管理账户内所有云服务资产。",
		"CreateMode": 2,
		"Attachments": 1,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 3,
		"PolicyName": "QCloudFinanceFullAccess",
		"AddTime": "2016-06-02 19:40:37",
		"Type": 2,
		"Description": "该策略允许您管理账户内财务相关的内容，例如：付款、开票。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 534810,
		"PolicyName": "QcloudAAIFullAccess",
		"AddTime": "2017-10-24 21:29:54",
		"Type": 2,
		"Description": "智能语音（AAI）全读写访问",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "aai"
	}, {
		"PolicyId": 4142469,
		"PolicyName": "QcloudAccessForAegisRole",
		"AddTime": "2018-08-17 18:18:03",
		"Type": 2,
		"Description": "游戏安全-宙斯盾（Aegis）对云资源的访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 13847366,
		"PolicyName": "QcloudAccessForASRole",
		"AddTime": "2019-03-21 19:41:49",
		"Type": 2,
		"Description": "腾讯云弹性伸缩(AS)操作权限含创建、销毁、查询云服务器（CVM）实例；含查询私有网络（VPC）子网；含查询、绑定、解绑负载均衡（CLB）实例；含增加、删除监控策略。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 3915863,
		"PolicyName": "QcloudAccessForBAASRole",
		"AddTime": "2018-08-10 16:11:55",
		"Type": 2,
		"Description": "腾讯区块链（Tencent BlockChain）对云资源的跨服务访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cvm"
	}, {
		"PolicyId": 2195916,
		"PolicyName": "QcloudAccessForBKRole",
		"AddTime": "2018-04-26 18:28:52",
		"Type": 2,
		"Description": "蓝鲸平台(BlueKing)对云资源的访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 18150187,
		"PolicyName": "QcloudAccessForCARole",
		"AddTime": "2019-05-06 11:29:35",
		"Type": 2,
		"Description": "云审计服务(CA)创建跟踪集功能操作权限含查询和创建对象存储桶(COS)；含查询和创建消息队列(CMQ)等权限；查询和创建密钥管理服务(KMS)键等权限，用以将云审计跟踪日志投递到COS和进行CMQ通知。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 16313162,
		"PolicyName": "QcloudAccessForCDNRole",
		"AddTime": "2019-04-19 10:55:31",
		"Type": 2,
		"Description": "腾讯云内容分发网络(CDN)操作权限含日志服务(CLS)的增删改查日志集，增删改查日志主题，搜索下载上传日志。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 19042704,
		"PolicyName": "QcloudAccessForCFSRole",
		"AddTime": "2019-05-16 11:16:48",
		"Type": 2,
		"Description": "文件存储(CFS)操作权限含查询密钥管理(KMS)服务，创建密钥，使用密钥加解密数据等。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 18149988,
		"PolicyName": "QcloudAccessForCloudAuditCARole",
		"AddTime": "2019-05-06 11:25:55",
		"Type": 2,
		"Description": "腾讯云云审计服务(CA)创建跟踪集功能操作权限含查询和创建对象存储桶(COS)；含查询和创建(CMQ)队列等权限，用以将云审计跟踪日志投递到COS和进行CMQ通知。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 16196117,
		"PolicyName": "QcloudAccessForCOSBatchRole",
		"AddTime": "2019-04-18 17:31:42",
		"Type": 2,
		"Description": "腾讯云对象存储批量处理服务（COS Batch Operations）操作权限包括但不限于以下权限：增删查改对象存储(COS)对象、对象ACL、对象数据元数据信息等。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 21695529,
		"PolicyName": "QcloudAccessForCOSRole",
		"AddTime": "2019-06-25 10:24:22",
		"Type": 2,
		"Description": "对象存储服务(COS)操作权限含密钥管理服务(KMS)内创建查看密钥信息等；增删查改日志服务(CLS)日志集、日志主题、日志，增删查改机器组，增删查改索引以及投递日志等。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 10929325,
		"PolicyName": "QcloudAccessForDTSRole",
		"AddTime": "2019-02-13 15:54:40",
		"Type": 2,
		"Description": "数据传输服务(DTS)操作权限含获取消息队列(CKafka)实例列表，添加、删除 CKafka 内网路由信息等。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 4522633,
		"PolicyName": "QcloudAccessForEMRRole",
		"AddTime": "2018-08-28 17:25:16",
		"Type": 2,
		"Description": "弹性MapReduce（EMR）对云资源的访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 1938071,
		"PolicyName": "QcloudAccessForIOTSOLSRole",
		"AddTime": "2018-04-11 11:26:26",
		"Type": 2,
		"Description": "物联网解决方案(IOTSOLS)对云资源的跨服务访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "iotcloud"
	}, {
		"PolicyId": 9319432,
		"PolicyName": "QcloudAccessForIPAMDofTKERole",
		"AddTime": "2018-12-20 12:51:13",
		"Type": 2,
		"Description": "容器服务IPAMD支持（TKE IPAMD）对云资源的访问权限",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}, {
		"PolicyId": 15636817,
		"PolicyName": "QcloudAccessForLVBRole",
		"AddTime": "2019-04-11 17:08:55",
		"Type": 2,
		"Description": "腾讯云直播(LVB)操作权限含日志服务(CLS)的增删改查日志集，增删改查日志主题，搜索下载上传日志。",
		"CreateMode": 2,
		"Attachments": 0,
		"ServiceType": "cooperator"
	}],
	"ServiceTypeList": [],
	"RequestId": "e945caf3-04f1-41b2-bf47-676ed539cf6b"
}
             */
		}catch(Exception e) {
			log.error("查询策略列表服务异常：errorMessage={}",e);
		}
	}
}
