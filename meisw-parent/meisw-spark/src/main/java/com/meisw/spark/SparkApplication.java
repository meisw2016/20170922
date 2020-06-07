//package com.meisw.spark;
//
//import java.util.List;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.hive.HiveContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.annotation.Bean;
//
///**
// * eureka server
// */
//@SpringBootApplication
//@EnableDiscoveryClient
//public class SparkApplication implements CommandLineRunner{
//
//	@Autowired
//    private HiveContext hc;
//	
//    public static void main(String[] args) {
//        SpringApplication.run(SparkApplication.class, args);
//    }
//    
//
//	@Override
//    public void run(String... args) throws Exception {
//		Dataset df = hc.sql("select count(1) from LCS_DB.STAFF_INFO");
//		List<Long> result = df.javaRDD().map((Function<Row, Long>) row -> {
//            return row.getLong(0);
//        }).collect();
//        result.stream().forEach(System.out::println);    
//    }
//}