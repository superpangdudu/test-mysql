package com.test.leetcode.array;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"
 */
public class LongestPalindrome {

    /**
     * Using dynamic programming
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        boolean[][] P = new boolean[length][length];
        String result = "";

        //
        for (int l = 0; l < length; l++) {
            for (int i = 0; i < length; i++) {
                int j = i + l;
                if (j >= length)
                    break;

                if (l == 0)
                    P[i][j] = true;
                else if (l == 1)
                    P[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    P[i][j] = (P[i + 1][j - 1] && (s.charAt(i) == s.charAt(j)));

                if (P[i][j] && (l + 1) > result.length())
                    result = s.substring(i, j + 1);
            }
        }

        return result;
    }

    //===================================================================================
    public static void main(String[] args) {
        LongestPalindrome obj = new LongestPalindrome();
        obj.longestPalindrome("babad");
    }
}
