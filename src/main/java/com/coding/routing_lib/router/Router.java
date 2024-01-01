package com.coding.routing_lib.router;


import java.util.*;

public class Router {

    private final Map<String, RequestResolver> requestResolverMap;

    private Router(List<RequestResolver> requestResolvers){
        this.requestResolverMap = new HashMap<>();
        initMap(requestResolvers);
    }

    private void initMap(List<RequestResolver> requestResolvers){
        requestResolvers.forEach(requestResolver -> requestResolverMap.put(requestResolver.getFor(), requestResolver));
    }

    public Response route(Request request){
        Handler handler = getHttpRequestResolver(request).resolveRequest(request);
        Response res = new Response();
        handler.handle(request, res);
        return res;
    }

    private RequestResolver getHttpRequestResolver(Request request){
        return requestResolverMap.get("REST");
    }

    public static RouterBuilder builder(){
        return new RouterBuilder();
    }
    public static class RouterBuilder{
        List<RouteConfig> configs = new ArrayList<>();

        public RouterBuilder addRoute(RouteConfig routeConfig){
            configs.add(routeConfig);
            return this;
        }

        public Router build(){
            return new Router(List.of(getRequestResolver()));
        }

        private RequestResolver getRequestResolver(){
            return new RestRequestResolver("REST", configs);
        }
    }
}
