package com.shirc.redis.delay.queue.iface;

import com.shirc.redis.delay.queue.common.Args;

/**
 * @author: 唐晓东
 * @since: 9:58 2021/7/23
 * @version: v1.0
 * @description: 回调接口
 */
public interface CallBack<T extends Args> {

    /**
     * 执行回调接口
     * @param t
     */
    public void execute(T t);


    /**
     * 重试超过2次(总共3次)回调接口;
     * 消费者可以在这个方法里面发送钉钉警告邮件警告等等
     * 回调这个接口是一个单独的线程
     *  @param t
     */
    public void retryOutTimes(T t);


}
