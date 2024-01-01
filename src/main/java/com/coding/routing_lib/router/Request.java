package com.coding.routing_lib.router;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Method method;
    private String uri;
    private Map<String, String> headers;
    private Map<String, String> pathParams;
    private Map<String, String> queryParams;
    private String payload;
}
