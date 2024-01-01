package com.san.ds;

import java.util.Arrays;

public class ArrayRotationByD {
    public static void main(String[] args){
        int[] arr = {0,1,2,3,4,5,6,7,8,9};
        rotateLeftByD(arr, 5);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0};
        rotateLeftByD(arr, 5);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0,1,2};
        rotateLeftByD(arr, 5);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0,1,2,3,4,5};
        rotateLeftByD(arr, 5);
        System.out.println(Arrays.toString(arr));



        arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        rotateLeftInWindowByD(arr, 5, 2);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0};
        rotateLeftInWindowByD(arr, 5, 1);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0,1,2};
        rotateLeftInWindowByD(arr, 3, 1);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{0,1,2,3,4,5};
        rotateLeftInWindowByD(arr, 2, 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void rotateLeftByD(int[] arr, int d){
        while(d > 0){
            rotateLeftBy1(arr, 0, arr.length - 1);
            d--;
        }
    }

    public static void rotateLeftInWindowByD(int[] arr, int windowSize, int rotateBy){
        int totalLength = arr.length;
        int startIndex = 0;
        int endIndex;
        int windowRotateBy;
        while(startIndex <  totalLength){
            endIndex = (startIndex + windowSize - 1) < totalLength ? startIndex + windowSize - 1 : totalLength - 1;
            windowRotateBy = 0;
            while(windowRotateBy < rotateBy){
                rotateLeftBy1(arr, startIndex, endIndex);
                windowRotateBy++;
            }
            startIndex = startIndex + endIndex + 1; // first loop, towards exit
        }
    }

    public static void  rotateLeftBy1(int[] arr, int s, int e){
        int n = e+1;
        int tmp = arr[s];
        if(n >= 2){
            for(int i = s; i < n -1 ; i++)
                arr[i] = arr[i+1];
        }
        arr[n-1] = tmp;
    }
}
