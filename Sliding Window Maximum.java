https://leetcode.com/discuss/46578/java-o-n-solution-using-deque-with-explanation


public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null || nums.length==0 || k==0)
            return new int[0];
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2-i1;
            }    
        });
        int i = 0;
        int[] res = new int[nums.length-k+1];
        while(i<k) {
            heap.offer(nums[i]);
            i++;
        }
        int index = 0;
        res[0] = heap.peek();
        while(i<nums.length) {
            heap.remove(nums[i-k]);
            heap.offer(nums[i++]);
            res[++index] = heap.peek();
        }
        return res;
    }
}

O(nlogk)

https://leetcode.com/discuss/46739/java-solution-with-priorityqueue