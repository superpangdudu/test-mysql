package com.test.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/17.
 */
public class TestIntervalIntersection {

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

        int[][] getSetPositions() {
            int maxPos = data.length * 8;
            List<int[]> posList = new LinkedList<>();

            boolean isSet = false;
            int[] range = null;

            for (int i = 0; i < maxPos; i++) {
                if (isSet(i) && !isSet) { // found first set pos
                    isSet = true;
                    range = new int[2];
                    range[0] = i;
                    range[1] = i;

                    //
                    posList.add(range);

                } else if (!isSet(i) && isSet) { // found first unset pos
                    isSet = false;
                    range[1] = i - 1;
                }
            }

            //
            int[][] result = new int[posList.size()][];
            posList.toArray(result);

            return result;
        }

        //
        static MyBitmap and(MyBitmap a, MyBitmap b) {
            MyBitmap bitmap = new MyBitmap(a.data.length);

            // assume a and b has same size for simplicity
//            int count = a.data.length * 8;
//            for (int i = 0; i < count; i++) {
//                if (a.isSet(i) && b.isSet(i))
//                    bitmap.set(i);
//            }

            for (int n = 0; n < a.data.length; n++) {
                bitmap.data[n] = (byte) (a.data[n] & b.data[n]);
            }

            return bitmap;
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        MyBitmap bitmapA = getMyBitmap(A);
        MyBitmap bitmapB = getMyBitmap(B);

        int[][] test = bitmapA.getSetPositions();
        test = bitmapB.getSetPositions();

        MyBitmap result = MyBitmap.and(bitmapA, bitmapB);
        return result.getSetPositions();
    }

    private MyBitmap getMyBitmap(int[][] data) {
        MyBitmap bitmap = new MyBitmap(12500000);

        for (int n = 0; n < data.length; n++) {
            int start = data[n][0];
            int end = data[n][1];

            for (int i = start; i <= end; i++)
                bitmap.set(i);
        }

        return bitmap;
    }

    //===================================================================================
    public static void main(String[] args) {
        MyBitmap bitmap = new MyBitmap(100000);
        boolean isSet = false;

        bitmap.set(1);
        isSet = bitmap.isSet(1);

        bitmap.set(0);
        isSet = bitmap.isSet(0);

        bitmap.set(10);
        isSet = bitmap.isSet(10);

        bitmap.set(1000);
        isSet = bitmap.isSet(1000);


        int[][] A = new int[4][2];
        A[0] = new int[]{0, 2};
        A[1] = new int[]{5, 10};
        A[2] = new int[]{13, 23};
        A[3] = new int[]{24, 25};

        int[][] B = new int[4][2];
        B[0] = new int[]{1, 5};
        B[1] = new int[]{8, 12};
        B[2] = new int[]{15, 24};
        B[3] = new int[]{25, 26};

        //
        TestIntervalIntersection obj = new TestIntervalIntersection();
        int[][] result = obj.intervalIntersection(A, B);
        int n = 0;
    }

    //输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
}
