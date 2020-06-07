private static final Logger log = LoggerFactory.getLogger(this.class);
public static void main(String[] args){
	try{
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
            httpProfile.setEndpoint("cvm.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
	}catch(Exception e){
		log.error(""+e.getMessage());
	}
}



            
            
            
https://cam.api.qcloud.com/v2/index.php
?policyName=testpppName323
&policyDocument=%7B%22version%22%3A%222.0%22%2C%22statement%22%3A%5B%7B%22action%22%3A%22cvm%3A%2A%22%2C%22effect%22%3A%22allow%22%2C%22resource%22%3A%22%2A%22%7D%5D%7D
&SignatureMethod=HmacSHA256
&Action=CreatePolicy
&Region=gz
&SecretId=
&Nonce=19057
&Timestamp=1508489703
&RequestClient=SDK_PHP_1.1
&Signature=7mTuDFCaxt8Ki%2FtnEpvws%2BvoWcMPi1zESHpPXMElSQc%3D