package com.san.ds.backtracking;

public class NBitGrayCodes {

    public static void main(String[] args) {
        printNBitGrayCodes(0, 10, "");
    }

    private static void printNBitGrayCodes(int currentIndex, int n, String answer){
        if(currentIndex == n){
            System.out.println(answer);
            return;
        }

        printNBitGrayCodes(currentIndex+1, n, answer+"0");
        printNBitGrayCodes(currentIndex+1, n, answer+"1");
    }
}
