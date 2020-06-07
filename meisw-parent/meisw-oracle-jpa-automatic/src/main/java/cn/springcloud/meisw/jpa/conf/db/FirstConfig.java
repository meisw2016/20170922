package cn.springcloud.meisw.jpa.conf.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager", basePackages = {
        "cn.springcloud.meisw.jpa.db1.dao"})
public class FirstConfig {
	
	@Autowired
	@Qualifier("firstDataSource")
	private DataSource firstDataSource;
	
	@Primary
	@Bean(name = "firstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String,String> map = new HashMap<String,String>();
		return builder.dataSource(firstDataSource).packages("cn.springcloud.meisw.jpa.db1.po").properties(map).persistenceUnit("firstPersistenceUnit").build();
	}
	
	@Primary
	@Bean(name = "firstEntityManager")
	public EntityManager firstEntityManager(EntityManagerFactoryBuilder builder) {
		return firstEntityManagerFactory(builder).getObject().createEntityManager();
	}
	
	@Primary
	@Bean(name = "firstTransactionManager")
	public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(firstEntityManagerFactory(builder).getObject());
	}
}
