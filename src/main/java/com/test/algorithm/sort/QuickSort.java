package com.test.algorithm.sort;

/**
 * QuickSort
 *
 * Partition:
 *  a) i pj                          r
 *       2   8   7   1   3   5   6   4
 *
 *  b) p,i j                       r
 *     2   8   7   1   3   5   6   4
 *
 *  c) p,i     j                   r
 *     2   8   7   1   3   5   6   4
 *
 *  d) p,i         j               r
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
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (A[j] <= x) {
                i = i + 1;

                if (i != j) {
                    int tmp = A[j];
                    A[j] = A[i];
                    A[i] = tmp;
                }
            }
        }

        int tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;

        return i + 1;
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
