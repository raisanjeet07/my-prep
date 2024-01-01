package com.pubsub;

import java.util.LinkedList;
import java.util.Queue;

public class EventRecordDataStore {
    private final Queue<Record> recordDequeue = new LinkedList<>();

    public void recordEvent(String topic, String data){
        if(topic != null && !"".equals(topic)){
            recordDequeue.add(new Record(topic, data));
        }
    }

    public boolean hasRecords(){
        return recordDequeue.size() > 0;
    }

    public Record getNext(){
        return recordDequeue.poll();
    }
}
