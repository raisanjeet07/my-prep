package com.design_patterns.creational.builder;

public class BuilderDesignPatterns {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest.builder()
                .addHeader("Content-Type", "xml/json")
                .addHeader("authorization", "bearer 125nmbk24kb23bk235b245k234lkn234lk2")
                .method("POST")
                .url("http://localhost:8080/api/v1/controller/sy-hello")
                .payload("123123123234")
                .build();
        System.out.println(request);
    }
}
