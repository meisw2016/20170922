//package cn.springcloud.meisw.jpa.conf.db;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class BaseConfiguration {
//	
//	@Autowired
//	protected Environment env;
//	
//	@Bean
//	public HibernateExceptionTranslator hibernateExceptionTranslator(){
//		return new HibernateExceptionTranslator();
//	}
//	
//	protected Properties hibernateProperties(){
//		Properties properties = new Properties();
//		//方言
//		properties.put("spring.jpa.properties.hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
//		//名字策略
//		properties.put("spring.jpa.hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
//		return properties;
//	}
//}
