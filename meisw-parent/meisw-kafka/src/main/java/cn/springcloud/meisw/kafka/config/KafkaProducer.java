package cn.springcloud.meisw.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 生产者
 *
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("send")
	public String send(String msg) {
		kafkaTemplate.send("test_topic", msg);
		return "success";
	}
}
