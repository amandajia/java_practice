//https://leetcode.com/problems/coin-change/
public int coinChange(int[] coins, int amount) {
        if(coins==null || coins.length==0 || amount<0) return -1;
        if(amount==0) return 0;
        int[] box=new int[amount+1];
        for(int i=0;i<=amount;i++) box[i]=Integer.MAX_VALUE-1;
        box[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i && box[i-coins[j]]+1<box[i]){
                    box[i]= box[i-coins[j]]+1;
                }
            }
        }
        if(box[amount]==Integer.MAX_VALUE-1) return -1;
        return box[amount];
    }
