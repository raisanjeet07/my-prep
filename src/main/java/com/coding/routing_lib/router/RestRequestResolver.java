package com.coding.routing_lib.router;

import java.util.ArrayList;
import java.util.List;

public class RestRequestResolver extends AbstractRequestResolver{

    private final List<Route> routes;

    public RestRequestResolver(String serveFor, List<RouteConfig> routeConfigs){
        super(serveFor);
        this.routes = new ArrayList<>();
        initRoutes(routeConfigs);
    }

    private void initRoutes(List<RouteConfig> routeConfigs){
        routeConfigs.forEach(routeConfig -> {
            routes.add(new Route(routeConfig.path, routeConfig.method, routeConfig.handler));
        });
    }

    @Override
    public Handler resolveRequest(Request request) {
        PathChain reqPathChain = new PathChain(request.getUri());
        Route matchedRoute = getPathChainFromMatchedRoute(request.getMethod(), reqPathChain);
        return matchedRoute.getHandler();
    }

    private Route getPathChainFromMatchedRoute(Method method, PathChain reqPathChain){
        return routes.stream()
                .filter(route -> route.matchMethod(method))
                .filter(route -> route.getChain().isPathMatching(reqPathChain))
                .findFirst()
                .orElse(null);
    }

}
