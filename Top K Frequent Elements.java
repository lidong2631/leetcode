public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
                return m1.getValue() - m2.getValue();
            }
        });
        for (int n : nums) {
            int count = map.getOrDefault(n, 0);     // getOrDefault
            map.put(n, count+1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) heap.poll();
        }
        List<Integer> res = new LinkedList<>();
        while (!heap.isEmpty()) {
            res.add(0, heap.poll().getKey());
        }
        return res;
    }
}

use a minheap to save top k frequent elements

O(N + (N-k)lgk + klgk) = O(N + Nlgk)  = O(nlogk)

https://leetcode.com/discuss/100704/java-straightforward-o-n-n-k-lg-k-solution





public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
    
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
    
        for (int key : map.keySet()) {
            int count = map.get(key);
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(key);
        }
    
        List<Integer> res = new ArrayList<>();
    
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
}

bucketsort

https://leetcode.com/discuss/100581/java-o-n-solution-bucket-sort