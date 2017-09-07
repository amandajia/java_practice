/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode p = head;
        while(n > 0 && p!=null) {
            p = p.next;
            n --;
        } 
        if (p == null && n == 0) return head.next;
        if (p == null) return head;
        ListNode s = head;
        while(p.next!=null) {
            p = p.next;
            s = s.next;
        }
        s.next = s.next.next;
        return head;
    }
}
