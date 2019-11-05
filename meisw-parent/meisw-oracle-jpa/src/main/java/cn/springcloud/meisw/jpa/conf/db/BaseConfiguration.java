//package cn.springcloud.meisw.jpa.conf.db;
//
//
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//public class BaseConfiguration {
//	
////	@Autowired
////	protected Environment env;
////	
////	@Bean
////	public HibernateExceptionTranslator hibernateExceptionTranslator(){
////		return new HibernateExceptionTranslator();
////	}
////	
////	protected Properties hibernateProperties(){
////		Properties properties = new Properties();
////		//方言
////		properties.put("spring.jpa.properties.hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
////		//名字策略
////		properties.put("spring.jpa.hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
////		return properties;
////	}
//	
//	private static final Logger log = LoggerFactory.getLogger(BaseConfiguration.class);
//	
//	@Bean(name = "primaryDataSource")
//	@Qualifier("primaryDataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource.primary")
//	public DataSource primaryDataSource() {
//		log.info("创建primary datasource...");
//		return DataSourceBuilder.create().build();
//	}
//	@Bean(name = "secondaryDataSource")
//	@Qualifier("secondaryDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.secondary")
//	public DataSource secondaryDataSource() {
//		log.info("创建secondary datasource...");
//		return DataSourceBuilder.create().build();
//	}
//	@Bean(name = "primaryJdbcTemplateDataSource")
//	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
//		log.info("创建primary  jdbcTemplate datasource...");
//		return new JdbcTemplate(dataSource);
//	}
//	@Bean(name = "secondaryJdbcTemplateDataSource")
//	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
//		log.info("创建secondary  jdbcTemplate datasource...");
//		return new JdbcTemplate(dataSource);
//	}
//}
