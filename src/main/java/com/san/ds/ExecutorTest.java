package com.san.ds;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(2);
        ExecutorService es = new ThreadPoolExecutor(1, 1, 5, TimeUnit.MINUTES, queue);
    Runnable job =
        () -> {
          System.out.println("Deleting");
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          System.out.println("Job done");
        };
        es.submit(job);
        runInLoop(queue, job, 10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main done");
    }

    private static void runInLoop(LinkedBlockingQueue<Runnable> queue, Runnable job, int count){
        while(count > 0){
            System.out.println("pushing job id: "+count);
            try {
                queue.put(job);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count--;
        }
    }
}
