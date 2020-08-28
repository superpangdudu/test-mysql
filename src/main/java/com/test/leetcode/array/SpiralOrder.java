package com.test.leetcode.array;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/17.
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();

        //
        int m = matrix.length;
        if (m == 0)
            return result;

        int n = matrix[0].length;
        if (n == 0)
            return result;

        //
        int visited = 0;
        int totalCount = m * n;

        //
        int top = 0;
        int right = n - 1;
        int bottom = m - 1;
        int left = 0;

        int currentRow = top;
        int currentColumn = left;

        //
        int state = 0; // 0 - left to right, 1 - top to bottom, 2 - right to left, 3 - bottom to top

        //
        if (top == bottom) { // one row only
            for (int i = 0; i < n; i++) {
                result.add(matrix[0][i]);
            }
            return result;
        }

        //
        if (left == right) { // one column only
            for (int i = 0; i < m; i++) {
                result.add(matrix[i][0]);
            }
            return result;
        }

        //
        while (visited < totalCount) {
            result.add(matrix[currentRow][currentColumn]);
            visited += 1;

            if (state == 0) { // left to right
                currentColumn += 1;

                if (currentColumn == right) {
                    state = 1;
                    top += 1;
                }

            } else if (state == 1) { // top to bottom
                currentRow += 1;

                if (currentRow == bottom) {
                    state = 2;
                    right -= 1;
                }

            } else if (state == 2) { // right to left
                currentColumn -= 1;

                if (currentColumn == left) {
                    state = 3;
                    bottom -= 1;
                }

            } else if (state == 3) { // bottom to top
                currentRow -= 1;

                if (currentRow == top) {
                    state = 0;
                    left += 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] data = new int[3][3];
        data[0] = new int[] {1, 2, 3};
        data[1] = new int[] {4, 5, 6};
        data[2] = new int[] {7, 8, 9};

//        int[][] data = new int[2][1];
//        data[0] = new int[] {3};
//        data[1] = new int[] {2};

        SpiralOrder obj = new SpiralOrder();
        List<Integer> result = obj.spiralOrder(data);
        int n = 0;
    }
}
