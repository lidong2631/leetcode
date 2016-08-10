import java.util.Random;

public class RandomizedCollection {

    List<Integer> list;
    Map<Integer, Set<Integer>> map;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new LinkedList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isContain = map.containsKey(val);
        if (!isContain) map.put(val, new HashSet<Integer>());   // if not contain create a HashSet
        map.get(val).add(list.size());                          // careful need to add list.size() not list.size()-1
        list.add(val);
        return !isContain;                              // careful need to add !
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int loc = map.get(val).iterator().next();           // get first index for val
        if (loc < list.size() - 1) {
            int last = list.get(list.size()-1);             // get last value
            list.set(loc, last);                            // exchange value 
            map.get(last).remove(list.size()-1);            // remove one index from last's hashset
            map.get(last).add(loc);                         // add loc to last index hashset
        }
        map.get(val).remove(loc);
        list.remove(list.size()-1);
        if (map.get(val).isEmpty()) map.remove(val);        // careful need to check if val's index is empty. remove it if so
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */