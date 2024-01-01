package com.san.ds.aaray;

import java.util.*;

public class MaxSubArrayWithSum {
    /*
    * Find maximum length sub-array having given sum
    * Given an array of integers, find maximum length sub-array having given sum. For example, consider below array:
    * A[] = { 5, 6, -5, 5, 3, 5, 3, -2, 0 } Sum = 8 Sub-arrays with sum 8 are { -5, 5, 3, 5 } { 3, 5 } { 5, 3 }
    * The longest subarray is { -5, 5, 3, 5 } having length 4 Naive solution would be to consider all sub-arrays and find their sum.
    * If sub-array sum is equal to given sum, we update maximum length sub-array. The time complexity of naive solution is O(n3)
    * as there are n2 sub-arrays and it takes O(n) time to find sum of its elements. The method can be optimized to run in O(n2)
    * time by calculating sub-array sum in constant time.
    * */

    static int getSubArrayLentOfSum(int arr[], int k){
        int n = arr.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 0);
        int max = 0;
        for(int i = 1; i < n ; i++){
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            if(map.containsKey(prefixSum[i] - k))
                max = Math.max(max, i - map.get(prefixSum[i] - k));
            map.putIfAbsent(prefixSum[i], i);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 6, -5, 0, 5, 3, 5, 3, -2, 0 };
        int k = 8;
        System.out.println(getSubArrayLentOfSum(arr, k));
        int[] arr2 = { 0,0,0,0,3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
        printAllSubArraySumZero(arr2);
    }

    static int printAllSubArraySumZero(int arr[]){
        int n = arr.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(arr[0], new HashSet<>());
        map.get(arr[0]).add(0);
        int max = 0;
        for(int i = 1; i < n ; i++){
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            if(map.containsKey(prefixSum[i])){
                printSubArrays(map.get(prefixSum[i]), i, arr);
            }
            if(prefixSum[i] == 0){
                System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, i+1)));
            }
            map.putIfAbsent(prefixSum[i], new HashSet<>());
            map.get(prefixSum[i]).add(i);
        }
        return max;
    }

    private static void printSubArrays(Set<Integer> integers, int i, int[] arr) {
        for(int index : integers){
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, index+1, i+1)));
        }
    }
}
