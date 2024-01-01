package com.san.ds;

import java.util.*;
import java.util.stream.Stream;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> targetCharMap = new HashMap<>();
        int count;
        for(char c : t.toCharArray()){
            targetCharMap.putIfAbsent(c, 0);
            targetCharMap.put(c, targetCharMap.get(c) + 1);
        }
        System.out.println(targetCharMap);

        int maxCounts = targetCharMap.size();


        int si = 0;
        int ei = 0;
        Set<Character> matchedChar = new HashSet<>();
        String smallestStr = s;
        while(ei < s.length()){
            ei = iterateTillAllMatched(ei, s, matchedChar, targetCharMap);
            si = sinkTillWindowHasAll(si, s, targetCharMap);
            if(s.substring(si, ei + 1).length() < smallestStr.length())
                smallestStr = s.substring(si, ei + 1);
            ei++;
        }
        return smallestStr;
    }

    private int iterateTillAllMatched(int currentIndex, String s, Set<Character> matchedChar, Map<Character, Integer> targetMap){
        while(currentIndex < s.length()){
            char c = s.charAt(currentIndex);
            if(!targetMap.containsKey(c)){
                currentIndex++;
                continue;
            }
            targetMap.put(c, targetMap.get(c) - 1); // found a char. so decrementing the required count

            if(targetMap.get(c) <= 0){
                matchedChar.add(c);
                if(matchedChar.size() == targetMap.size())
                    return currentIndex; // a window found
            }
            currentIndex++;
        }
        return currentIndex == s.length() ? currentIndex - 1 : currentIndex;
    }

    private int sinkTillWindowHasAll(int si, String s, Map<Character, Integer> targetMap){
        while(si < s.length()){
            char c = s.charAt(si);
            if(targetMap.containsKey(c)){
                if(targetMap.get(c) < 0){
                    targetMap.put(c, targetMap.get(c) + 1);
                    si++;
                }else {
                    return si;
                }
            }else{
                si++;
            }

        }
        return si;
    }




    public int lengthOfLongestSubstringHavingUniqueChars(String s) {
        Set<Character> distinctChars = new HashSet<>();
        int si = 0;
        int ci = 0;
        char c;
        int max = 0;
        while(ci < s.length()){
            // start adding items
            c = s.charAt(ci);
            if(distinctChars.contains(c)){
                while(distinctChars.contains(c)){
                    distinctChars.remove(s.charAt(si));
                    si++;
                }
            }
            if(max < (ci - si + 1)){
                max = ci - si + 1;
            }
            ci++;
            distinctChars.add(c);

        }
        return max;
    }

    public String longestPalindrome(String s) {
        String lps = "";
        int l = s.length();
        int[][] dp = new int[l][l];
        // all single char is the palindrom
        for(int rc = 0; rc < l; rc++){
            dp[rc][rc] = 1;
            lps = s.charAt(rc)+"";
        }
        // check all 2 adjuscent chars also
        for(int rc = 0; rc < l-1; rc++){
            if(s.charAt(rc) == s.charAt(rc+1)){
                dp[rc][rc+1] = 1;
                lps = s.substring(rc, rc+2);
            }
        }

        for(int len = 2; len <= l; len++){
            for(int row = 0; row < l - len; row++){
                if(s.charAt(row) == s.charAt(row+len) && dp[row + 1][row + len -1] == 1){
                    dp[row][row+len] = 1;
                    lps = s.substring(row, row+len+1);
                }
            }
        }
        for (int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
        return lps;
    }


    public List<String> generateParenthesis(int n) {
        Set<String> pairs = new HashSet<>();
        List<List<String>> pairsCombinations = new ArrayList<>();
        List<String> p1 = new ArrayList<>();
        p1.add("()");
        pairsCombinations.add(p1);
        int len;
        for(int i =2; i < n; i++){
            len = pairsCombinations.size();
            for(int j = 0; j < len; j++){
                pairsCombinations.addAll(addParenthesisAndGetAllCombinations(pairsCombinations.remove(j)));
            }
        }
        return new ArrayList<>(pairs);
    }

    private Collection<? extends List<String>> addParenthesisAndGetAllCombinations(List<String> remove) {

        return null;
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dic = new HashSet<>(wordDict);
        int i = 0;
        int j = 0;
        while(i < s.length()){

        }
        return true;
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() ==0)
            return Collections.EMPTY_LIST;
        Map<Character, List<String>> mapping = new HashMap<>();
        mapping.put('2', Arrays.asList("a","b","c"));
        mapping.put('3', Arrays.asList("d","e","f"));
        mapping.put('4', Arrays.asList("g","h","i"));
        mapping.put('5', Arrays.asList("j","k","l"));
        mapping.put('6', Arrays.asList("m","n","o"));
        mapping.put('7', Arrays.asList("p","q","r","s"));
        mapping.put('8', Arrays.asList("t","u","v"));
        mapping.put('9', Arrays.asList("w","x","y", "z"));
        mapping.put('1', Arrays.asList(""));
        mapping.put('0', Arrays.asList(""));
        List<String> combinations = new ArrayList<>(mapping.get(digits.charAt(0)));
        List<String> newLetters;
        int len;
        List<String> nCombinations;
        for(int i = 1; i < digits.length(); i++){
            newLetters = mapping.get(digits.charAt(i));
            len = combinations.size();
            nCombinations = new ArrayList<>();
            for(int j = 0; j < len; j++){
                for(String letter : newLetters){
                    nCombinations.add(combinations.get(j) + letter);
                }
            }
            combinations = nCombinations;
        }
        return combinations;
    }

    public int minEditDistance(String word1, String word2) {
        /*
        * Just by 3 operation
        * Insert, Remove, Update
        * */
        int l1 = word1.length();
        int l2 = word2.length();

        int[][] dp = new int[l2+1][l1+1];
        // if there is no word to make
        for(int i = 0; i <= l2; i++){
            dp[i][0] = i;
        }

        for(int i = 0; i <= l1; i++){
            dp[0][i] = i;
        }

        for(int row = 1; row <= l2; row++ ){
            for(int col=1; col <= l1; col++){
                if(word2.charAt(row - 1) == word1.charAt(col - 1)){
                    dp[row][col] = dp[row - 1][col -1];
                }else{
                    // replace = dp[row - 1][col -1] + 1
                    // insert = dp[row - 1][col] + 1
                    // delete = dp[row][col - 1] + 1
                    dp[row][col] = 1 + Math.min(Math.min(dp[row - 1][col -1], dp[row - 1][col]), dp[row ][col -1]);
                }
            }
        }

        for(int[] ar : dp){
            System.out.println(Arrays.toString(ar));
        }

        return dp[l2][l1];
    }


    public String decodeString(String s) {
        /*
        * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
*
* Input: s = "3[a2[c]]"
Output: "accaccacc"
        * */

        Stack<String> stack = new Stack<>();
        String open = "[";
        String close = "]";

        String[] codedStrArr = s.split("");
        for(String str : codedStrArr){
            if(str.equals(close)){
                stack.push(multiplyString(popTillOpen(stack), popTheNumber(stack)));
            }else {
                stack.push(str);
            }
        }
        String result = "";
        while(stack.size() > 0){
            result = stack.pop() + result;
        }
        return result;
    }

    private String popTheNumber(Stack<String> stack){
        String intStr = "";
        while(stack.size() > 0){
            try{
                Integer.parseInt(stack.peek());
                intStr = stack.pop() + intStr;
            }catch (Exception e){
                break;
            }
        }
        return intStr;
    }

    private String multiplyString(String str, String by){
        int n = Integer.parseInt(by);
        String str2 = "";
        while(n > 0){
            str2 += str;
            n--;
        }
        return str2;
    }

    private String popTillOpen(Stack<String> stack){
        String open = "[";
        String str = "";
        String tmp;
        while(stack.size() > 0){
            if(open.equals(stack.peek())){
                stack.pop();
                break;
            }
            str = stack.pop() + str;
        }
        return str;
    }


    private Object streamChecks(){
        List<String> list = Arrays.asList("1234567890abcdefghijklmnopqrstuvwxxyz".split(""));
        int[] array = {1,2,3,4,5,6,7,8,9,0};
        Spliterator<Integer> intSpl = Spliterators.spliterator(array, 0);
        Spliterator<Integer> inSpl1 = intSpl.trySplit();
        inSpl1.forEachRemaining(System.out::println);
        System.out.println("------");
        inSpl1.tryAdvance(System.out::println); // it won't print anything as the above method already trversed the array
        System.out.println("------");
        intSpl.trySplit().forEachRemaining(System.out::println);
        Object result;
        result = list.parallelStream().flatMap(s -> Stream.of(s+"|12|23|34|45")).flatMap(s -> Stream.of(s.split("\\|"))).map(s -> s+":").limit(500).reduce("", String::concat);
        return result;
    }

    private Object streamOnMap(){
        Map<String, String> map = new HashMap<>();
        map.put("a*b","5");
        map.put("b","4");
        map.put("c","3");
        map.put("d","2");
        map.put("e","1");
        map.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByValue().reversed())
                .limit(10)
                .forEach(System.out::println); // or any other terminal method
        return null;

    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // from left to right product
        result[0] = 1;
        result[1] = nums[0];
        for(int i = 2; i < n; i++){
            result[i] = nums[i -1] * result[i -1];
        }

        // right to left
        int[] result2 = new int[n];
        result2[n-1] = 1;
        result2[n-2] = nums[n-1];
        for(int i = n-3; i >=0 ; i--){
            result2[i] = nums[i + 1] * result2[i + 1];
        }

        for(int i = 0; i < n; i++){
            result[i] = result[i] * result2[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }


    public boolean isOneEditDistance(String s, String d){
        int i = 0;
        int j = 0;

        if("".equals(s) && "".equals(d) || s.equals(d))
            return false;

        if(("".equals(s) && d.length() > 1) || ("".equals(d) && s.length() > 1))
            return false;

        if(("".equals(s) && d.length() == 1) || ("".equals(d) && s.length() == 1))
            return true;

        // if the string length is > 1, then execute below codes
        while(i < s.length() && j < d.length()){
            if(s.charAt(i) != d.charAt(j)){
                i--;
                j--;
                break;
            }
            i++;
            j++;
        }

        if((i == s.length() && j + 1 == d.length()) || (j == d.length() && i + 1 == s.length()))
            return true;
        i++;
        j++;
        return Objects.equals(s.substring(i + 1), d.substring(j + 1))
                || Objects.equals(s.substring(i + 1), d.substring(j))
                || Objects.equals(s.substring(i), d.substring(j + 1));
    }

    public static void main(String[] args) {
        String s = "30[a]2[bc]";
        String t = "qwert";
        System.out.println("a".substring(1));
        int[] a = {1,2,3,4};
//        System.out.println(new Solution().productExceptSelf(a));
        System.out.println(new Solution().isOneEditDistance("aab", "aaa"));

    }
}
