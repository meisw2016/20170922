//package cn.springcloud.meisw.jpa.work;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.Test;
//
///**
// * 线程池
// * @author Administrator
// *
// */
//public class ThreadPoolExecutor {
//	
//	/**
//	 * 缓存线程池
//	 */
//	@Test
//	public void cachedThreadPoo(){
//		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//	    for(int i=0;i<10;i++){
//	    	try{
//	    		Thread.sleep(1000);
//	    	}catch(InterruptedException e){
//	    		
//	    	}
//	    	cachedThreadPool.execute(new Runnable(){
//	    		public void run(){
//	    			System.out.println(Thread.currentThread().getName()+"正在被执行");
//	    		}
//	    	});
//	    }
//	}
//	
//	/**
//	 * 执行定时人物的线程池
//	 */
//	@Test
//	public void scheduledThreadPoo(){
//		//创建一个定长线程池，支持定时及周期性人物执行-----定期执行
//		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//		//延迟1秒后每3秒执行一次
//		scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
//			public void run(){
//				System.out.println("延迟1秒后每3秒执行一次");
//			}
//		}, 1, 3, TimeUnit.SECONDS);
//		while(true){
//			try {
//	            Thread.sleep(1000);
//            } catch (InterruptedException e) {
//	            e.printStackTrace();
//            }
//		}
//	}
//	
//	/**
//	 * 单线程线程池
//	 * 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
//	 */
//	@Test
//	public void singleThreadExecutorTest(){
//		//创建一个单线程的线程池
//		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//		for(int i =0; i<10;i++){
//			final int index = i;
//			singleThreadExecutor.execute(new Runnable(){
//				public void run(){
//					System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是："+index);
////					System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是：");
//					try {
//	                    Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//	                    e.printStackTrace();
//                    }
//				}
//			});
//		}
//	}
//}
