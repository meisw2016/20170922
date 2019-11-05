//package cn.springcloud.meisw.jpa.conf.db;
//
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = {
//        "cn.springcloud.meisw.jpa.db1.dao"}, entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary")
//@Repository
//public class PrimaryConfig extends BaseConfiguration {
//	
//	@Autowired
//	@Qualifier("primaryDataSource")
//	private DataSource primaryDataSource;
//	
////	@Primary
//	@Bean(name = "entityManagerPrimary")
//	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
//	}
//	
//	/**
//	 * 2.配置EntityManagerFactory
//	 *
//	 * @return
//	 */
////	@Primary
//	@Bean(name = "entityManagerFactoryPrimary")
//	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(primaryDataSource).properties(getVendorProperties(primaryDataSource))
//		        .packages("cn.springcloud.meisw.jpa.db1.po").persistenceUnit("primaryPersistenceUnit").build();
//	}
//	
//	@Autowired
//	private JpaProperties jpaProperties;
//	
//	public Map<String, Object> getVendorProperties(DataSource dataSource) {
//		return jpaProperties.getHibernateProperties(new HibernateSettings());
//	}
//	
//	/**
//	 * 3.事务管理器配置
//	 *
//	 * @return
//	 */
//	@Bean(name = "transactionManagerPrimary")
////	@Primary
//	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
//	}
//}
