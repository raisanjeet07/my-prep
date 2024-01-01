package com.san.test.pubsub;

public class OrderSubcriber extends AbstractSubscribers {

    public OrderSubcriber(String topic){
        super(topic);
    }
    @Override
    public void update(String data) {
        System.out.println("Got order Event.... : "+ data);
    }
}
