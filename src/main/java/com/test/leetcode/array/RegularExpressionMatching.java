package com.test.leetcode.array;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 Example 3:

 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".
 Example 4:

 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 Example 5:

 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false
 */
public class RegularExpressionMatching {

    private static int STATE_NORMAL = 0;
    private static int STATE_DOT = 1;
    private static int STATE_STAR = 2;

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0)
            return true;

        //
        int pos = 0;
        int state = STATE_NORMAL;

        //


        return true;
    }

    //===================================================================================
    public static void main(String[] args) {
        RegularExpressionMatching obj = new RegularExpressionMatching();
        obj.isMatch("aa", "a");
    }
}
