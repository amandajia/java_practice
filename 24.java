/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode ret = head.next;
        ListNode pre = null;
        while(head!= null) {
            if (head.next == null) 
                break;
            ListNode next = head.next.next;
            head.next.next = head;
            if (pre != null) pre.next = head.next;
            head.next = next;
            pre = head;
            head = next;
        }
        return ret;
    }
}
