package com.design_patterns.creational.builder;

import lombok.SneakyThrows;

import java.io.IOException;

public class SingletonPattern {

    private final String p1;
    private final String p2;

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "SingletonPattern{" +
                "p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                '}';
    }

    private static SingletonPattern singleObject;

    private static final Object lock = new Object();
    private SingletonPattern(String p1, String p2){
        if(singleObject != null)
            throw new RuntimeException("Already Object Created.....");
        this.p1 = p1;
        this.p2 = p2;
    }
    // thread safe
    public static SingletonPattern getInstance(String p1, String p2){
        if(singleObject == null){
            synchronized (lock){
                if(singleObject == null)
                    singleObject = new SingletonPattern(p1, p2);
                return singleObject;
            }
        }
        return singleObject;
    }

    @Override
    public SingletonPattern clone(){
        return singleObject;
    }

    // safe from de-serialization
    protected Object readResolve(SingletonPattern objectToResolve) {
        synchronized (lock){
            if(singleObject != null)
                return singleObject;
            singleObject = this;
            return this;
        }

    }

    @SneakyThrows
    public static void main(String[] args) {
        SingletonPattern singletonPattern = SingletonPattern.getInstance("qwe", "qweas");
        System.out.println(singletonPattern.clone() == singletonPattern);
    }
}
