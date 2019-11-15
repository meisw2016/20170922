package cn.springcloud.meisw.jpa.conf.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * MyProperties
 * </p>
 *
 * @description :
 * @date : 2019/8/19 9:07
 */
// @ConfigurationProperties(prefix = "meisw", ignoreUnknownFields = false)
@Component
@PropertySource("classpath:bootstrap.yml")
@Configuration
@ComponentScan(basePackages = {"cn.springcloud.meisw.jpa.conf.security"})
public class MyProperties {
	
	/**
	 * SWAGGER参数
	 */
	private final Swagger swagger = new Swagger();
	/**
	 * 安全认证
	 */
	private final Auth auth = new Auth();
	
	public Swagger getSwagger() {
		return swagger;
	}
	
	public Auth getAuth() {
		return auth;
	}
	
	/**
	 * SWAGGER接口文档参数
	 */
	public static class Swagger {
		
		@Value("${meisw.swagger.title}")
		private String title;
		@Value("${meisw.swagger.description}")
		private String description;
		@Value("${meisw.swagger.version}")
		private String version;
		@Value("${meisw.swagger.termsOfServiceUrl}")
		private String termsOfServiceUrl;
		@Value("${meisw.swagger.contactName}")
		private String contactName;
		@Value("${meisw.swagger.contactUrl}")
		private String contactUrl;
		@Value("${meisw.swagger.contactEmail}")
		private String contactEmail;
		@Value("${meisw.swagger.license}")
		private String license;
		@Value("${meisw.swagger.licenseUrl}")
		private String licenseUrl;
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getVersion() {
			return version;
		}
		
		public void setVersion(String version) {
			this.version = version;
		}
		
		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}
		
		public void setTermsOfServiceUrl(String termsOfServiceUrl) {
			this.termsOfServiceUrl = termsOfServiceUrl;
		}
		
		public String getContactName() {
			return contactName;
		}
		
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
		
		public String getContactUrl() {
			return contactUrl;
		}
		
		public void setContactUrl(String contactUrl) {
			this.contactUrl = contactUrl;
		}
		
		public String getContactEmail() {
			return contactEmail;
		}
		
		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}
		
		public String getLicense() {
			return license;
		}
		
		public void setLicense(String license) {
			this.license = license;
		}
		
		public String getLicenseUrl() {
			return licenseUrl;
		}
		
		public void setLicenseUrl(String licenseUrl) {
			this.licenseUrl = licenseUrl;
		}
		
	}
	
	public static class Auth {
		
		/**
		 * token过期时间（分钟）
		 */
		@Value("${meisw.auth.tokenExpireTime}")
		private Integer tokenExpireTime;
		/**
		 * 用户选择保存登录状态对应token过期时间（天）
		 */
		@Value("${meisw.auth.saveLoginTime}")
		private Integer saveLoginTime;
		/**
		 * 限制用户登陆错误次数（次）
		 */
		@Value("${meisw.auth.loginTimeLimit}")
		private Integer loginTimeLimit;
		/**
		 * 错误超过次数后多少分钟后才能继续登录（分钟）
		 */
		@Value("${meisw.auth.loginAfterTime}")
		private Integer loginAfterTime;
		/**
		 * 忽略安全认证的URL
		 */
		@Value("${meisw.auth.ignoreUrls}")
		private List<String> ignoreUrls;
		
		public Integer getTokenExpireTime() {
			return tokenExpireTime;
		}
		
		public void setTokenExpireTime(Integer tokenExpireTime) {
			this.tokenExpireTime = tokenExpireTime;
		}
		
		public Integer getSaveLoginTime() {
			return saveLoginTime;
		}
		
		public void setSaveLoginTime(Integer saveLoginTime) {
			this.saveLoginTime = saveLoginTime;
		}
		
		public Integer getLoginTimeLimit() {
			return loginTimeLimit;
		}
		
		public void setLoginTimeLimit(Integer loginTimeLimit) {
			this.loginTimeLimit = loginTimeLimit;
		}
		
		public Integer getLoginAfterTime() {
			return loginAfterTime;
		}
		
		public void setLoginAfterTime(Integer loginAfterTime) {
			this.loginAfterTime = loginAfterTime;
		}
		
		public List<String> getIgnoreUrls() {
			return ignoreUrls;
		}
		
		public void setIgnoreUrls(List<String> ignoreUrls) {
			this.ignoreUrls = ignoreUrls;
		}
		
	}
	
}
