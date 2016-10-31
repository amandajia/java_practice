//https://leetcode.com/problems/add-two-numbers-ii/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


private int length(ListNode l){
		int i=0;
		while(l!=null){
			i++;
			l=l.next;
		}
		return i;
	}
	public ListNode addFunction(ListNode l1, ListNode l2){
        int length1=length(l1);
        int length2=length(l2);
        if(length1<length2) {
            ListNode t = l1;
            l1 = l2;
            l2 = t;
            int it = length1;
            length1 = length2;
            length2 = it;
        }
        int lengthDiff=Math.abs(length1-length2);
        ListNode head1=l1;
        ListNode head2=l2;
        ListNode pre=l1;
        for(int j=lengthDiff;j>0;j--){
        	pre=l1;
        	l1=l1.next;
        }
        int carry=0;
        int i=lengthDiff;
        int[] box=new int[length1];
        while(l2!=null){
    		l2.val=l2.val+l1.val;
    		box[i]=l2.val;
    		l1=l1.next;
    		l2=l2.next;
    		i++;
    	}
    	l1=head1;
        for(int j=0;j<lengthDiff;j++){
        	box[j]=l1.val;
        	l1=l1.next;
        }
       for(int j=length1-1;j>=0;j--){
    	   box[j]=box[j]+carry;
    	   if(box[j]>=10){
    		   box[j]=box[j]%10;
    		   carry=1;
    	   }
    	   else carry=0;
       }
       ListNode temp=new ListNode(box[0]);
       ListNode p=temp;
       ListNode newHead=temp;
       for(int j=1;j<length1;j++){ 
    	   ListNode xx=new ListNode(box[j]);
    	   temp.next=xx;
    	   temp=temp.next;
       }
       if(carry==1){
    	   newHead=new ListNode(1);
           newHead.next=p;
       }
       return newHead;
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1==null && l2==null) return null;
		return(addFunction(l1,l2));
    }
