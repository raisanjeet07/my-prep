package com.san.ds.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Matrix {

    public boolean isValidSudoku(char[][] board) {
        Map<String, Set<Integer>> checkedMap = new HashMap<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] != 0 && isChecked(board[i][j], i, j, checkedMap))
                    return false;
            }
        }
        return true;
    }

    private boolean isChecked(int number, int row, int col, Map<String, Set<Integer>> checkedMap){
        String key = getKey(row, col, 'R');
        checkedMap.putIfAbsent(key, new HashSet<>());
        if(checkedMap.get(key).contains(number))
            return true;
        checkedMap.get(key).add(number);
        key = getKey(row, col, 'C');
        checkedMap.putIfAbsent(key, new HashSet<>());
        if(checkedMap.get(key).contains(number))
            return true;
        checkedMap.get(key).add(number);
        key = getKey(row, col, 'B');
        checkedMap.putIfAbsent(key, new HashSet<>());
        if(checkedMap.get(key).contains(number))
            return true;
        checkedMap.get(key).add(number);
        return false;
    }


    private String getKey(int row, int col, char type){
        switch (type) {
            case 'R' -> {
                return "R" + row;
            }
            case 'C' -> {
                return "C" + col;
            }
            case 'B' -> {
                return "B" + row % 3 + "" + col % 3;
            }
        }
        return "";
    }
}
