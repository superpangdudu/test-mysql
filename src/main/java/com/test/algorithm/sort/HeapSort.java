package com.test.algorithm.sort;

/**
 * HeapSort, a O(nlgn) sorting method
 *
 *                           1
 *                          16
 *                         /  \
 *                        2    3
 *                       14    10
 *                      / \    / \
 *                     4   5  6   7
 *                     8   7  9   3
 *                    / \ /
 *                   8  9 10
 *                   2  4 1
 *
 * Index:   1  2  3  4  5  6  7  8  9  10
 * Value:   16 14 10 8  7  9  3  2  4  1
 *
 * Parent(i) = i / 2
 * Left(i)   = 2i
 * Right(i)  = 2i + 1
 */
public class HeapSort {

    public void maxHeapify(int[] A, int i) {
        int l = 2 * (i + 1) - 1;
        int r = 2 * (i + 1);
        int largest = i;

        //
        if (l < A.length && A[l] > A[i])
            largest = l;

        //
        if (r < A.length && A[r] > A[largest])
            largest = r;

        //
        if (largest != i) {
            int tmp = A[i];
            A[i] = A[largest];
            A[largest] = tmp;

            //
            maxHeapify(A, largest);
        }
    }

    public void buildMaxHeap(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            maxHeapify(A, i);
        }
    }

    //=========================================================================
    public static void main(String[] args) {
        HeapSort obj = new HeapSort();
        int[] data = new int[] {1, 4, 3, 14, 16, 10, 8, 7, 9, 2};
        obj.buildMaxHeap(data);
        int n = 0;
    }
}
