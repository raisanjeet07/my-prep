package com.coding.routing_lib.router;

public interface RequestResolver {
    Handler resolveRequest(Request request);

    default String getFor(){
        return "default";
    }
}
