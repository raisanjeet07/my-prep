package com.coding.routing_lib.router;

public abstract class AbstractRequestResolver implements RequestResolver{

    private String serveFor;

    public AbstractRequestResolver(String serveFor){
        this.serveFor = serveFor;
    }
    public String getFor() {
        return serveFor;
    }
}
