package com.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPractice {
    public static List<Integer> list = Arrays.asList(1,2,3,4,3,2,1,3,4,6,32,3,46,4,67,4,3,24,5,4,67,7,5,3,34,23,34,35,46,5,8);


    public static void printAvg(){
        double avg = list.stream().mapToInt(value -> value.intValue()).average().orElse(0);
        System.out.println(avg);
    }

    public static void top2MostFrequentNo(){
        List<Integer> sortedlist = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 3)
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(sortedlist);
    }

    public static void removeDuplicates(){
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        removeDuplicates();
    }
}
