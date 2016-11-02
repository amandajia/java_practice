//https://leetcode.com/problems/basic-calculator-ii/
public static int calculate(String s) {
        s=s.trim();
        int length=s.length();
        int result=0;
        int sign=1;
        boolean toTime=false;
        boolean toDivide=false;
        Stack<Integer> box=new Stack<>();
        for(int i=0;i<length;i++){
        	if(s.charAt(i)>=48 && s.charAt(i)<=57){
        		int num=s.charAt(i)%48;
        		i++;
        		while((i<length) && (s.charAt(i)>=48 && s.charAt(i)<=57)){
        			num*=10;
        			num+=s.charAt(i)%48;
        			i++;
        		}
        		i--;
        		num=num*sign;
        		sign=1;
        		box.add(num);
        		if(toTime==true){
        			num=box.pop()*box.pop();
        			toTime=false;
        			box.add(num);
        		}
        		if(toDivide==true){
        			int t=box.pop();
        			num=box.pop()/t;
        			toDivide=false;
        			box.add(num);
        		}
        	}
        	else if(s.charAt(i)=='+'){
        		sign=1;
        	}
        	else if(s.charAt(i)=='-'){
        		sign=-1;
        	}
        	else if(s.charAt(i)=='*'){
        		toTime=true;
        	}
        	else if(s.charAt(i)=='/'){
        		toDivide=true;
        	}
        }
        while(!box.isEmpty()){
        	result+=box.pop();
        }
        return result;
    }
