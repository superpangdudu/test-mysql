package com.test.leetcode.array;

/**
 * Created by Administrator on 2020/8/18.
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value > target)
                continue;

            for (int n = i + 1; n < nums.length; n++) {
                int candidate = nums[n];
                if (candidate > target)
                    continue;

                if (value + candidate == target)
                    return new int[] {i, n};
            }
        }

        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;

        int[] differences = new int[length];
        Map<Integer, Integer> numberIndexMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            int value = nums[i];
            if (!numberIndexMap.containsKey(value))
                numberIndexMap.put(nums[i], i);

            //
            differences[i] = target - nums[i];

            Integer index = numberIndexMap.get(differences[i]);
            if (index == null
                    || index == i)
                continue;

            return new int[] {index, i};
        }

        return null;
    }

    //===================================================================================
    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;

//        int[] nums = {3, 2, 4};
        int[] nums = {3, 3};
        int target = 6;

        TwoSum obj = new TwoSum();
        int[] retulst = obj.twoSum2(nums, target);
        int n = 0;
    }
}
