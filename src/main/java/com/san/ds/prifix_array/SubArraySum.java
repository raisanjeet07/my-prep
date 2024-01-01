package com.san.ds.prifix_array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {
    public static int subArraySum(int[] nums, int k) {
        // subArray: contiguous indexed array
        int count = 0;
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i -1] + nums[i];
        }
        Map<Integer, Integer> nf = new HashMap<>();
        nf.put(0, 1);
        int lookUpInt;
        int sum;
        for(int i =0; i < nums.length; i++){
            sum = prefixSum[i];
            lookUpInt = sum - k;
            if(nf.containsKey(lookUpInt)){
                count += nf.get(lookUpInt);
            }
            if(nf.containsKey(sum)){
                nf.put(sum, nf.get(sum) + 1);
            }else{
                nf.put(sum, 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subArraySum(new int[]{1,-1,0}, 0));
    }
}
