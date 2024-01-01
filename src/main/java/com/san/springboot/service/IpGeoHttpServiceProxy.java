package com.san.springboot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.san.springboot.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class IpGeoHttpServiceProxy {
    String url = "http://netacuity.clmbtech.com/netacuity/ip/";

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    OkHttpClient okHttpClient;

//    @Autowired
    CloseableHttpClient httpClient;

    @Autowired
    MeterRegistry meterRegistry;

    Timer a_timer = null;

    Timer o_timer = null;

    @PostConstruct
    public void init(){
        a_timer = Timer.builder("apache.time").publishPercentiles(0.5, 0.9, 0.95, 0.99, 0.999).register(meterRegistry);
        o_timer = Timer.builder("okhttp.time").publishPercentiles(0.5, 0.9, 0.95, 0.99, 0.999).register(meterRegistry);
    }

    public void getIpDetails(String ip, int choice){
        String url = this.url + ip;
        String flow = choice == 1? "APACHE" : "OKHTTP";
        long t1 = System.currentTimeMillis();
        try{
            switch (choice){
                case 1: apacheCall(url);
                    break;
                case 2: makeCall(url);
            }
            System.out.println("("+flow+") Total Time in ms: "+ (System.currentTimeMillis() - t1));
        }catch (Exception e){
            System.out.println("("+flow+") -------------Total Time in ms: "+ (System.currentTimeMillis() - t1)+ ". Error: " + e.getMessage());
        }
    }
//    @Timed(value = "apache.time", description = "Time taken to return greeting", percentiles = {0.5, 0.9, 0.95, 0.99, 0.999})
    public void getIpDetailsA(String ip){
        String url = this.url + ip;
        long t1 = System.currentTimeMillis();
        try{
            a_timer.record(() ->apacheCall(url));
            System.out.println("(APACHE) Total Time in ms: "+ (System.currentTimeMillis() - t1));
        }catch (Exception e){
            System.out.println("(APACHE) -------------Total Time in ms: "+ (System.currentTimeMillis() - t1)+ ". Error: " + e.getMessage());
        }
    }

//    @Timed(value = "okhttp.time", description = "Time taken to return greeting", percentiles = {0.5, 0.9, 0.95, 0.99, 0.999})
    public void getIpDetailsO(String ip){
        String url = this.url + ip;
        long t1 = System.currentTimeMillis();
        try{
            o_timer.record(() -> makeCall(url));
            System.out.println("(OKHTTP) Total Time in ms: "+ (System.currentTimeMillis() - t1));
        }catch (Exception e){
            System.out.println("(OKHTTP) -------------Total Time in ms: "+ (System.currentTimeMillis() - t1)+ ". Error: " + e.getMessage());
        }
    }

    private Map<String, String> makeCall(String url){
        try {
            Response response = okHttpClient.newCall(new Request.Builder().get().url(url).build()).execute();
            if (!response.isSuccessful()) throw new RuntimeException(response.message());
            ResponseBody responseBody = response.body();
            Map<String, String> responseObj = (responseBody == null) ? null : mapper.readValue(responseBody.string(), new TypeReference<Map<String, String>>() {});
            if(responseObj != null) responseBody.close();
            return responseObj;
        } catch (Exception e) {
            Counter.okError.incrementAndGet();
            throw new RuntimeException(e.getMessage());
        }
    }

    private Map<String, String> apacheCall(String url){
        try {
            HttpGet get1 = new HttpGet(url);
            CloseableHttpResponse res = httpClient.execute(get1);
            String json = EntityUtils.toString(res.getEntity());
            return (json == null) ? null : mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            Counter.apacheError.incrementAndGet();
            throw new RuntimeException(e.getMessage());
        }
    }
}
