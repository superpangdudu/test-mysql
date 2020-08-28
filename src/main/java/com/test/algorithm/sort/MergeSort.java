package com.test.algorithm.sort;

/**
 * Created by Administrator on 2020/8/25.
 */
public class MergeSort {

    public void mergeSort(int[] A, int p, int r) {
        if (r - p <= 1)
            return;

        int q = (p + r) / 2;

        mergeSort(A, p, q);
        mergeSort(A, q, r);

        merge(A, p, q, r);
    }

    private void merge(int[] A, int p, int q, int r) {
        int n1 = q - p;
        int n2 = r - q;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        //
        for (int i = 0; i < n1; i++)
            L[i] = A[p + i];
        for (int i = 0; i < n2; i++)
            R[i] = A[q + i];

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        //
        int i = 0;
        int j = 0;
        for (int k = p; k < r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                ++i;
            } else if (L[i] > R[j]) {
                A[k] = R[j];
                ++j;
            }
        }
    }

    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int[] data = new int[] {2, 5, 4, 6, 7, 1, 2, 8, 0, 9};
        //obj.merge(data, 0, 4, data.length);
        obj.mergeSort(data, 0, data.length);

        int n = 0;
    }
}
