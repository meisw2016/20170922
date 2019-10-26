/*package cn.springcloud.meisw.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import cn.springcloud.meisw.jpa.conf.redis.RedisHelperImpl;
import cn.springcloud.meisw.jpa.po.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelperImpl redisHelper;

    @Test
    public void test() throws Exception{
//        基本写法
//        stringRedisTemplate.opsForValue().set("aaa","111");
//        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        User user=new User();
        user.setAge(10);
        user.setName("不会打篮球的程序不是好男人");
        redisHelper.valuePut("aaa",user);
        System.out.println(redisHelper.getValue("aaa"));
    }

    @Test
    public void testObj() throws Exception {
    	User user=new User();
        user.setAge(20);
        user.setName("不会打篮球的程序不是好男人!");

        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("502", user);
        Thread.sleep(500);
        boolean exists=redisTemplate.hasKey("502");
        if(exists){
            System.out.println(redisTemplate.opsForValue().get("502"));
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
*/