package cn.springcloud.boot.eureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.springcloud.meisw.strategy.methodStatistics.test.TestCostTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTest {
	
	@Autowired
	private TestCostTime testCostTime;
	
	@Test
	public void test(){
		try {
	        testCostTime.testCostTime();
        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
}
