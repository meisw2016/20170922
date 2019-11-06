package cn.springcloud.meisw.jpa.conf.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Primary
	@Bean(name = "firstDataSource")
	@Qualifier("firstDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource firstDataSource() {
		log.info("{}", "primary db built");
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secondDataSource")
	@Qualifier("secondDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		log.info("{}", "secondary db built");
		return DataSourceBuilder.create().build();
	}
}
