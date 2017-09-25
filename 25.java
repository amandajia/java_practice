/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    ListNode reverse(ListNode head, int k) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while(k > 0 && cur!= null) {
            cur.next = pre;
            k --;
            pre = cur;
            cur = next;
            if(next!= null) next = next.next;
        }
        head.next = cur;
        return pre;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) return head;
        ListNode p = head;
        ListNode cur = head;
        ListNode pre = null;
        ListNode ret = null;
        while (p!= null) {
            int count = k;
            while(count >0 &&  p != null ) {
                p = p.next;
                count --;
            }
            if(count >0) {
                break;
            }
            ListNode nh = reverse(cur, k);
            if(ret == null) ret = nh;
            if(pre != null) pre.next = nh;
            pre = cur;
            cur = p;
        }
        
        return (ret == null )? head: ret;
    }
}
