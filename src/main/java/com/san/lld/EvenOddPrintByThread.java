package com.san.lld;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddPrintByThread {
    private static class NumberPrinter implements Runnable{
        AtomicInteger sharedCounter;
        int reminderCheck;

        public NumberPrinter(AtomicInteger sharedCounter, int reminderCheck){
            this.sharedCounter = sharedCounter;
            this.reminderCheck = reminderCheck;
        }

        @SneakyThrows
        @Override
        public void run() {
            while(true){
                synchronized (sharedCounter) {
                    if (sharedCounter.get() % 2 == reminderCheck) {
                        System.out.println(Thread.currentThread().getName() + " : "+ sharedCounter);
                        sharedCounter.incrementAndGet();
                        Thread.sleep(1000);
                        sharedCounter.notifyAll();
                    } else {
                        try {
                            sharedCounter.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }





    public static void main(String[] args) {
        AtomicInteger sharedCounter = new AtomicInteger();
        new Thread(new NumberPrinter(sharedCounter, 0), "even").start();
        new Thread(new NumberPrinter(sharedCounter, 1), "odd").start();
    }
}
