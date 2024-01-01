package com.pubsub;

import java.util.Date;

public class Record {
    private String topic;
    private String data;
    private Date date = new Date();
    public Record(String topic, String data) {
        this.topic = topic;
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "topic=\"" + topic + "\"" +
                ", date=" + date.getTime() +
                ", data=\"" + data + "\"" +
                '}';
    }
}
