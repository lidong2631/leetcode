Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int len = tasks.length, res = 0;
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(len, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> m1, Map.Entry<Character, Integer> m2) {
                return m2.getValue() - m1.getValue();
            }
        });
        for (char task : tasks) {
            if (map.containsKey(task))
                map.put(task, map.get(task)+1);
            else map.put(task, 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            heap.offer(entry);
        while (!heap.isEmpty()) {
            int size = heap.size();
            Map<Character, Integer> cache = new HashMap<>();
            for (int i = 0; i < n + 1; i++) {
                if (!heap.isEmpty()) {
                    Map.Entry<Character, Integer> entry = heap.poll();
                    int val = entry.getValue();
                    if (--val > 0) cache.put(entry.getKey(), val);
                    len--;
                }
                res++;
            }
            for (Map.Entry<Character, Integer> entry : cache.entrySet())
                heap.offer(entry);
            if (heap.isEmpty())         // careful for the last time we need to remove extra idle
                res -= (n + 1 - size);
        }
        return res;
    }
}

same as Rearrange String k Distance Apart