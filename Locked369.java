/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode plusOne(ListNode head) {
        if(head == null) {
            return new ListNode(1);
        }
        ListNode pre = null;
        ListNode p = head;
        while(p!=null) {
            if(p.val != 9)  {
                pre = p;
            }
            p = p.next;
        }
        if(pre != null) {
            pre.val ++;
        }
        else {
            pre = new ListNode(1);
            pre.next = head;
            head = pre;
        }
        p = pre.next;
        while(p!=null) {
            p.val = 0;
            p = p.next;
        }
        return head;
    }
}
