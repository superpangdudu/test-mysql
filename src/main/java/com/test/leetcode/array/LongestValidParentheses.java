package com.test.leetcode.array;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 Example 1:

 Input: "(()"
 Output: 2
 Explanation: The longest valid parentheses substring is "()"
 Example 2:

 Input: ")()())"
 Output: 4
 Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxMatchedLength = 0;
        int state = 0; // 0 - for invalid, 1 - for left, 2 - right

        int currentMatchedLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (state == 0) {
                if (c == '(') {
                    state = 1;
                } else {
                    maxMatchedLength = Math.max(maxMatchedLength, currentMatchedLength);
                    currentMatchedLength = 0;
                }

            } else if (state == 1) {
                if (c == ')') {
                    state = 0;
                    currentMatchedLength += 2;
                } else {
                    currentMatchedLength = 0;
                }

                maxMatchedLength = Math.max(maxMatchedLength, currentMatchedLength);
            }
        }

        return maxMatchedLength;
    }

    public int longestValidParentheses2(String s) {
        int maxMatchedLength = 0;
        int currentMatchedLength = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(')
                stack.push((int) c);
            else {
                try {
                    Integer value = stack.pop();

                    currentMatchedLength += 2;
                    maxMatchedLength = Math.max(maxMatchedLength, currentMatchedLength);

                } catch (Exception e) {
                    currentMatchedLength = 0;
                }
            }
        }

        return maxMatchedLength;
    }

    //===================================================================================
    static class Parentheses {
        char left = 0;
        char right = 0;
        Parentheses prev = null;
        Parentheses next = null;

        boolean isValid() {
            return left == '(' && right == ')';
        }
    }

    Parentheses head = null;
    Parentheses tail = null;

    void addParentheses(char c) {
        Parentheses node = new Parentheses();
        node.left = c;

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public int longestValidParentheses3(String s) {
        //
        int maxMatchedLength = 0;
        int currentMatchedLength = 0;

        //
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //
            if (c == ')' && head == null)
                continue;

            if (c == '(') {
                Parentheses node = new Parentheses();
                node.left = c;

                //
                addParentheses(c);

            } else {
                Parentheses node = tail;

                boolean found = false;
                while (node != null) {
                    if (node.isValid()) {
                        node = node.prev;

                    } else {
                        if (node.left == '(') {
                            node.right = c;
                            found = true;
                        }
                        break;
                    }
                }

                //
                if (found == false) {
                    addParentheses(c);
                }
            }
        }

        //
        Parentheses node = head;
        while (node != null) {
            if (node.isValid()) {
                currentMatchedLength += 2;
                maxMatchedLength = Math.max(maxMatchedLength, currentMatchedLength);
            } else {
                currentMatchedLength = 0;
            }

            node = node.next;
        }

        return maxMatchedLength;
    }


    public static void main(String[] args) {
        LongestValidParentheses obj = new LongestValidParentheses();

        //obj.longestValidParentheses3(")(()))))())(((()");
        //obj.longestValidParentheses3(")(()())())(((()))(()()()(()(()(())))(())()((()()(((()())()))(()()())())(())(()(()()()()))(((()())))(((()))))()()()))");
        obj.longestValidParentheses3(")(()())())(((()))(()()()(()(()(())))(())()((()()(((()())()))(()()())())(())(()(()()()()))(((()())))(((()))))()()())))(()))))())(((()");
        //obj.longestValidParentheses3(")(()())())(((()))(()()()(()(()(())))(())()((()()(((()())()))(()()())())(())(()(()()()()))(((()())))(((()))))()()())))(()))))())(((()");
    }
}
