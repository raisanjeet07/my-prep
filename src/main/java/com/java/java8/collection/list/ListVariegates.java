package com.java.java8.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListVariegates {

    static  private List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,22,11,1,2,3,4,8); // return a unmodifiable List. a new class that has all update method set to throw exception

    public static void addInAboveList(){
//        list.add(12); // its changing the size, it will throw exception
//        list.add(1,23); // also not supported
        List<Integer> list2 = new ArrayList<>(list);
        list2.add(4123413);
        System.out.println(list.size());
        System.out.println(list2.size());
    }

    public static void main(String[] args) {
        addInAboveList();
    }
}
