public class HitCounter {

    Deque<Integer> deque;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        deque = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        deque.addLast(timestamp);
        if (deque.getLast() - deque.getFirst() >= 300)
            deque.removeFirst();
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!deque.isEmpty() && timestamp - deque.getFirst() >= 300)
            deque.removeFirst();
        return deque.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

hit: O(1)
getHits: worst O(n)