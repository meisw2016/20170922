//package cn.springcloud.meisw.jpa.conf.db;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"cn.springcloud.meisw.jpa.db2.dao"}, entityManagerFactoryRef = "entityManagerFactorySecondary", transactionManagerRef = "transactionManagerSecondary")
//public class SecondConfig extends BaseConfiguration {
//	
//	@Bean(name = "secondaryDataSource")
//	@Qualifier("secondaryDataSource")
//	public DataSource secondaryDataSource() {
//		DruidDataSource source = new DruidDataSource();
//		source.setDriverClassName(env.getRequiredProperty("spring.datasource.auth.driver"));
//		source.setUrl(env.getRequiredProperty("spring.datasource.auth.url"));
//		source.setUsername(env.getRequiredProperty("spring.datasource.auth.username"));
//		source.setPassword(env.getRequiredProperty("spring.datasource.auth.password"));
//		return source;
//	}
//	
//	@Bean(name = "entityManagerFactorySecondary")
//	@Qualifier("entityManagerFactorySecondary")
//	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary() {
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		// 配置数据源
//		factory.setDataSource(secondaryDataSource());
//		// VendorAdapter
//		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		// entity包扫描路径
//		factory.setPackagesToScan(env.getRequiredProperty("packages.to.scan.auth"));
//		// jpa属性
//		factory.setJpaProperties(hibernateProperties());
//		factory.afterPropertiesSet();
//		return factory;
//	}
//	
//	@Bean(name = "transactionManagerSecondary")
//	@Qualifier("transactionManagerSecondary")
//	public PlatformTransactionManager transactionManagerSecondary() {
//		JpaTransactionManager manager = new JpaTransactionManager();
//		manager.setEntityManagerFactory(entityManagerFactorySecondary().getObject());
//		return manager;
//	}
//}
