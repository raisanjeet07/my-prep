package com.san.ds;

import java.util.HashMap;
import java.util.Map;

public class DFSExample {

    public static void main(String[] args) {
        int[] arr = {1,2,0,1};
        System.out.println(new DFSExample().longestConsecutive(arr));
    }


    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num))
                continue;
            map.put(num, 0);
            if(map.containsKey(num - 1)){
                map.put(num, map.get(num) + 1);
                map.put(num - 1, map.get(num - 1) + 2);
            }
            if(map.containsKey(num + 1)){
                map.put(num, map.get(num) + 2);
                map.put(num + 1, map.get(num + 1) + 1);
            }
        }
        int max = 1;
        int tmp;
        for(int num : nums){
            if(!map.containsKey(num))
                continue;
            int directions = map.get(num);
                switch (directions){
                    case 1:{
                        tmp = goMaxDepth(num, map);
                        tmp = num - tmp + 1;
                        max = Math.max(max, tmp);

                    }
                    case 2:{
                        tmp = goMaxUp(num, map) - num + 1;
                        max = Math.max(max, tmp);
                    }
                    case 3: {
                        tmp = goMaxDepth(num, map);
                        tmp = goMaxUp(num+1, map) - tmp + 1;
                        max = Math.max(max, tmp);
                    }
                }

        }

        return max;
    }

    private int goMaxDepth(int num, Map<Integer, Integer> map){
        while(map.containsKey(num)){
            map.remove(num);
            num--;
        }
        return 1 + num;
    }

    private int goMaxUp(int num, Map<Integer, Integer> map){
        while(map.containsKey(num)){
            map.remove(num);
            num++;
        }
        return num - 1;
    }
}
