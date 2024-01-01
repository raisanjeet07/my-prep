package com.pubsub;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TopicManager {
    private final Map<String, Set<Subscriber>> subscriberMap = new HashMap<>();

    public void subscribe(String topic, Subscriber subscriber){
        subscriberMap.put(topic, subscriberMap.getOrDefault(topic, ConcurrentHashMap.newKeySet()));
        subscriberMap.get(topic).add(subscriber);
    }

    public boolean unSubscribe(String topic, Subscriber subscriber){
        if(subscriberMap.containsKey(topic)){
            return subscriberMap.get(topic).remove(subscriber);
        }
        return true;
    }

    public Stream<Subscriber> getSubscriberStreamForTopic(String topic){
        if(subscriberMap.containsKey(topic))
            return subscriberMap.get(topic).stream();
        return null;
    }
}
