https://leetcode.com/problems/multiply-strings/
public static String multiply(String num1, String num2) {
		if(num1==null && num2==null) return null;
		int l1=num1.length();
		int l2=num2.length();
		if(num1.charAt(0)==48 || num2.charAt(0)==48) return "0";
		int[] box=new int[l1+l2];
		int carry=0;
		int temp=0;
		int start=l1+l2-1;
		for(int i=l2-1;i>=0;i--){  
			int id=start;
			for(int j=l1-1;j>=0;j--){
				temp=box[id]+(num1.charAt(j)-'0')*(num2.charAt(i)-'0')+carry;
				int t=temp;
				carry=t/10;
				box[id]=temp%10;
				id--;
			}
			if(carry!=0){
				box[id]=carry;
				carry=0;
			}
			start--;
		}
		if(carry!=0) box[0]=carry;
		int itr=0;
		if(box[0]==0) itr=1;
		StringBuilder sb=new StringBuilder();
		for(;itr<box.length;itr++){
			sb.append(box[itr]);
		}
		return sb.toString();
    }
