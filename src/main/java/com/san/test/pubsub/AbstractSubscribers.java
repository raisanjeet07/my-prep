package com.san.test.pubsub;

public abstract class AbstractSubscribers implements Subscribers{
    String topic;
    public AbstractSubscribers(String topic){
        this.topic = topic;
        PublisherService.subscribe(topic, this);
    }
}
