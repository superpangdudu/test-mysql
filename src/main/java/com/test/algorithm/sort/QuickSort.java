package com.test.algorithm.sort;

import java.util.Random;

/**
 * QuickSort
 *
 * Partition:
 *  a) pij                         r
 *     2   8   7   1   3   5   6   4
 *
 *  b) pi  j                       r
 *     2   8   7   1   3   5   6   4
 *
 *  c) pi      j                   r
 *     2   8   7   1   3   5   6   4
 *
 *  d) pi          j               r
 *     2   8   7   1   3   5   6   4
 *
 * e)  p   i           j           r
 *     2   1   7   8   3   5   6   4
 *
 * f)  p       i           j       r
 *     2   1   3   8   7   5   6   4
 *
 * g)  p       i               j   r
 *     2   1   3   8   7   5   6   4
 *
 * h)  p       i                   r
 *     2   1   3   8   7   5   6   4
 *
 * i)  p       i                   r
 *     2   1   3   4   7   5   6   8
 *
 */
public class QuickSort {

    /**
     *
     * @param A
     * @param p
     * @param r
     * @return
     */
    private int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p;

        for (int j = p; j < r; j++) {
            if (A[j] <= x) {

                if (i != j) {
                    int tmp = A[j];
                    A[j] = A[i];
                    A[i] = tmp;
                }

                i = i + 1;
            }
        }

        int tmp = A[i];
        A[i] = A[r];
        A[r] = tmp;

        return i;
    }

    /**
     *
     * @param A
     * @param p
     * @param r
     * @return
     */
    private int randomizedPartition(int A[], int p, int r) {
        // exchange the last one and a random position value
        Random random = new Random();
        int randomInt = random.nextInt(r);

        int tmp = A[r];
        A[r] = A[randomInt];
        A[randomInt] = tmp;

        //
        return partition(A, p, r);
    }

    /**
     *
     * @param A
     * @param p
     * @param r
     */
    public void quickSort(int A[], int p, int r) {
        if (p >= r)
            return;

        int q = partition(A, p, r);
        quickSort(A, p, q - 1);
        quickSort(A, q + 1, r);
    }

    //=========================================================================
    public static void main(String[] args) {
        QuickSort obj = new QuickSort();
        int[] data = new int[] {2, 8, 7, 1, 3, 5, 6, 9};
        obj.quickSort(data, 0, data.length - 1);
        //int p = obj.partition(data, 0, data.length - 1);
        int n = 0;
    }
}
