package com.san.ds.recurssion;

public class Knapsack01 {

    private static int pickMaxValuable(int[] arr, int currentIndex, int valueSoFar, int pickedSoFar, int maxItemTiPick){
        if(arr.length == currentIndex || pickedSoFar == maxItemTiPick)
            return valueSoFar;

        return Math.max(
                pickMaxValuable(arr, currentIndex+1, valueSoFar+arr[currentIndex], pickedSoFar+1, maxItemTiPick),
                pickMaxValuable(arr, currentIndex+1, valueSoFar, pickedSoFar, maxItemTiPick)
                );
    }

    private static int pickMaxValuable(int[] arr, int maxItemTiPick){
        return pickMaxValuable(arr, 0, 0, 0, maxItemTiPick);
    }

    public static void main(String[] args) {
        int[] arr = {2,3,5,3,-1,4};

        System.out.println(pickMaxValuable(arr, 15));
    }
}
