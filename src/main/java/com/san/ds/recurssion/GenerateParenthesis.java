package com.san.ds.recurssion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String[] args) {
        System.out.println(generateAllParenthesis(8));
    }
    private static List<String> generateAllParenthesis(int n){
        List<String> result = new ArrayList<>();
        generateAllParenthesis("", n, 0, 0, result);
        System.out.println(result.size());
        return result;
    }

    private static void generateAllParenthesis(String str, int n, int open, int close, List<String> result){
        if(open == n && open == close){
            result.add(str);
        }else{
            if(open == close){ // start new parenthesis
                generateAllParenthesis(str + "(", n,open + 1, close, result);
            }else if (open == n){
                generateAllParenthesis(str+")", n,open, close + 1, result);
            }else{
                generateAllParenthesis(str + "(", n,open + 1, close , result);
                generateAllParenthesis(str + ")", n,open, close + 1, result);
            }
        }
    }
}
