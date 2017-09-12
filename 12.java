public class Solution {
    public String intToRoman(int num) {
        Map<Integer, String> dict = new HashMap<> ();
        dict.put(1, "I");
        dict.put(4, "IV");
        dict.put(5, "V");
        dict.put(9, "IX");
        dict.put(10, "X");
        dict.put(40, "XL");
        dict.put(50, "L");
        dict.put(90, "XC");
        dict.put(100, "C");
        dict.put(400, "CD");
        dict.put(500, "D");
        dict.put(900, "CM");
        dict.put(1000, "M");
        List<Integer> thres  = new ArrayList<>(dict.keySet());
        Collections.sort(thres, Collections.reverseOrder());
        int index = 0;
        StringBuffer ret = new StringBuffer();
        while(num!=0 ) {
        	while(num < thres.get(index)) index++;
        	ret.append(dict.get(thres.get(index)));
        	num -= thres.get(index);
        }
        return ret.toString();
   }
}
