package com.san.ds.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeSolutions {


    private  int fourSumCount_454(int[] nums1, int[] nums2, int[] nums3, int[] nums4 ){
        Map<Integer, Integer>[] sumMapF = new Map[2];
        sumMapF[0] = new HashMap<>();
        sumMapF[1] = new HashMap<>();
        int count = 0;
        int sum;
        int n = nums1.length;
        for (int k : nums1) {
            for (int j : nums2) {
                sum = k + j;
                sumMapF[0].put(sum, sumMapF[0].getOrDefault(sum, 0) + 1);
            }
        }

        for (int k : nums3) {
            for (int j : nums4) {
                sum = k + j;
                sumMapF[1].put(sum, sumMapF[1].getOrDefault(sum, 0) + 1);
            }
        }


        count = sumMapF[0].entrySet().stream().mapToInt(entry -> {
            if(sumMapF[1].containsKey(-entry.getKey())){
                return  entry.getValue() * sumMapF[1].get(-entry.getKey());
            }
            return 0;
        }).sum();

        return count;
    }


    public static void main(String[] args) {

    }
}
