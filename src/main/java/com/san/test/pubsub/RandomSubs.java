package com.san.test.pubsub;

public class RandomSubs extends AbstractSubscribers{

    public RandomSubs(String topic){
        super(topic);
    }
    @Override
    public void update(String data) {
        System.out.println("i am random..." + data);
    }
}
