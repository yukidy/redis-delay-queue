package com.shirc.redisdelayqueuespringdemo.config;

import com.shirc.redis.delay.queue.core.RedisDelayQueueContext;
import com.shirc.redis.delay.queue.iface.RedisDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author: 唐晓东
 * @since: 10:17 2021/7/23
 * @version: v1.0
 * @description: 接入Redis_Delay_Queue
 */
@Component
public class BeanConfig {


    @Value("${delay.queue.project-name}")
    private String projectName;

    private RedisTemplate redisTemplate;


    /**修改 redisTemplate 的key序列化方式  **/
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }


    /******* 接入 RedisDelayQueue  *******/

    @Bean
    public RedisDelayQueueContext getRdctx(){
        RedisDelayQueueContext context =  new RedisDelayQueueContext(redisTemplate, projectName);
        return context;
    }

    @Bean
    public RedisDelayQueue getRedisOperation(RedisDelayQueueContext context){
        return context.getRedisDelayQueue();
    }



}
