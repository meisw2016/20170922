package cn.springcloud.meisw.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * @author Administrator
 *
 */
@Component
public class KafkaConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@KafkaListener(topics = "test_topic")
	public void listen(ConsumerRecord<?, ?> record) throws Exception {
		System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
		logger.info("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
	}
}
