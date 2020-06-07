package com.meisw.spark.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WordMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		// 获得每行文档内容，并且进行折分
		String[] words = value.toString().split(" ");
 
		// 遍历折份的内容
		for (String word : words) {
			// 每出现一次则在原来的基础上：+1
			context.write(new Text(word), new LongWritable(1));
		}
	}
}
