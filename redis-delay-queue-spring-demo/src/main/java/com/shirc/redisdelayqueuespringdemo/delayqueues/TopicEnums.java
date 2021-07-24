package com.shirc.redisdelayqueuespringdemo.delayqueues;

/**
 * @Description TODO
 * @Author shirenchuang
 * @Date 2019/7/31 12:23 PM
 **/
public enum  TopicEnums  {

    DELAY_QUEUE_TEST_TOPIC("DELAY_QUEUE_TOPIC", "第一个线上测试TOPIC"),
    DEMO_TOPIC("DEMO_TOPIC","第一个测试TOPIC"),
    DEMO_TOPIC_2("DEMO_TOPIC_2","第二个测试TOPIC"),

    ;

    public String topic;

    public String desc;

    TopicEnums(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public String getDesc() {
        return desc;
    }



}
