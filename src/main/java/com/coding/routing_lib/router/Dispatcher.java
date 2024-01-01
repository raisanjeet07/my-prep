package com.coding.routing_lib.router;

public class Dispatcher {

    public static void main(String[] args) {
        Router router = Router.builder()
                .addRoute(new RouteConfig("/api/v1/test", Method.GET, (req, res) -> {
                    System.out.println("test api called : "+ req.getUri());
                }))
                .addRoute(new RouteConfig("/api/v1/test2", Method.GET, (req, res) -> {
                    System.out.println("test api called : " + req.getUri());
                }))
                .addRoute(new RouteConfig("/api/v1/test3", Method.GET, (req, res) -> {
                    System.out.println("test api called : "+ req.getUri());
                }))
                .addRoute(new RouteConfig("/api/v1/test4", Method.GET, (req, res) -> {
                    System.out.println("test api called : "+ req.getUri());
                }))
                .build();

        Request req = Request.builder().uri("/api/v1/test2").method(Method.GET).build();

        Response res = router.route(req);
        System.out.println(res);
    }
}
