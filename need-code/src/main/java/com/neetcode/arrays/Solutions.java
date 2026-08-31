package com.neetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solutions {

    public static int[] topKFrequentBucket(int[] nums, int k) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int num: nums) {
            if (!seen.containsKey(num)) {
                seen.put(num, 0);
            }
            seen.put(num, seen.get(num) + 1);
        }
        List<Integer>[] countArray = new List[nums.length + 1];
        for (int key: seen.keySet()) {
            int frequency = seen.get(key);
            if (countArray[frequency] == null) {
                countArray[frequency] = new ArrayList<>();
            }
            countArray[frequency].add(key);
        }

        List<Integer> finalList = new ArrayList<Integer>();
        for (int i = countArray.length - 1; (i >= 0 && finalList.size() < k); i--) {
            if (countArray[i] != null) {
                finalList.addAll(countArray[i]);
            }
        }
        return finalList.stream().mapToInt(Integer::intValue).toArray();
    }
    

    // nums = [2, 3, 4, 5]
    // result = [ 60, 40, 30, 24 ]
    // prefix = [ 1, 2, 6, 24 ]
    // postfix = [ 60, 20, 5, 1 ]
    // product = [ 60, 40, 30, 24 ]
    public static int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] postfix = new int[nums.length];
        int[] product = new int[nums.length];
        int runningProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            prefix[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }
        runningProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            postfix[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            product[i] = prefix[i] * postfix[i];
        }
        return product;
    }

    // nums = [1, 2, 3, 4]
    // output = [ 1, 1, 2, 6 ]
    // Required = [ 24, 12, 8, 6 ]
    public static int[] productExceptSelfO_n_Memory(int[] nums) {
        int runningProduct = 1;
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = runningProduct;
            runningProduct = runningProduct * nums[i];
        }
        runningProduct = 1;
        for (int i = nums.length - 1; i >=0; i--) {
            output[i] = output[i] * runningProduct;
            runningProduct = runningProduct * nums[i];
        }
        return output;
    }

    // Eg: nums: [ 100, 4, 200, 3, 2, 1 ]
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num: set) {
            int sequence = 1;
            if (set.contains(num - 1)) {
                continue;
            }
            while (set.contains(num + sequence)) {
                sequence += 1;
            }
            longest = Math.max(sequence, longest);
        }
        return longest;
    }
}
