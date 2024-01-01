package com.bloomfliter;

import java.util.ArrayList;

public class BloomRunner {
    public static void main(String[] args) {
        BloomAlgo<String> algo1 = s -> ("asdfgsdf" +s).hashCode();
        BloomAlgo<String> algo2 = s -> ("asdqwertwer" +s).hashCode();
        BloomAlgo<String> algo3 = s -> ("hhnfht" +s).hashCode();
        BloomAlgo<String> algo4 = s -> ("utyuy" +s).hashCode();
        BloomAlgo<String> algo5 = s -> ("4123442" +s).hashCode();

        int n = 1;
        int size = (1 << n) | ((1 << n) - 1);
        System.out.println(size);
        BloomFilter<String> bloomFilter = BloomFilter.<String>builder()
                .size(size)
                .addAlgo(algo1)
                .addAlgo(algo2)
                .addAlgo(algo3)
                .addAlgo(algo4)
                .addAlgo(algo5)
                .build();
        bloomFilter.markSeen("a");
        int i = 100;
        while(i-- > 1){
            bloomFilter.markSeen("" + (char)(i));
        }
        System.out.println(bloomFilter.isSeen("a"));
        System.out.println(bloomFilter.isSeen("b"));
        System.out.println(bloomFilter.isSeen("e"));
        System.out.println(bloomFilter.isSeen("c"));
    }
}
