//https://leetcode.com/problems/reconstruct-original-digits-from-english/
/* e:[0,1,3,3,5,7,7,8,9]
		     * f:[4,5]  5
		     * g:[8]    8
		     * h:[3,8]  3
		     * i:[5,6,8,9]  9
		     * n:[1,7,9,9]
		     * o:[0,1,2,4]  1
		     * r:[0,3,4]
		     * s:[6,7]  7
		     * t:[2,3,8]
		     * u:[4]    4
		     * v:[5,7]
		     * w:[2]    2
		     * x:[6]    6
		     * z:[0]    0
		     */
	public String originalDigits(String s) {
		int[] box=new int[10];
		for(int i=0;i<s.length();i++){
			char cur=s.charAt(i);
			if(cur=='z') box[0]++;
			else if(cur=='o') box[1]++;
			else if(cur=='w') box[2]++;
			else if(cur=='h') box[3]++;
			else if(cur=='u') box[4]++;
			else if(cur=='f') box[5]++;
			else if(cur=='x') box[6]++;
			else if(cur=='s') box[7]++;
			else if(cur=='g') box[8]++;
			else if(cur=='i') box[9]++;
		}
		box[5]=box[5]-box[4];
		box[3]=box[3]-box[8];
		box[7]=box[7]-box[6];
		box[1]=box[1]-(box[0]+box[2]+box[4]);
		box[9]=box[9]-(box[5]+box[6]+box[8]);
		StringBuilder result=new StringBuilder();
		for(int i=0;i<10;i++){
			for(int j=0;j<box[i];j++){
				result.append(i);
			}
		}
		return result.toString();
    }
