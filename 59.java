//https://leetcode.com/problems/spiral-matrix-ii/
public int[][] generateMatrix(int n) {
        int[][] result=new int[n][n];
        if(n==0) return result;
        int shang=0;
        int xia=n-1;
        int zuo=0;
        int you=n-1;
        int x=1;
        while(shang<=xia && zuo<=you){
        	for(int i=zuo;i<=you;i++){
        		result[shang][i]=x;
        		x++;
        	}
        	shang++;
        	for(int i=shang;i<=xia;i++){
        		result[i][you]=x;
        		x++;
        	}
        	you--;
        	for(int i=you;i>=zuo;i--){
        		result[xia][i]=x;
        		x++;
        	}
        	xia--;
        	for(int i=xia;i>=shang;i--){
        		result[i][zuo]=x;
        		x++;
        	}
        	zuo++;
        }
        return result;
    }
