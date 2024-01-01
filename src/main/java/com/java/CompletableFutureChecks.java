package com.java;

import java.util.concurrent.*;

public class CompletableFutureChecks {
    private static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.completedFuture(0);
        FutureTask<Integer> f = new FutureTask<>(() -> 5);
        System.out.println(f.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }
}
