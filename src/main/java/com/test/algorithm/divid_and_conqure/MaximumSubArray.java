package com.test.algorithm.divid_and_conqure;

/**
 * MaximumSubArray
 *
 */
public class MaximumSubArray {

    /**
     *
     */
    private static class Maximum {
        Maximum(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }

        int low;
        int high;
        int sum;
    }

    /**
     *
     * @param A
     * @param low
     * @param high
     * @return
     */
    public Maximum findMaximumSubArray(int[] A, int low, int high) {
        if (low == high - 1)
            return new Maximum(low, high, A[low]);

        int mid = (low + high) / 2;

        Maximum leftMaximum = findMaximumSubArray(A, low, mid);
        Maximum rightMaximum = findMaximumSubArray(A, mid, high);
        Maximum crossingMaximum = findMaxCrossingSubArray(A, low, mid, high);

        if (leftMaximum.sum >= rightMaximum.sum
                && leftMaximum.sum >= crossingMaximum.sum)
            return leftMaximum;
        else if (rightMaximum.sum >= leftMaximum.sum
                && rightMaximum.sum >= crossingMaximum.sum)
            return rightMaximum;

        return crossingMaximum;
    }

    private Maximum findMaxCrossingSubArray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;

        int maxLeft = 0;
        int maxRight = 0;

        //
        for (int i = mid - 1; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        //
        sum = 0;
        for (int i = mid; i < high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        //
        return new Maximum(maxLeft, maxRight, leftSum + rightSum);
    }

    public static void main(String[] args) {
        int[] data = new int[] {13, -3, -25, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

        MaximumSubArray obj = new MaximumSubArray();
        Maximum ret = obj.findMaximumSubArray(data, 0, data.length);
        int n = 0;
    }
}
