public class Solution {
    static final String cdata = "[CDATA[";
    static final String cend  = "]]>";
    
    int parseTag(String code, int i, Stack<String> tag, Stack<Integer> context) {
        int l = code.length();
        if(code.charAt(i) != '<' || i == l - 1) { return -1;    }
        switch(code.charAt(i + 1)) {
            case '!':
                if(i + 2 +  cdata.length() > l)   return -1;
                if(!cdata.equals(code.substring(i + 2, i + 2 + cdata.length()))) return -1;
                if(context.isEmpty() || context.peek() != 0)    return -1;
                context.push(3);
                return i + 1 + cdata.length();
            case '/':
                if(tag.isEmpty() || context.isEmpty() || context.peek() != 0)   return -1;
                context.pop();
                context.push(2);
                return i + 1;
            default:
                context.push(1);
                return i;
            }
            
    }
    
    int findTagEnd(String code, int i) {
        int j = i;
        for(; j < code.length(); ++j) {
            char cur = code.charAt(j);
            //System.out.println(cur);
            if(j > i + 9)   return -1;
            if(cur == '>') {
                break;
            }
            if(cur > 'Z' || cur < 'A')    return -1;
        }
        if( i == j) return -1;
        return j;
    }

    
    public boolean isValid(String code) {
        if(code == null || code.length() == 0)  return false;
        Stack<String> tag = new Stack();
        Stack<Integer> context = new Stack();
        int type = 0;
        // type = 0, tag content. type = 1, within tag name, type 2 within end tag type = 3, within CDATA
        // System.out.println(code + "============================");
        int l = code.length();
        for(int i = 0;  i< l; i++) {
            //System.out.println(i + " " + tag + " ! " + context);
            if(context.isEmpty()) {
                if(i != 0)  return false;
                i = parseTag(code, i, tag, context);
                if(i< 0) return false;
                continue;
            }
            int j;
            switch(context.peek()) {
                case 0:
                    j = code.indexOf("<", i);
                    if(j < 0)  return false;
                    i = parseTag(code, j, tag, context);
                    if(i< 0) return false;
                    break;
                case 1:
                    j = findTagEnd(code, i);
                    if(j < 0 || j == code.length())  return false;
                    tag.push(code.substring(i, j));
                    i = j;
                    context.pop();
                    context.push(0);
                    break;
                case 2:
                    j = findTagEnd(code, i);
                    if(j < 0 || j == code.length())  return false;
                    if(tag.isEmpty() || !tag.pop().equals(code.substring(i, j)))    return false;
                    context.pop();
                    i = j;
                    break;
                case 3:
                    j = code.indexOf(cend, i);
                    if(j < 0 || j == code.length())  return false;
                    context.pop();
                    i = j + cend.length() - 1;
                    break;
                default:
                    break;
            } 
        }
        return tag.isEmpty();
    }
}
