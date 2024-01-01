package com.pubsub;

public interface Subscriber {
    void subscribe(String topic);

    void unSubscribe(String topic);

    void update(Record data);

    String getClientId();
}
