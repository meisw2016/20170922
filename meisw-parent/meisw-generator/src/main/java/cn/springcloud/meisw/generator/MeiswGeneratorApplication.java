package cn.springcloud.meisw.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午2:43:55
 * @ClassName MeiswGeneratorApplication
 * @Description 自动生成代码工具启动类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MeiswGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswGeneratorApplication.class, args);
    }
}