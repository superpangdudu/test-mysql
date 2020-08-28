package com.test.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            Integer pos = charMap.get(c);

            // if find repeated character
            if (pos != null) {
                // update max length
                int length = charMap.values().size();
                if (length > maxLength)
                    maxLength = length;

                // FIXME
                // remove all characters before the repeated one
                Character[] characters = new Character[charMap.keySet().size()];
                charMap.keySet().toArray(characters);
                for (Character ch : characters) {
                    int tmp = charMap.get(ch);
                    if (tmp <= pos)
                        charMap.remove(ch);
                }
            }

            //
            charMap.put(c, i);
        }

        //
        int length = charMap.values().size();
        if (length > maxLength)
            maxLength = length;

        return maxLength;
    }

    //===================================================================================
    public static void main(String[] args) {
        LengthOfLongestSubstring obj = new LengthOfLongestSubstring();
        int length = obj.lengthOfLongestSubstring("abcabcbb");
    }
}
