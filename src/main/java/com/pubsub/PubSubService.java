package com.pubsub;

import java.util.stream.Stream;

public class PubSubService {

    private EventRecordDataStore eventRecordDataStore;

    private TopicManager topicManager;

    public PubSubService(EventRecordDataStore eventRecordDataStore, TopicManager topicManager){
        this.topicManager = topicManager;
        this.eventRecordDataStore = eventRecordDataStore;
        this.startNotifier();
    }

    public void publish(String topic, String data){
        this.eventRecordDataStore.recordEvent(topic, data);
    }

    public void subscribe(String topic, Subscriber subscriber){
        this.topicManager.subscribe(topic, subscriber);
    }

    public void unSubscribe(String topic, Subscriber subscriber){
        this.topicManager.unSubscribe(topic, subscriber);
    }
    private void startNotifier() {
        new Thread(() -> {
            Stream<Subscriber> subsStream;
            while (true) {
                if (eventRecordDataStore.hasRecords()) {
                    Record record = eventRecordDataStore.getNext();
                    subsStream = topicManager.getSubscriberStreamForTopic(record.getTopic());
                    subsStream.forEach(subscriber -> subscriber.update(record));
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
