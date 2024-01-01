package com.san.ds.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionKEqualSum {

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,2,2,2,2};
        System.out.println(partitionInEqualSm(arr, 4));
    }

    private static boolean partitionInEqualSm(int[] arr, int k){
        List<Integer> options = new ArrayList<>();
        int sum = 0;
        for(int i : arr){
            options.add(i);
            sum += i;
        }

        return isPossibleToBreak(arr, sum/k, k);
    }

    private static boolean isPossibleToBreak(int[] arr, int sum, int k){
        List<List<Integer>> allPossible = getAllSubsetWithSum(arr, sum);
        Set<Integer> allUsedIndexes = new HashSet<>();
        for(List<Integer> oneOfResult : allPossible){
            oneOfResult.forEach(index -> allUsedIndexes.add(index));
        }
        return allUsedIndexes.size() == arr.length && allPossible.size() >= k;
    }

    private static List<List<Integer>> getAllSubsetWithSum(int[] arr, int sum){
        List<List<Integer>> container = new ArrayList<>();
        getAllSubsetWithSum(arr, sum, 0, new ArrayList<>(), container);

        List<List<Integer>> result = new ArrayList<>();

        for(List<Integer> oneOfResult : container){
            List<Integer> tmp = new ArrayList<>();
            oneOfResult.forEach(index -> tmp.add(arr[index]));
            result.add(tmp);
        }
        System.out.println(result);
        return container;
    }

    private static void getAllSubsetWithSum(int[] arr, int sum, int currentIndex, List<Integer> selected, List<List<Integer>> container){
        if(sum == 0){
            container.add(new ArrayList<>(selected));
            return;
        }

        if(currentIndex == arr.length)
            return;

        int i = currentIndex;
        while(i < arr.length){
            if(sum - arr[i] >= 0 ){
                selected.add(i);
                getAllSubsetWithSum(arr, sum - arr[i], i+1, selected, container);
                selected.remove(Integer.valueOf(i));
            }
            i++;
        }
    }



}
