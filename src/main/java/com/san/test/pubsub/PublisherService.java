package com.san.test.pubsub;

public class PublisherService {
    static TopicManager topicManager = new TopicManager();

    public static void publish(String topic, String data){
        topicManager.event(new Topic(topic), data);
    }

    public static void subscribe(String topic, Subscribers subscribersIns){
        topicManager.subscribe(new Topic(topic), subscribersIns);
    }
}
