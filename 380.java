public class RandomizedSet {
    List<Integer> rand = new ArrayList<>();
    Map<Integer, Integer> vi = new HashMap<>();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(vi.containsKey(val)) {
            return false;
        }
        vi.put(val, rand.size());
        rand.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!vi.containsKey(val)) {
            return false;
        }
        int old = vi.get(val);
        int lastVal = rand.get(rand.size() - 1);
        rand.set(old, lastVal);
        vi.put(lastVal, old);
        rand.remove(rand.size() - 1);
        vi.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return rand.get((int)(Math.random() * rand.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
