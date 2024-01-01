package com.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamChecks {
    private static void modifyCollectionAfterStreamCreated(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,67,7,8,9,9));
        Iterator<Integer> itr = list.iterator();
        Stream<Integer> stream = list.stream();
//        stream.forEach(System.out::println);
        list.add(1);
        itr.hasNext();
        Stream<Integer> s2 = stream.filter(i -> i % 2 == 0);
        System.out.println(list);
        stream.forEach(System.out::println);
        s2.forEach(System.out::println);
    }

    public static void main(String[] args) {
        modifyCollectionAfterStreamCreated();
    }
}
