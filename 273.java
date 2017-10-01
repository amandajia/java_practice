public class Solution {
    Map<Integer, String> table = new HashMap<>();
    void init() {
        table.put(1, "One");
        table.put(2, "Two");
        table.put(3, "Three");
        table.put(4, "Four");
        table.put(5, "Five");
        table.put(6, "Six");
        table.put(7, "Seven");
        table.put(8, "Eight");
        table.put(9, "Nine");
        table.put(10, "Ten");
        table.put(11, "Eleven");
        table.put(12, "Twelve");
        table.put(13, "Thirteen");
        table.put(14, "Fourteen");
        table.put(15, "Fifteen");
        table.put(16, "Sixteen");
        table.put(17, "Seventeen");
        table.put(18, "Eighteen");
        table.put(19, "Nineteen");
        table.put(20, "Twenty");
        table.put(30, "Thirty");
        table.put(40, "Forty");
        table.put(50, "Fifty");
        table.put(60, "Sixty");
        table.put(70, "Seventy");
        table.put(80, "Eighty");
        table.put(90, "Ninety");
        
    }
    public String numberToWords(int num) {
        if(num == 0)  return "Zero";
        init();
        String[] dw  = new String [] {"Hundred", "Thousand", "Million", "Billion"};
        StringBuilder sb = new StringBuilder();
        int unit = 0;
        while(num!= 0) {
            StringBuilder line = new StringBuilder();
            int cur = num % 1000;
            if(cur == 0)   {
                num /=1000;
                unit ++;   
                continue;
            }
            if(num/1000 != 0)   line.append(" ");
            if(cur >= 100) {
                line.append(table.get(cur/100));
                line.append(" ");
                line.append(dw[0]);
            }
            cur = cur % 100;
            if(cur != 0) {
                if(line.length()>1)
                    line.append(" ");
                if(table.get(cur)!= null) {
                    line.append(table.get(cur));
                } else {
                    int tens = cur / 10 * 10;
                    //System.out.println(tens);
                    line.append(table.get(tens));
                    line.append(" ");
                    line.append(table.get(cur%10));
                }
            }
            if(unit > 0) {
                line.append(" " + dw[unit]);
            }
            sb.insert(0, line);
            num /=1000;
            unit ++;
        }
        return sb.toString();
    }
}
