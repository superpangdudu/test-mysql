package com.test.leetcode.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.

 Example 1:

 Input: [1,2,0]
 Output: 3
 Example 2:

 Input: [3,4,-1,1]
 Output: 2
 Example 3:

 Input: [7,8,9,11,12]
 Output: 1
 Follow up:

 Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive {

    static class MyBitmap {
        private byte[] data;

        MyBitmap(int bytes) {
            data = new byte[bytes];
        }

        void set(int pos) {
            int shift = pos / 8;
            int bit = pos % 8;

            data[shift] |= (0x01 << bit);
        }

        boolean isSet(int pos) {
            int shift = pos / 8;
            int bit = pos % 8;

            return (data[shift] & (0x01 << bit)) > 0;
        }
    }

    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0)
                nums[i] = 0;
            if (nums[i] > nums.length)
                nums[i] = nums.length + 1;
        }

        MyBitmap bitmap = new MyBitmap(nums.length / 8 + 1);

        for (int i : nums) {
            bitmap.set(i);
        }

        //
        for (int i = 1; i <= nums.length; i++) {
            if (bitmap.isSet(i) == false)
                return i;
        }

        return nums.length + 1;
    }

    //===================================================================================
    public int firstMissingPositive2(int[] nums) {


        return 0;
    }

    //===================================================================================
    public static void main(String[] args) {
        FirstMissingPositive obj = new FirstMissingPositive();
        int n = obj.firstMissingPositive(new int[] {1, Integer.MAX_VALUE});
        n = 0;
    }
}
