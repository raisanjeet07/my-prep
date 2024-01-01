package com.coding.routing_lib.router;

public class Route {
    private String path;
    private Method method;
    private Handler handler;

    private int weight;
    private PathChain chain;

    public Handler getHandler() {
        return handler;
    }

    public PathChain getChain() {
        return chain;
    }

    public Route(String path, Method method, Handler handler){
        this.path = path;
        this.method = method;
        this.handler = handler;
        initRoute();
    }

    private void initRoute(){
        this.chain = new PathChain(this.path);
    }

    public PathChain getMatchedPathChain(PathChain pathChain){
        return this.chain.isPathMatching(pathChain) ? this.chain : null;
    }

    public boolean matchMethod(Method method){
        return this.method == method;
    }
}
