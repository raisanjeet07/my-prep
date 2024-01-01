package com.design_patterns.creational.builder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String, Object> headers;
    private String method;
    private String payload;
    private String url;

    public Map<String, Object> getHeaders() {
        return new HashMap<>(headers);
    }

    public String getMethod() {
        return method;
    }

    public String getPayload() {
        return payload;
    }

    public String getUrl() {
        return url;
    }

    private HttpRequest(String url, String method, Map<String, Object> headers, String payload){
        this.headers = headers;
        this.method = method;
        this.url = url;
        this.payload = payload;
    }

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public static HttpRequestBuilder builder(){
        return new HttpRequestBuilder();
    }

    static class HttpRequestBuilder{
        private Map<String, Object> headers;
        private String method;
        private String payload;
        private String url;

        private HttpRequestBuilder(){
            headers = new HashMap<>();
        }

        public HttpRequestBuilder addHeader(String key, String value){
            headers.put(key, value);
            return this;
        }

        public HttpRequestBuilder url(String url){
            this.url = url;
            return this;
        }

        public HttpRequestBuilder payload(String payload){
            this.payload = payload;
            return this;
        }

        public HttpRequestBuilder method(String method){
            this.method = method;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this.url, this.method, this.headers, this.payload);
        }
    }
}


