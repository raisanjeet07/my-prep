package com.san.ds;

public class MatrixSpiralPrint {

    public static void main(String[] args){
        Integer[][] arr= {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
        };
        printSpiral(arr, arr.length, arr[0].length);
    }

    public static <T> void printSpiral(T[][] arr, int rows, int cols){
        int startRow = 0;
        int endRow = rows - 1;
        int startCol = 0;
        int endCol = cols - 1;

        while(startRow <= endRow && startCol <= endCol){
            printLeftToRight(arr, startCol, endCol, startRow);
            startRow++;
            printTopToBottom(arr, startRow, endRow, endCol);
            endCol --;
            printRightToLeft(arr, endCol, startCol, endRow);
            endRow--;
            printBottomToTop(arr, endRow, startRow, startCol);
            startCol++;

        }

    }

    private static <T> void printTopToBottom(T[][] arr, int startRow, int endRow, int colIndex){
        while(startRow <= endRow){
            System.out.print(arr[startRow][colIndex] + ",");
            startRow++;
        }
    }
    private static <T> void printBottomToTop(T[][] arr, int startRow, int endRow, int colIndex){
        while(startRow >= endRow){
            System.out.print(arr[startRow][colIndex] + ",");
            startRow--;
        }
    }
    private static <T> void printLeftToRight(T[][] arr, int startCol, int endCol, int rowIndex){
        while(startCol <= endCol){
            System.out.print(arr[rowIndex][startCol] + ",");
            startCol++;
        }
    }
    private static <T> void printRightToLeft(T[][] arr, int startCol, int endCol, int rowIndex){
        while(startCol >= endCol){
            System.out.print(arr[rowIndex][startCol] + ",");
            startCol--;
        }
    }

}
