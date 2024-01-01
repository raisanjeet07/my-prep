package com.san.ds.recurssion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LSI {
    public static void main(String[] args) {
        int arr[] = {1,1,1,2,2,3};
        System.out.println(topKFrequent(arr, 2));
    }
    private static int lsi(int arr[]){
        int dp[] = new int[arr.length];
        dp[0] = 1;
        int i = 1;
        int max = 1;
        while(i < arr.length){
            for(int j = 0; j < i; j++){
                dp[i] = (arr[j] < arr[i] && dp[i]  < dp[j] + 1) ? dp[j]+ 1 : dp[i];
            }
            if(max < dp[i])
                max = dp[i];
            i++;
        }
        return max;
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(
                Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        System.out.println(map);
        List<Integer> topk = map.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).limit(k).map(integerLongEntry -> integerLongEntry.getKey().intValue()).collect(Collectors.toList());
        int a[] = new int[topk.size()];
        for(int i = 0; i < a.length; i++){
            a[i] = topk.get(i);
        }
        System.out.println(Arrays.toString(a));
        return a;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(item -> getSortedCharsString(item))).values());
    }
    private String getSortedCharsString(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
