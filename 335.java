//https://leetcode.com/problems/self-crossing/
public boolean isSelfCrossing(int[] x) {
        if(x.length<=3) return false;
        if(x[0]<=0 || x[1]<=0) return true;
        int length=x.length;
        int i=2;
        while(i<length && x[i-2]<x[i]) i++;
        if(i>=length) return false;
        if((i==3 && x[i]==x[i-2]) || (i>=4 && x[i]>=x[i-2]-x[i-4])){
        	x[i-1]=x[i-1]-x[i-3];
        }
        i++;
        while(i<length){
        	if(x[i]>=x[i-2]) return true;
        	i++;
        }
        return false;
}
