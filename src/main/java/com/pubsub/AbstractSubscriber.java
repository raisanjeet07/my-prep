package com.pubsub;

public abstract class AbstractSubscriber implements Subscriber{

    private PubSubService pubSubService;

    private String clientId;

    public  AbstractSubscriber(PubSubService pubSubService, String clientId){
        this.pubSubService = pubSubService;
        this.clientId = clientId;
    }
    @Override
    public void subscribe(String topic) {
        this.pubSubService.subscribe(topic, this);
    }

    @Override
    public void unSubscribe(String topic) {
        this.pubSubService.unSubscribe(topic, this);
    }

    @Override
    public String getClientId(){
        return clientId;
    }
}
