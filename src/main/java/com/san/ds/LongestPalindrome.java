package com.san.ds;

import java.util.Arrays;

public class LongestPalindrome {
    // String str: 34324234

    public static void main(String[] args) {
        String str = "1111324423";
        int n = str.length();
        int[][] table = new int[n][n];

        int max = 1;
        // update to 1 for single chars
        int i = 0;
        for( ; i < n; i++){
            table[i][i] = 1;
        }

        // update table for length 2
        i = 0;
        for( ; i < n -1; i++){
            if(str.charAt(i) == str.charAt( i + 1)){
                table[i][i+1] = 2;
                max = 2;
            }
            else{
                table[i][i+1] = 1;
            }
        }

        // update for rest of the lengths
        int len = 3;
        for( ; len <= n; len++){
            for(i = len-1; i < n; i++){
                int sIndex = i + 1 - len;
                if(str.charAt(sIndex) == str.charAt( i)){
                    table[sIndex][i] = 2 + table[sIndex + 1][i - 1];
                    if (table[sIndex][i] > max)
                        max = table[sIndex][i];
                }
            }
        }
        print2D(table);
        System.out.println(max);
    }

    private static void print2D(int[][] arr2D){
        for (int[] arr : arr2D)
            System.out.println(Arrays.toString(arr));
    }
}
