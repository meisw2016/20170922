//package cn.springcloud.meisw.jpa.conf.db;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager", basePackages = {
//        "cn.springcloud.meisw.jpa.db2.dao"})
//public class SecondConfig {
//	
//	@Autowired
//	@Qualifier("secondDataSource")
//	private DataSource secondDataSource;
//	
//	@Bean(name = "secondEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//		Map<String, String> map = new HashMap<String, String>();
//		return builder.dataSource(secondDataSource).packages("cn.springcloud.meisw.jpa.db2.po").properties(map)
//		        .persistenceUnit("secondPersistenceUnit").build();
//	}
//	
//	@Bean(name = "secondEntityManager")
//	public EntityManager secondEntityManager(EntityManagerFactoryBuilder builder) {
//		return secondEntityManagerFactory(builder).getObject().createEntityManager();
//	}
//	
//	@Bean(name = "secondTransactionManager")
//	public PlatformTransactionManager secondTransactionManager(EntityManagerFactoryBuilder builder) {
//		return new JpaTransactionManager(secondEntityManagerFactory(builder).getObject());
//	}
//}
