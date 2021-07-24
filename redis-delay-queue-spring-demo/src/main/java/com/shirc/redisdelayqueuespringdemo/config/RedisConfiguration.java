package com.shirc.redisdelayqueuespringdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther wuxuefeng
 * @Date 2021/6/12
 **/
@Configuration
public class RedisConfiguration {
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.cluster.timeout}")
    private Long timeout;
    @Value("${spring.redis.cluster.max-redirects}")
    private int redirects;
    @Value("${spring.redis.cluster.password}")
    private String password;
    @Value("2048")
    private int maxTotal;
    @Value("200")
    private int maxIdle;
    @Value("10")
    private int minIdle;

    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put("spring.redis.cluster.nodes", clusterNodes.trim());
        config.put("spring.redis.cluster.timeout", timeout);
        config.put("spring.redis.cluster.max-redirects",8);
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", config));
    }
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(2048);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(10);
//        jedisPoolConfig.setMaxWaitMillis(redisPoolConfig.getMaxWaitMillis());
//        jedisPoolConfig.setTestOnBorrow(redisPoolConfig.getTestOnBorrow());
//        jedisPoolConfig.setTestWhileIdle(redisPoolConfig.getTestWhileIdle());
//        jedisPoolConfig.setTestOnReturn(redisPoolConfig.getTestOnReturn());
//        jedisPoolConfig.setBlockWhenExhausted(redisPoolConfig.getBlockWhenExhausted());
//        jedisPoolConfig.setNumTestsPerEvictionRun(redisPoolConfig.getNumTestsPerEvictionRun());
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisPoolConfig.getTimeBetweenEvictionRunsMillis());
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisPoolConfig.getMinEvictableIdleTimeMillis());
        return jedisPoolConfig;
    }
    @Bean
    public RedisConnectionFactory connectionFactory(RedisClusterConfiguration redisClusterConfig,
                                                    @Qualifier("jedisPoolConfig") JedisPoolConfig pooConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfig);
        jedisConnectionFactory.setPoolConfig(pooConfig);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
