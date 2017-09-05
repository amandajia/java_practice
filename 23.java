/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists ==null || lists.length == 0) return null; 
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, (ListNode l1, ListNode l2) -> (l1.val - l2.val));
        ListNode head = null;
        
        for(ListNode i: lists)  {
            if(i != null)
                pq.add(i);
        }
        ListNode last = null;
        
        while(!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if(cur.next!= null)     pq.add(cur.next);
            if(head == null ) {
                head = cur;
            } else {
                last.next = cur;
            }
            last = cur;
        }
        return head;
    }
}
