/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false

*/
public class TwoSum {
    Map<Integer, Integer> table = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    Integer old = table.get(number);
	    if(old == null) {
	        list.add(number);
	        table.put(number, 1);
	    } else {
	        table.put(number, old + 1);
	    }
	    
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(int i = 0; i < list.size(); ++i) {
	        int k = list.get(i);
	        int other = value - k;
	        if(k == other && table.get(k) > 1) {
	            return true;
	        } else if(k!=other && table.containsKey(other))
	            return true;
	    }
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
