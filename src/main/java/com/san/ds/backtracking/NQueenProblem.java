package com.san.ds.backtracking;

import java.util.Arrays;

public class NQueenProblem {

    public static void main(String[] args) {
        int rows = 6;
        int cols = 6;
        boolean[][] board = new boolean[rows][cols];
        printTest(rows, cols);

        new NQueenProblem().placeQueen(board, 0, new boolean[cols], new boolean[rows + cols -1], new boolean[rows + cols -1], "");
    }

    public static void printTest(int rows, int cols){
        NQueenProblem nqp = new NQueenProblem();
        String[][] b = new String[rows][cols];
        for(int row = 0; row < rows ; row++){
            for (int col = 0; col < cols; col++){
                b[row][col] = nqp.getRightToLeftDiagonalIndex(row, col, rows, cols)+ "\\";
            }
        }
        for(String[] a : b){
            System.out.println(Arrays.toString(a));
        }
        System.out.println("--------------");
        for(int row = 0; row < rows ; row++){
            for (int col = 0; col < cols; col++){
                b[row][col] = nqp.getLeftToRightDiagonalIndex(row, col) + "\\";
            }
        }
        for(String[] a : b){
            System.out.println(Arrays.toString(a));
        }
    }
    public void placeQueen(boolean[][] board, int currentQueen, boolean[] checkedCols, boolean[] rightToLeftDiagonal, boolean[] leftToRightDiagonal, String answer){
        int n = board.length;
        if(currentQueen >= n){
            System.out.println(answer);
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
//        int maxN = Math.max(board.length, board[0].length);
        for(int col = 0; col < checkedCols.length; col++){
            if(canPlaceQueen(currentQueen, col, rows, cols, checkedCols, rightToLeftDiagonal, leftToRightDiagonal)){
                board[currentQueen][col] = true;
                checkedCols[col] = true;
                leftToRightDiagonal[getLeftToRightDiagonalIndex(currentQueen, col)] = true;
                rightToLeftDiagonal[getRightToLeftDiagonalIndex(currentQueen, col, rows, cols)] = true;
                placeQueen(board, currentQueen+1, checkedCols, rightToLeftDiagonal, leftToRightDiagonal, answer + currentQueen + "," + col +"#");
                board[currentQueen][col] = false;
                checkedCols[col] = false;
                leftToRightDiagonal[getLeftToRightDiagonalIndex(currentQueen, col)] = false;
                rightToLeftDiagonal[getRightToLeftDiagonalIndex(currentQueen, col, rows, cols)] = false;
            }
        }
    }

    private boolean canPlaceQueen(int currentRow, int currentCol, int rows, int cols, boolean[] checkedCols, boolean[] rightToLeftDiagonal, boolean[] leftToRightDiagonal){
        if(checkedCols[currentCol]
                || rightToLeftDiagonal[getRightToLeftDiagonalIndex(currentRow, currentCol, rows, cols)]
                || leftToRightDiagonal[getLeftToRightDiagonalIndex(currentRow, currentCol)])
            return false;
        return true;
    }
    private int getRightToLeftDiagonalIndex(int currentRow, int currentCol, int maxR, int maxC){
        if(maxR > maxC)
            return currentRow - currentCol + maxC - 1;
        else
            return currentCol - currentRow + maxR - 1;
    }

    private int getLeftToRightDiagonalIndex(int currentRow, int currentCol){
        return currentCol+currentRow;
    }

}
