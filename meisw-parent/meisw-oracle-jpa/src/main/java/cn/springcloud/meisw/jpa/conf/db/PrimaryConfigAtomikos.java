package cn.springcloud.meisw.jpa.conf.db;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@DependsOn("transactionManager")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManager", basePackages = {
        "cn.springcloud.meisw.jpa.db1.dao"})
@Repository
public class PrimaryConfigAtomikos {
	
	@Autowired(required = true)
	@Qualifier(value = "primaryDataSource")
	private DataSource primaryDataSource;
	
	@Primary
	@Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource).properties(getVendorProperties(primaryDataSource))
		        .packages("cn.springcloud.meisw.jpa.db1.po").persistenceUnit("primaryPersistenceUnit").jta(true).build();
	}
	
	@Autowired
	private JpaProperties jpaProperties;
	
	private Map<String, Object> getVendorProperties(DataSource dataSource) {
		Map<String, Object> jpamap = jpaProperties.getHibernateProperties(new HibernateSettings());
		jpamap.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		return jpamap;
	}
}
