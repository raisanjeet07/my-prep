package com.san.ds;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingSubString {
    public int longestSubstring(String s, int k) {
        if(s == null || "".equals(s))
            return 0;
        String splitChar = getSplitCharIfFrequencyIsLess(s, k);
        if("".equals(splitChar))
            return s.length();
        String[] strs = s.split(splitChar);
        int ans = 0;
        int tmp;
        for(String str : strs){
            tmp = longestSubstring(str, k);
            if(ans < tmp)
                ans = tmp;
        }
        return ans;
    }

    private String getSplitCharIfFrequencyIsLess(String str, int f){
        Map<Character, Integer> table = getCharCountMap(str);
        if(table.size() == 0)
            return "";
        Map.Entry<Character, Integer> entry = table.entrySet().stream().filter(characterIntegerEntry -> characterIntegerEntry.getValue() < f).findAny().orElseGet(() -> null);
        return entry == null ? "" : entry.getKey()+"";
    }

    private Map<Character, Integer> getCharCountMap(String s){
        Map<Character, Integer> table = new HashMap<>();
        int i =0;
        int n = s.length();
        int ans = 0;
        char c;
        while(i < n){
            c = s.charAt(i);
            table.putIfAbsent(c, 0);
            table.put(c, table.get(c)+1);
            i++;
        }
        return table;
    }

    public static void main(String[] args) {
        LongestRepeatingSubString l = new LongestRepeatingSubString();
        System.out.println(l.longestSubstring("weitong", 2));
    }
}
