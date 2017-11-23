/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = cur.next;
        while(cur != null) {
            cur.next = pre; 
            pre = cur;
            cur = next;
            if(next != null) next = next.next;
        }
        return pre;
    }
    
    public void reorderList(ListNode head) {
        if(head == null || head.next == null ) return ;
        // separate list to two parts
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next !=null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        slow = reverse(slow);
        pre = head;
        while(pre != null) {
            ListNode pn = pre.next;
            ListNode sn = slow.next;
            pre.next = slow;
            if(pn != null)
                slow.next = pn;
            pre = pn;
            slow = sn;
        }
    }
}
