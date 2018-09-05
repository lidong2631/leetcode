Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;




public class Logger {

    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message) && timestamp - map.get(message) <= 9)
            return false;
        else {
            map.put(message, timestamp);
            return true;
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */


The problem with this approach is that your map size will keep growing. 
It will have messages that have come since the beginning even though we need to keep only the words that have come only 10 seconds before the current timestamp.

we can have another thread running cron job to evict timeout entries from the hash map. It would be more efficient and solve the problem of exploded hash map

We can use LinkedHashMap to remove old entry and use map as a cache.
https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
Sample use: this override will allow the map to grow up to 100 entries and then delete the eldest entry each time a new entry is added, maintaining a steady state of 100 entries.

     private static final int MAX_ENTRIES = 100;

     protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
     }



public class Logger {

    public Map<String, Integer> map;
    int lastSecond = 0;
    
    /** Initialize your data structure here. */
    public Logger() {
        map = new java.util.LinkedHashMap<String, Integer>(100, 0.6f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return lastSecond - eldest.getValue() > 10;
            }
        };
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        lastSecond = timestamp;
        if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message,timestamp);
            return true;
        }
        return false;
    }
}

https://leetcode.com/problems/logger-rate-limiter/discuss/83254/Java-with-a-LinkedHashMap-and-using-removeEldestEntry



Native Approach:

public class Logger {
    Queue<Tuple> q = new ArrayDeque<>();
    Set<String> dict = new HashSet<>();
  
    public Logger() {}
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!q.isEmpty() && q.peek().t <= timestamp - 10) {
            Tuple next = q.poll();
            dict.remove(next.msg);
        }
        if (!dict.contains(message)) {
            q.offer(new Tuple(timestamp, message));
            dict.add(message);
            return true;
        }
        return false;
    }
    private static class Tuple {
        int t; 
        String msg;
        public Tuple(int t, String msg) {
            this.t = t;
            this.msg = msg;
        }
    }
}

https://leetcode.com/problems/logger-rate-limiter/discuss/83284/A-solution-that-only-keeps-part-of-the-messages


RateLimiter Algorithms
https://konghq.com/blog/how-to-design-a-scalable-rate-limiting-algorithm/

Guava RateLimiter
https://www.baeldung.com/guava-rate-limiter


Redis RateLimiter
https://redis.io/commands/INCR#pattern-rate-limiter