package com.shirc.redis.delay.queue.common;

/**
 * @author: 唐晓东
 * @since: 19:06 2021/7/22
 * @version: v1.0
 * @description: 延时队列异常
 */
public class DelayQueueException extends RuntimeException {

    public DelayQueueException(String message) {
        super(message);
    }
}
