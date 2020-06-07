package com.meisw.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkContextBean {
	
	private String appName = "sparkExp";
	private String  master = "local";
	
	@Bean
	@ConditionalOnMissingBean(SparkConf.class)
	public SparkConf sparkConf()throws Exception{
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        return conf;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public JavaSparkContext javaSparkContext()throws Exception{
		return new JavaSparkContext(sparkConf());
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HiveContext hiveContext() throws Exception{
		return new HiveContext(javaSparkContext());
	}
}
