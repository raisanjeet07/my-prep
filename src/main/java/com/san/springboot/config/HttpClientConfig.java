package com.san.springboot.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import lombok.SneakyThrows;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.SocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    @Autowired
    MeterRegistry meterRegistry;

    @Bean
    public OkHttpClient okHttpClient(){
        long connectionTimeOut = 200;
        long readTimeOut = 200;
        int maxIdealConnection = 1000;
        int maxConcurrentRequestCount = Integer.MAX_VALUE;
        int maxConcurrentRequestCountOnHost = Integer.MAX_VALUE;
        ConnectionPool pool = new ConnectionPool(maxIdealConnection, 10L, TimeUnit.MINUTES);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(maxConcurrentRequestCount);
        dispatcher.setMaxRequestsPerHost(maxConcurrentRequestCountOnHost);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .eventListener(OkHttpMetricsEventListener.builder(meterRegistry, "okhttp3.monitor").build())
                .connectTimeout(connectionTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .dispatcher(dispatcher)
                .addInterceptor(logging)
                .connectionPool(pool)
                .build();
    new Thread(
            new Runnable() {
              @SneakyThrows
              @Override
              public void run() {
                while (true) {
                  Thread.sleep(1000 * 5);
                  System.out.println(new Date() + "\tpool stats");
                  System.out.println(client.connectionPool().connectionCount());
                  System.out.println(client.connectionPool().idleConnectionCount());
                }
              }
            })
        .start();

        return client;
    }

//    @Bean
    public CloseableHttpClient apacheClient(){
        PoolingHttpClientConnectionManager connManager
                = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(1000);
        connManager.setDefaultMaxPerRoute(1000);
        HttpHost host = new HttpHost("http://netacuity.clmbtech.com", 80);
        connManager.setMaxPerRoute(new HttpRoute(host), 1000);
        connManager.setDefaultSocketConfig(
                SocketConfig.custom()
                        .setSoTimeout(200, TimeUnit.MILLISECONDS)
                        .build());
//        RequestConfig config = RequestConfig.custom().build();
        return HttpClients.custom().setConnectionManager(connManager).build();
    }
}
