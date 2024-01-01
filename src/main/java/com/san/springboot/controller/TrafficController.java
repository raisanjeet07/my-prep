package com.san.springboot.controller;

import com.san.springboot.executor.ConcurrentRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class TrafficController {

    @Autowired
    ConcurrentRunner concurrentRunner;

    @PostConstruct
    public  void init(){
        System.out.println();
    }

    @GetMapping("/generate/{size}")
    public String generateTraffic(@PathVariable(value = "size") String batchSize){
        System.out.println(batchSize);
        concurrentRunner.runInBatches(0);
        return "OK";
    }
}
