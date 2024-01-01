package com.experiments;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.nio.charset.Charset;
import java.util.*;

public class Experiment {

    private static void printMemory(){
        System.out.println("Memory details......");
        for (MemoryPoolMXBean mpBean: ManagementFactory.getMemoryPoolMXBeans()) {
            if (true) {
                System.out.printf(
                        "Name: %s: %s\n",
                        mpBean.getName(), mpBean.getUsage().getInit()/(2 <<6)
                );
            }
        }
    }
    public static void Map_MAX_SIZE_BREAk(){
//        printMemory();
        Set<String> ints = new HashSet<>();
        int n = 5;
        int allSetInte = ((1<<n) ^ ((1<<n) - 1));
//        System.out.println((allSetInte));
        System.out.println(0xFF);
//        for(int i = 0; i < Integer.MAX_VALUE/(2<<32); i++){
//            ints.add(i + "");
//        }
//        System.out.println(ints.size());
    }

    private static void StringEncodingTest(){
        int n = 4;
        byte[] b =  new byte[n];
        while(n > 0){
            b[n - 1] = (byte)(Math.pow(n,2));
            n--;
        }
        String s = new String(b);
        System.out.println(new String(b));
        char[] chars = {'A','B','C',0xffff, 0xaa, 'D', 'A'};
        System.out.println(new String(chars));
        for(int i = 0; i < 10; i++){
            System.out.println(i + " : " +(i << 1));
        }
    }

    private static void GenericsExp(){
        List<? super Objects> list = new ArrayList<>();

    }

    public static void main(String[] args) {
        StringEncodingTest();
    }
}
