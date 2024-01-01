package com.pubsub;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventRecordDataStore eventRecordDataStore = new EventRecordDataStore();
        TopicManager topicManager = new TopicManager();

        PubSubService pubSubService = new PubSubService(eventRecordDataStore, topicManager);
        String topic = "qwer";
        String topic2 = "31312";
        Subscriber subscriber1 = new AbstractSubscriber(pubSubService, "client1") {
            @Override
            public void update(Record data) {
                System.out.println("subscriberId:" + this.getClientId() + ", consumedData: "+data);
            }
        };
        subscriber1.subscribe(topic);
        subscriber1.subscribe(topic2);
        pubSubService.publish(topic, "my 1st data");
        pubSubService.publish(topic2, "my 1st data : topic 2");
        Thread.sleep(1000);
        subscriber1.unSubscribe(topic);
        pubSubService.publish(topic, "my 1st data");
        pubSubService.publish(topic2, "my 1st data : topic 2");
    }
}
