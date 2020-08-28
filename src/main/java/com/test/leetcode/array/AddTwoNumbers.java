package com.test.leetcode.array;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //===================================================================================
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long number1 = toNumber(l1);
        long number2 = toNumber(l2);
        long result = number1 + number2;

        return toListNode(result);
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode lastNode = null;

        int carry = 0;
        do {
            int number1 = 0;
            int number2 = 0;

            if (l1 != null) {
                number1 = l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                number2 = l2.val;
                l2 = l2.next;
            }

            //
            int result = number1 + number2 + carry;
            carry = 0;

            if (result >= 10) {
                carry = result / 10;
                result = result % 10;
            }

            //
            ListNode node = new ListNode(result);
            lastNode = node;

            if (head == null) {
                head = node;
            } else {
                lastNode.next = node;
            }

        } while (l1 != null
                || l2 != null
                || carry != 0);

        return head;
    }

    private long toNumber(ListNode listNode) {
        long result = 0;
        int pos = 0;

        while (listNode != null) {
            if (listNode.val != 0) {
                long nodeValue = (long) Math.pow(10, pos);
                nodeValue *= listNode.val;
                result += nodeValue;
            }

            //
            listNode = listNode.next;
            ++pos;
        }

        return result;
    }

    private ListNode toListNode(long number) {
        long quotient = 0;
        long reminder = 0;

        ListNode head = null;
        ListNode lastNode = null;

        do {
            quotient = number / 10;
            reminder = number % 10;
            number = quotient;

            ListNode node = new ListNode((int) reminder);
            if (head == null) {
                head = node;
                lastNode = node;

            } else {
                lastNode.next = node;
                lastNode = node;
            }

        } while (quotient > 0);

        return head;
    }

    //===================================================================================
    public static void main(String[] args) {
//        ListNode node1 = new ListNode(0);
//        ListNode node2 = new ListNode(0);
//        ListNode node3 = new ListNode(5);
//
//        node1.next = node2;
//        node2.next = node3;
//
        //
        AddTwoNumbers obj = new AddTwoNumbers();
//        obj.toNumber(node1);
//
//        ListNode node = obj.toListNode(501);

        //
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode l22 = new ListNode(9);
        ListNode l23 = new ListNode(9);
        ListNode l24 = new ListNode(9);
        ListNode l25 = new ListNode(9);
        ListNode l26 = new ListNode(9);
        ListNode l27 = new ListNode(9);
        ListNode l28 = new ListNode(9);
        ListNode l29 = new ListNode(9);
        ListNode l210 = new ListNode(9);

        //l2.next = l22;
//        l22.next = l23;
//        l23.next = l24;
//        l24.next = l25;
//        l25.next = l26;
//        l26.next = l27;
//        l27.next = l28;
//        l28.next = l29;
//        l29.next = l210;
        obj.addTwoNumbers2(l1, l2);
    }
}
