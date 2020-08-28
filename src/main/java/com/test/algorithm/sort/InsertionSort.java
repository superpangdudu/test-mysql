package com.test.algorithm.sort;

/**
 * Created by Administrator on 2020/8/21.
 */
public class InsertionSort {

    public void sort(int[] data) {
        for (int j = 1; j < data.length; j++) {
            int key = data[j];

            // Insert A[j] into the sorted sequence A[0..j - 1]
            int i = j - 1;

            while (i >= 0 && data[i] > key) {
                data[i + 1] = data[i];
                --i;
            }

            data[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort obj = new InsertionSort();
        int[] data = new int[] {5, 2, 4, 6, 1, 3};
        obj.sort(data);
        int n = 0;
    }
}
