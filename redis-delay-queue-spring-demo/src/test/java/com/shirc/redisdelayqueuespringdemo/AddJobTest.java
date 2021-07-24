package com.shirc.redisdelayqueuespringdemo;

import com.shirc.redis.delay.queue.common.RunTypeEnum;
import com.shirc.redis.delay.queue.iface.RedisDelayQueue;
import com.shirc.redisdelayqueuespringdemo.bo.MyArgs;
import com.shirc.redisdelayqueuespringdemo.delayqueues.TopicEnums;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author: 唐晓东
 * @since: 16:26 2021/7/22
 * @version: v1.0
 * @description: 性能测试
 */
@SpringBootTest
public class AddJobTest {

    @Autowired
    private RedisDelayQueue redisDelayQueue;

    @Test
    public void redisTest() {
        for (int i = 0; i < 10000; i++) {
            Integer type = null;
            Long rt = null;
            if(rt ==null){
                rt = System.currentTimeMillis() + 30000;
            }
            MyArgs myArgs = new MyArgs();
            String id = UUID.randomUUID().toString();
            myArgs.setId(id);
            myArgs.setPutTime(new Date());
            myArgs.setShoudRunTime(new Date(rt));
            myArgs.setContent("lalalalala");
            redisDelayQueue.add(myArgs, TopicEnums.DEMO_TOPIC.getTopic(), rt, type==null? RunTypeEnum.ASYNC:RunTypeEnum.SYNC);
        }
    }

}
