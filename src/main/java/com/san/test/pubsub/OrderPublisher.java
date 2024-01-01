package com.san.test.pubsub;

public class OrderPublisher {
    public void createOrderAndSend(String topic){
        PublisherService.publish(topic, "new Order Created");
    }
}
