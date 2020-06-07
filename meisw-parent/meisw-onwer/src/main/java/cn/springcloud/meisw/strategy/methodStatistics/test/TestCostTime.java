package cn.springcloud.meisw.strategy.methodStatistics.test;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.springcloud.meisw.strategy.methodStatistics.CostTime;

@Component
public class TestCostTime {
	
	
	
	@CostTime
	public void testCostTime() throws InterruptedException{
		for(int i =0;i<10;i++){
			if(i%2==0){
				Thread.sleep(100);
			}
		}
	}
	
	@PostConstruct
	public void init(){
		try {
			testCostTime();
        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
}
