package cn.springcloud.meisw.jpa.conf.db;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@DependsOn("transactionManager")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFacotrySecondary", transactionManagerRef = "transactionManager", basePackages = {
        "cn.springcloud.meisw.jpa.db2.dao"})
@Repository
public class SecondaryConfigAtomikos {
	
	@Autowired(required = true)
	@Qualifier(value = "secondDataSource")
	private DataSource secondaryDataSource;
	
	@Autowired
	private JpaProperties jpaProperties;
	
	@Bean(name = "entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondaryDataSource).properties(getVendorProperties(secondaryDataSource))
		        .packages("cn.springcloud.meisw.jpa.db2.po").persistenceUnit("secondaryPersistenceUnit").jta(true).build();
	}
	
	private Map<String, Object> getVendorProperties(DataSource dataSource) {
		Map<String, Object> jpamap = jpaProperties.getHibernateProperties(new HibernateSettings());
		jpamap.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		return jpamap;
	}
}
