package com.in.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Test {

    public static void main(String[] args) {
        String exp = "1+2-3*5/5*3-5+4";
        evaluate(exp);
    }
    private static Set<Character> operators = new HashSet<>(Arrays.asList('*', '-', '/', '+'));
    private static Set<Character> md = new HashSet<>(Arrays.asList('*', '/'));
    private static int evaluate(String exp){
        Stack<String> stack = new Stack<>();
        int i = 0;
        while(i < exp.length()){
            if(operators.contains(exp.charAt(i))){
                String operand2 = getOperand(i+1, exp);
                String operand1 = stack.pop();
                if(md.contains(exp.charAt(i))){
                    stack.push(eval(operand1, operand2, exp.charAt(i)) + "");
                }else{
                    stack.push(exp.charAt(i)+"");
                    stack.push(operand1);
                    stack.push(operand2);
                }
                i += operand2.length() + 1;
            }else{
                String operand2 = getOperand(i , exp);
                stack.push(operand2);
                i += operand2.length();
            }
        }
        return 0;
    }

    private static int eval(String operand1, String operand2, char operator){
        return switch (operator) {
            case '*' -> Integer.parseInt(operand1) * Integer.parseInt(operand2);
            case '/' -> Integer.parseInt(operand1) / Integer.parseInt(operand2);
            case '+' -> Integer.parseInt(operand1) + Integer.parseInt(operand2);
            case '-' -> Integer.parseInt(operand1) - Integer.parseInt(operand2);
            default -> 0;
        };
    }

    private static String getOperand(int i, String exp) {
        StringBuilder operand = new StringBuilder();
        while(!operators.contains(exp.charAt(i))){
            operand.append(exp.charAt(i++));
        }
        return operand.toString().trim();
    }

}
