package com.san;

public class Test {
    /*
    [

            [0, 0, 0,1 ]
            [0, 0, 1,1 ],
            [0, 1, 1,1 ],
            [1, 1, 1,1 ],
            ]
    */


    public static void main(String[] args){
        int[][] table = new int[][] {{0,0,0,1},{1,1,1,1},{1,1,1,1},{0,0,1,1}};


    System.out.println(getFirstOnesIndex(table));
    }

    static  private int getFirstOnesIndex(int[][] table){

        int tmp;
        int l = table[0].length;
        int i = l;
        int rowCount = -1;
        int result = 0;
        for(int[] row : table){
            rowCount++;
            tmp = getIndexOfOne(row, 0, l-1);
            if(tmp < i){
                i = tmp;
                result = rowCount;
            }
        }
        return result;
    }

    static  private int getIndexOfOne(int[] arr, int sti, int ei){
        int mid = sti + (ei - sti)/2;
        int l = arr.length;
        while(mid <= ei && mid >= sti){
            if(arr[mid] == 1){
                ei = mid - 1;
            }else{
                sti = mid + 1;
            }
            mid = sti + (ei - sti)/2;
        }
        return arr[sti] == 1? sti : ei;
    }

}
