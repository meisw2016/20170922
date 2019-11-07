package cn.springcloud.meisw.jpa.conf.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

 import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
public class DataSourceAtomikosConfig {
	
	private static final Logger log = LoggerFactory.getLogger(DataSourceAtomikosConfig.class);
	
	@Primary
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
	public DataSource primaryDataSource() {
		log.info("{}", "primary db built");
		// return DataSourceBuilder.create().build();
		return new AtomikosDataSourceBean();
	}
	
	@Bean(name = "secondDataSource")
	@Qualifier("secondDataSource")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.secondary")
	public DataSource secondaryDataSource() {
		log.info("{}", "secondary db built");
		// return DataSourceBuilder.create().build();
		return new AtomikosDataSourceBean();
	}
	
	// @Bean(name = "primaryJdbcTemplate")
	// public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
	// return new JdbcTemplate(dataSource);
	// }
	//
	// @Bean(name = "secondaryJdbcTemplate")
	// public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
	// return new JdbcTemplate(dataSource);
	// }
}
