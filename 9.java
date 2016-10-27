//https://leetcode.com/problems/palindrome-number/
public static boolean isPalindrome(int x) {
        if(x>=0 && x<10) return true;
        if(x<0 || x==10) return false;
        double length=0;
        int temp=x;
        while(temp>=10){ // count except the top digit, how many digits rest;
        	length++;
        	temp/=10;
        }
        int iter=((int)length+1)/2;
        temp=x;
        for(int i=0;i<iter;i++){
        	int top=temp/((int)Math.pow(10, length));
        	int tail=temp%10;
        	if(top!=tail) return false;
        	temp-=top*(int)Math.pow(10, length);
        	temp=(temp-tail)/10;
        	length-=2;
        }
        return true;
    }
