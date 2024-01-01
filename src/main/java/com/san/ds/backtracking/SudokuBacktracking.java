package com.san.ds.backtracking;

import java.util.*;

public class SudokuBacktracking {
    private static boolean found = false;
    public static void main(String[] args) {
        int n = 4;
        n = (int)Math.pow(n, 2);
        int[][] sudoku = new int[n][n];
        sudoku[0][0] = 1;
        sudoku[0][1] = 5;
        sudoku[0][2] = 4;
        sudoku[3][0] = 9;
        sudoku[3][0] = 8;
        sudoku[3][0] = 5;
        sudoku[3][0] = 2;
        sudoku[4][7] = 1;
        sudoku[4][5] = 7;
        sudoku[6][2] = 7;
        new SudokuBacktracking().solveAllPossibleSolutions(sudoku);
    }

    private void solveAllPossibleSolutions(int[][] sudoku){
        Map<String, Set<Integer>> usedOptionsMap = new HashMap<>();
        usedOptionsMap.putAll(getColumnWiseUsedOptionSet(sudoku));
        usedOptionsMap.putAll(getRowWiseUsedOptionSet(sudoku));
        usedOptionsMap.putAll(getTilesWisedOptionSet(sudoku));
        printAllSolutions(sudoku, 0, 0, usedOptionsMap);
    }

    private Map<String, Set<Integer>> getColumnWiseUsedOptionSet(int[][] sudoku){
        Map<String, Set<Integer>> colsMap = new HashMap<>();
        String colKey;
        for(int col = 0; col < sudoku.length; col++){
            colKey = col+"_c";
            colsMap.put(colKey, new HashSet<>());
            for (int row = 0; row < sudoku.length; row++){
                if(sudoku[row][col] != 0)
                    colsMap.get(colKey).add(sudoku[row][col]);
            }
        }
        return colsMap;
    }

    private Map<String, Set<Integer>> getRowWiseUsedOptionSet(int[][] sudoku){
        Map<String, Set<Integer>> rowsMap = new HashMap<>();
        String rowKey;
        for(int row = 0; row < sudoku.length; row++){
            rowKey = row+"_r";
            rowsMap.put(rowKey, new HashSet<>());
            for (int col = 0; col < sudoku.length; col++){
                if(sudoku[row][col] != 0)
                    rowsMap.get(rowKey).add(sudoku[row][col]);
            }
        }
        return rowsMap;
    }

    private Map<String, Set<Integer>> getTilesWisedOptionSet(int[][] sudoku){
        Map<String, Set<Integer>> tilesMap = new HashMap<>();
        String key;
        for(int row = 0; row < sudoku.length; row++){
            for (int col = 0; col < sudoku.length; col++){
                key = getTilesKey(sudoku.length, row, col);
                if(!tilesMap.containsKey(key))
                    tilesMap.put(key, new HashSet<>());
                if(sudoku[row][col] != 0)
                    tilesMap.get(key).add(sudoku[row][col]);
            }
        }
        return tilesMap;
    }

    private String getTilesKey(int n, int row, int col){
        int sqrt = (int)Math.sqrt(n);
        if(n != Math.pow(sqrt, 2))
            throw new RuntimeException("Solution not possible");
        return row/sqrt + "" + col/sqrt + "_t";
    }

    private void printAllSolutions(int[][] sudoku, int row, int col,  Map<String, Set<Integer>> usedOptions){
//        System.out.println("row: "+ row + " Col: "+col);

        if (row == sudoku.length){
            System.out.println("No solution");
            return;
        }
        if(sudoku.length == col && row < sudoku.length ){
            row++;
            col = 0;
        }

        if(row == sudoku.length){
            System.out.println("reached last cell");
            printSolution(sudoku);
            found = true;
            return;
        }

        if(sudoku[row][col] > 0){
            printAllSolutions(sudoku, row, col+1, usedOptions);
            return;
        }



        // trying all options
        for( int i = 1; i <= sudoku.length; i++){
            if(!checkedOption(i, sudoku.length, row, col, usedOptions)){
                markedChecked(i, sudoku.length, row, col, usedOptions);
                sudoku[row][col] = i;
                printAllSolutions(sudoku, row,  col+1, usedOptions);
                if(found)
                    break;
                sudoku[row][col] = 0;
                unMarkedChecked(i, sudoku.length, row, col, usedOptions);
            }
        }
    }

    private boolean checkedOption(int option, int n, int row, int col, Map<String, Set<Integer>> usedOptions) {
        return usedOptions.get(row+"_r").contains(option)
                || usedOptions.get(col+"_c").contains(option)
                || usedOptions.get(getTilesKey(n, row, col)).contains(option);
    }

    private void markedChecked(int option, int n, int row, int col, Map<String, Set<Integer>> usedOptions){
        usedOptions.get(row+"_r").add(option);
        usedOptions.get(col+"_c").add(option);
        usedOptions.get(getTilesKey(n, row, col)).add(option);
    }

    private void unMarkedChecked(int option, int n, int row, int col, Map<String, Set<Integer>> usedOptions){
        usedOptions.get(row+"_r").remove(option);
        usedOptions.get(col+"_c").remove(option);
        usedOptions.get(getTilesKey(n, row, col)).remove(option);
    }

    private void printSolution(int[][] sudoku){
        for(int[] a : sudoku)
            System.out.println(Arrays.toString(a));
    }
}
