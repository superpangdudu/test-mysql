package com.test.algorithm.sort;

/**
 * BubbleSort
 */
public class BubbleSort {
    public void sort(int[] A) {
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int value = A[j];
                if (value > A[j + 1]) {
                    A[j] = A[j + 1];
                    A[j + 1] = value;
                }
            }
        }
    }

    //===================================================================================
    public static void main(String[] args) throws Exception {
        int[] data = new int[] {2, 5, 4, 6, 7, 1, 2, 8, 0, 9};
        BubbleSort obj = new BubbleSort();
        obj.sort(data);
        int n = 0;
    }
}
