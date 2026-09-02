package com.neetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Revision {
    public static int[] topKFrequentBucket(int nums[], int k) {
       HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int num: nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            if (buckets[entry.getValue()] == null) {
                buckets[entry.getValue()] = new ArrayList<>();
            }
            buckets[entry.getValue()].add(entry.getKey());
        }

        ArrayList<Integer> topFrequentList = new ArrayList<>();
        for (int i = buckets.length - 1; (i >= 0 && topFrequentList.size() < k); i--) {
            if (buckets[i] != null) {
                for (int j = 0; (j < buckets[i].size() && topFrequentList.size() < k); j++) {
                    topFrequentList.add(buckets[i].get(j));
                }
            }
        }
        return topFrequentList.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int runningProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            prefix[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }

        int[] postfix = new int[nums.length];
        runningProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            postfix[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }

        int[] product = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            product[i] = prefix[i] * postfix[i];
        }
        return product;
    }

    public int[] productExceptSelfO_n_Memory(int[] nums) {
        int runningProduct = 1;
        int[] product = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            product[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }

        runningProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            product[i] = product[i] * runningProduct;
            runningProduct = runningProduct * nums[i];
        }
        return product;
    }
} 
