public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if(list1==null || list2==null)  return null;
        Map<String, Integer> tab = new HashMap<>();
        for(int i = 0; i < list1.length; ++i) {
            tab.put(list1[i], i);
        }
        int low = Integer.MAX_VALUE;
        List<String> ret = new ArrayList<>();
        for(int i =0; i < list2.length && i <= low; ++i) {
            Integer index = tab.get(list2[i]);
            if(index == null)   continue;
            if(index + i < low) {
                low = index+i;
                ret = new ArrayList<>();
                ret.add(list2[i]);
            } else if(index + i == low) {
                ret.add(list2[i]);
            }
        }
        String [] r = new String[ret.size()];
        for(int i = 0; i < ret.size(); ++i) {
            r[i] = ret.get(i);
        }
        return r;
    }
}
