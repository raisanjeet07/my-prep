package com.san.test.pubsub;

import lombok.SneakyThrows;

import java.util.*;

public class TopicManager {
    Map<String, List<Subscribers>> topicSubscribers = new HashMap<>();
    Queue<Record> records = new ArrayDeque<>();

    public TopicManager(){
        new Thread(new EventPublisher(records)).start();
    }
    public void event(Topic topic, String data){
        records.add(new Record(topic.topicName, data));
    }

    public void subscribe(Topic topic, Subscribers subscribers){
        topicSubscribers.put(topic.topicName, topicSubscribers.getOrDefault(topic.topicName, new ArrayList<>()));
        topicSubscribers.get(topic.topicName).add(subscribers);
    }

    class Record{
        String topic;
        String data;
        public Record(String topic, String data){
            this.topic = topic;
            this.data = data;
        }
    }


    class EventPublisher implements Runnable {

        Queue<Record> records;
        public EventPublisher(Queue<Record> queue){
            this.records = queue;
        }
        @SneakyThrows
        @Override
        public void run() {
            StringJoiner stringJoiner = new StringJoiner(",");
            while(true){
                if(records.size() > 0){
                    Record data = records.poll();
                    stringJoiner.add(data.data);
                    List<Subscribers> subscribers = topicSubscribers.get(data.topic);
                    if(subscribers != null)
                        subscribers.stream().forEach(subscribers1 -> subscribers1.update(data.data));
                }else System.out.println("no size");
                Thread.sleep(100);
                System.out.println("checking again + "+ stringJoiner.toString());
            }
        }
    }
}
