//https://leetcode.com/problems/range-sum-query-mutable/
public class NumArray {
    int[] arr;
    int size;
    public NumArray(int[] nums) {
        if(nums==null || nums.length==0) return;
        size=nums.length;
        int height=(int)(Math.ceil(Math.log(nums.length)/Math.log(2)));
        arr=new int[2*(int)Math.pow(2,height)-1];
        constructTree(nums,0,nums.length-1,0);
    }
    public int getMid(int start, int end){
        return start+(end-start)/2;
    }
    public int constructTree(int[] nums, int start, int end, int index){
        if(start==end){
            arr[index]=nums[start];
            return arr[index];
        }
        int mid=getMid(start,end);
        arr[index]=constructTree(nums,start,mid,index*2+1)+constructTree(nums,mid+1,end,index*2+2);
        return arr[index];
    }
    /*
    public void updateTree(int i, int val, int start, int end, int index){
        if(arr==null || i<start || i>end) return;
        int mid=getMid(start,end);
        if (i<=mid) updateTree(i, val, index*2+1, start, mid);
        else updateTree(i, val, index*2+2, mid+1, end);
        arr[index]=arr[index*2+1]+arr[index*2+2];
    }
    */
    void update(int i, int val) {
        if(i<0 || i>=size) return;
        // find arr index based on nums index
        int start = 0; int end = size -1;
        int arrIndex = 0;
        while(start != end) {
            int mid = getMid(start, end);
            if( start<= i && i<= mid) {
                end = mid;
                arrIndex = arrIndex * 2 + 1;
            } else {
                start = mid + 1;
                arrIndex = arrIndex * 2 + 2;
            }
        }
        // update parent's arr value,
        int diff = val - arr[arrIndex];
        arr[arrIndex] = val;
        while(arrIndex!=0) {
            arrIndex = (arrIndex - 1)/2;
            arr[arrIndex] += diff;
        }
        
    }
    public int quaryRangeSum(int requiredLeft, int requiredRight, int start, int end, int index){
        if(requiredLeft<=start && requiredRight>=end) return arr[index];
        if(end<requiredLeft || start>requiredRight) return 0;
        int mid=getMid(start,end);
        return quaryRangeSum(requiredLeft,requiredRight,start,mid,2*index+1)+quaryRangeSum(requiredLeft,requiredRight,mid+1,end,2*index+2);
    }
    public int sumRange(int i, int j) {
        if(arr == null || i<0 || j>=size || i>j) return -1;
        return quaryRangeSum(i,j,0,size-1,0);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
