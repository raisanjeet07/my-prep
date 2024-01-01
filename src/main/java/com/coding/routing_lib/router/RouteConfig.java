package com.coding.routing_lib.router;

public class RouteConfig {
    String path;
    Method method;
    Handler handler;

    public RouteConfig(String path, Method method, Handler handler) {
        this.path = path;
        this.method = method;
        this.handler = handler;
    }
}
