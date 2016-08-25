public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        int i = 0;
        while (i < k) {
            heap.offer(nums[i++]);
        }
        int[] res = new int[nums.length - k + 1];
        res[0] = heap.peek();
        int j = 0;
        while (i < nums.length) {
            heap.remove(nums[j++]);
            heap.offer(nums[i++]);
            res[j] = heap.peek();
        }
        return res;
    }
}




https://leetcode.com/discuss/46578/java-o-n-solution-using-deque-with-explanation


public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];    // careful here for special case
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        while (i < k) heap.offer(nums[i++]);
        int[] res = new int[nums.length - k + 1];   // careful it is nums.length - k + 1
        res[0] = heap.peek();           // careful here need to set res[0] first
        while (i < nums.length) {
            heap.remove(nums[i-k]);
            heap.offer(nums[i++]);
            res[i-k] = heap.peek();
        }
        return res;
    }
}

O(nlogk)

https://leetcode.com/discuss/46739/java-solution-with-priorityqueue