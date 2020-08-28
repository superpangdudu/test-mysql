package com.test.leetcode.array;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int max = 0;
        for (int i : height) {
            max = Math.max(i, max);
        }


        //
        int area = 0;
        int state = 0; // 0 - start, 1 - found left, 2 - found gap, 3 - found right

        // scan for each level
        for (int i = 1; i <= max; i++) {
            int left = -1;
            int right = -1;
            boolean foundGap = false;
            state = 0;

            for (int n = 0; n < height.length; n++) {
                int h = height[n];

                if (state == 0) {
                    if (h >= i) { // found left
                        left = n;
                        state = 1;
                    }

                } else if (state == 1) {
                    if (h >= i) {
                        left = n;
                    } else { // found gap
                        state = 2;
                    }

                } else if (state == 2) {
                    if (h >= i) { // found right
                        area += (n - left - 1);
                        state = 0;

                        // keep current wall
                        n -= 1;
                    }
                }
            }
        }

        return area;
    }

    /**
     * this is the accepted solution
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max)
                    left_max = height[left];
                else
                    ans += (left_max - height[left]);
                ++left;
            }
            else {
                if (height[right] >= right_max)
                    right_max = height[right];
                else
                    ans += (right_max - height[right]);
                --right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TrappingRainWater obj = new TrappingRainWater();
        //obj.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
        //obj.trap(new int[] {5,2,1,2,1,5});
        //obj.trap(new int[] {0,2,0});

        long start = System.currentTimeMillis();
        obj.trap(new int[] {});
        long end = System.currentTimeMillis();

        long used = end - start;
        System.out.println(used);
    }
}
