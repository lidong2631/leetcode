public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length-1, k);
    }

    private int helper(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        
        while (true) {
            int n = partition(nums, left, right);
            int rank = right - n + 1;                               // notice here !
            if (rank == k)
                return nums[n];
            else if (rank < k)
                return helper(nums, left, n-1, k-rank);
            else
                return helper(nums, n+1, right, k);
        }
    }
    
    private int partition(int[] nums, int left, int right) {        // quick sort partition
        int pivot = nums[right];
        int l = left - 1, r = right;
        while (true) {
            while (nums[++l] < pivot)
                ;
            while (r > 0 && nums[--r] > pivot)                      //
                ;
            if (l >= r)
                break;
            swap(nums, l, r);
        }
        swap(nums, l, right);
        return l;
    }
    
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}

quick select. Order Statistics 利用quicksort中的selection algorithm 其实就是partition那步

O(n) worst case complexity is O(n^2)

这种做法的进一步优化就是median of medians 有空可以看下 worst performance仍是O(n)

http://www.ardendertat.com/2011/10/27/programming-interview-questions-10-kth-largest-element-in-array/

http://www.quora.com/What-is-the-most-efficient-algorithm-to-find-the-kth-smallest-element-in-an-array-having-n-elements

http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/


这题也可以扩展成find kth smallest number
http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/




public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        int i = 0;
        while(i<k) {
            heap.offer(nums[i]);
            i++;
        }
        while(i<nums.length) {
            if(nums[i]>heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
            i++;
        }
        return heap.peek();
    }
}

堆排序 参考geeksforgeeks方法6 min heap

O(k + (n-k)Logk) without sorted output. If sorted output is needed then O(k + (n-k)Logk + kLogk)
http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/


