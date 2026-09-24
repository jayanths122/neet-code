package com.neetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left+1, right+1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }

        }
        return new int[]{-1, -1};
    }

    // Input: nums = [-1,0,1,2,-1,-4]
    // Output: [[-1,-1,2],[-1,0,1]]
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    triplets.add(Arrays.asList(nums[i], nums[right], nums[left]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left-1]) left++;
                    while (left < right && nums[right] == nums[right+1]) right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return triplets;
    }

    // Input: height = [1,8,6,2,5,4,8,3,7]
    public int maxArea(int[] height) {
        int area = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            area = Math.max(area, ((right - left) * Math.min(height[left], height[right])));
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return area;
    }

    public int trap(int[] height) {
        int[] maxLeft = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                maxLeft[i] = height[i];
                continue;
            }
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }
        int[] maxRight = new int[height.length];
        for (int i = height.length - 1; i > 0; i--) {
            if (i == height.length - 1) {
                maxRight[i] = height[i];
                continue;
            }
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }
        int totalArea = 0;
        for (int i = 0; i < height.length; i++) {
            int trappedArea = Math.min(maxLeft[i], maxRight[i]) - height[i];
            trappedArea = trappedArea < 0 ? 0 : trappedArea;
            totalArea += trappedArea;
        }
        return totalArea;
    }

    public int trapTwoPointers(int[] height) {
        int maxLeft = 0; int maxRight = 0;
        int left = 0; int right = height.length - 1;
        int total = 0; 
        while (left < right) {
            if (height[left] < height[right]) {
                maxLeft = Math.max(maxLeft, height[left]);
                total += maxLeft - height[left];
                left++;
            } else {
                maxRight = Math.max(maxRight, height[right]);
                total += maxRight - height[right];
                right--;
            }
        }
        return total;
    }
}
