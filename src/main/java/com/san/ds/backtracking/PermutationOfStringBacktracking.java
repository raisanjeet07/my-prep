package com.san.ds.backtracking;

import java.util.*;

public class PermutationOfStringBacktracking {
    public static void main(String[] args) {
        Set<String> strs = new HashSet<>();
        printAllPermutations("alam".split(""), 0, "", strs);
        System.out.println(strs);
        printAllPalindromicPermutations("malaysalam");
    }


    private static void printAllPermutations(String[] chars, int currentIndex, String answer, Set<String> answers){
        int n = chars.length;
        if(currentIndex == n){
//            System.out.println(answer);
            answers.add(answer);
            return;
        }
        String tmp;
        for(int i = currentIndex; i < n; i++){
            if(i != currentIndex && chars[i].equals(chars[currentIndex]))
                continue;
            tmp = chars[i];
            chars[i] = chars[currentIndex];
            chars[currentIndex] = tmp;
            printAllPermutations(chars, currentIndex+1, answer + chars[currentIndex], answers);
            tmp = chars[i];
            chars[i] = chars[currentIndex];
            chars[currentIndex] = tmp;
        }
    }

    private static void printAllPalindromicPermutations(String str){
        Map<Character, Integer> countMap = new HashMap<>();
        StringBuilder firstHalf = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            countMap.put(str.charAt(i), countMap.getOrDefault(str.charAt(i), 0) + 1);
            if(countMap.get(str.charAt(i)) == 2){
                firstHalf.append(str.charAt(i));
                countMap.remove(str.charAt(i));
            }
        }
        if(countMap.size() > 1){
            System.out.println("Not possible");
            return;
        }
        String midChar = countMap.keySet().stream().findFirst().orElse(' ').toString().trim();
        Set<String> firstHalfPerms = new HashSet<>();
        printAllPermutations(firstHalf.toString().split(""), 0, "", firstHalfPerms);
        System.out.println(firstHalfPerms.size());
        for(String s : firstHalfPerms)
            System.out.println(s + midChar + reverse(s));
    }

    private static String reverse(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1 ; i >= 0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
