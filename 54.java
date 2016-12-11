//https://leetcode.com/problems/spiral-matrix/
 public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result= new LinkedList<Integer>();
		if(matrix==null || matrix.length==0 ) return result;
		int shang=0;
		int xia=matrix.length-1;
		int zuo=0;
		int you=matrix[0].length-1;
		while(shang<=xia && zuo<=you){
			for(int i=zuo;i<=you;i++){
				result.add(matrix[shang][i]);
			}
			shang++;
			for(int i=shang;i<=xia;i++){
				result.add(matrix[i][you]);
			}
			you--;
			if(shang<=xia){
			    for(int i=you;i>=zuo;i--){
        		    result.add(matrix[xia][i]);
        	    }
        	    xia--;
			}
			if(zuo<=you){
			    for(int i=xia;i>=shang;i--){
        		    result.add(matrix[i][zuo]);
        	    }
        	    zuo++;
			}
		}
		return result;
    }
