//package cn.springcloud.meisw.jpa.conf.db;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"cn.springcloud.meisw.jpa.db1.dao"}, entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary")
//public class PrimaryConfig extends BaseConfiguration {
//	
//	/**
//	 * 1.配置主数据源
//	 *
//	 * @return DataSource
//	 */
//	@Bean(name = "primaryDataSource")
//	@Primary
//	@Qualifier("primaryDataSource")
//	public DataSource primaryDataSource() {
//		DruidDataSource source = new DruidDataSource();
//		source.setDriverClassName(env.getRequiredProperty("spring.datasource.driver"));
//		source.setUrl(env.getRequiredProperty("spring.datasource.url"));
//		source.setUsername(env.getRequiredProperty("spring.datasource.username"));
//		source.setPassword(env.getRequiredProperty("spring.datasource.password"));
//		try {
//			source.addFilters("log4j");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return source;
//	}
//	 /**
//     * 2.配置EntityManagerFactory
//     *
//     * @return
//     */
//       @Primary
//    @Bean(name = "entityManagerFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        // 配置数据源
//        factory.setDataSource(primaryDataSource());
//        // VendorAdapter
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        // entity包扫描路径
//        factory.setPackagesToScan(env.getRequiredProperty("packages.to.scan"));
//        // jpa属性
//        factory.setJpaProperties(hibernateProperties());
//        factory.afterPropertiesSet();
//        return factory;
//    }
//       /**
//     * 3.事务管理器配置
//     *
//     * @return
//     */
//    @Bean(name="transactionManagerPrimary")
//    @Primary
//    public PlatformTransactionManager transactionManagerPrimary() {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactoryPrimary().getObject());
//        return manager;
//    }
//}
