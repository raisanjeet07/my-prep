package com.san.springboot.executor;

import com.san.springboot.Counter;
import com.san.springboot.service.IpGeoHttpServiceProxy;
import okhttp3.internal.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ConcurrentRunner {
    @Autowired
    IpGeoHttpServiceProxy serviceProxy;

    int trafficSize = 1000;
    int batchSize = 1000;

    int concurrentRequest = 1000;

    final ExecutorService executorService = new ThreadPoolExecutor(concurrentRequest, concurrentRequest, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    final ExecutorService executorService2 = new ThreadPoolExecutor(concurrentRequest, concurrentRequest, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//    @PostConstruct
//    public void runJob(){
//        runInBatches();
//        System.out.println("All Done");
//    }
    @Async
    public void runInBatches(int batchSize){
        trafficSize = batchSize;
        int batches = trafficSize > batchSize ? trafficSize/batchSize : 1;
        trafficSize = batchSize;
        batches = 1;
        while (batches > 0){
            List<String> ips = generateRandomIps(batchSize);
            runParallel(ips);
            batches--;
        }
    }

    private void runSequentially(List<String> ips){
        ips.stream().forEach(ip -> serviceProxy.getIpDetails(ip, 2));
    }

    private void runParallel(List<String> ips){
//        ips.parallelStream().forEach(ip -> executorService2.execute(() -> serviceProxy.getIpDetailsA(ip)));
        ips.parallelStream().forEach(ip -> executorService2.execute(() -> serviceProxy.getIpDetailsO(ip)));
    }



    public List<String> generateRandomIps(int trafficSize){
        int i = trafficSize;
        List<String> ips = new ArrayList<>();
        while(i > 0){
            ips.add(getRandomIp());
            i--;
        }
        return ips;
    }

    Random r1 = new Random(41235424);
    Random r2 = new Random(48452467);
    Random r3 = new Random(41734523);
    Random r4 = new Random(416863434);

    public String getRandomIp(){
        return  getRandom(r1)+ "." + getRandom(r2) + "." +getRandom(r3) + "." + getRandom(r4);
    }

    public int getRandom(Random r){
        return Math.abs(r.nextInt()%255);
    }
}
