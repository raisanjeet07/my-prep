package com.san.ds;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping(value = "test")
    public Map<Integer, byte[]> data(){
        Map<Integer, byte[]> map = new HashMap<>();
        map.put(1, "Sanjeet".getBytes(StandardCharsets.UTF_8));
        return  map;
    }
}
