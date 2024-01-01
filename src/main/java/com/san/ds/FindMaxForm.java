package com.san.ds;

import java.util.*;

public class FindMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        return reachToTarget(m, n, new HashSet<>(), new PriorityQueue<>(Arrays.asList(strs)));
    }
    private int reachToTarget(int cur0, int cur1, Set<String> curSet, Queue<String> currentStrSet){
        if(currentStrSet.size() <= 0)
            return -1;
        if(cur0 == 0 && cur1 == 0){
            System.out.println(curSet);
            return curSet.size();
        }
        String str = currentStrSet.poll();
        int withOutCurrStr = reachToTarget(cur0, cur1, curSet, currentStrSet);
        int ones = getCount(str, "1");
        int zeros = getCount(str, "0");
        int withCurrentStr = 0;
        if((cur1 - ones) >= 0 && (cur0 - zeros) >= 0){
            curSet.add(str);
            withCurrentStr = reachToTarget(cur0 - zeros, cur1 - ones, curSet, currentStrSet);
            curSet.remove(str);
        }
        return Math.max(withOutCurrStr, withCurrentStr);
    }

    private int getCount(String str, String charToCount){
        return (int)Arrays.stream(str.split("")).filter(s -> s.equals(charToCount)).count();
    }

    public static void main(String[] args) {
        String[] strs = {"10","0001","111001","1","0"};
        int m = 5;
        int n = 3;
        System.out.println(new FindMaxForm().findMaxForm(strs, m, n));
    }
}
