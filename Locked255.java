/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/
public class Solution {
    
    boolean vp(int[] pre, int start, int end) {
        if(start >= end)    return true;
        int pivot = pre[start];
        int j = -1;
        for(int i = start + 1; i<end; ++i) {
            if(pre[i] > pivot && j < 0)
                j = i;
            if(j>0 && pre[i] < pivot)
                return false;
        }
        if(j < 0)
            j = end;
        return vp(pre, start+1, j) && vp(pre, j, end);
    }
    
    public boolean verifyPreorder(int[] preorder) {
        if(preorder == null) return true;
        return vp(preorder, 0, preorder.length);
    }
}
