Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4




Java:
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length-1, k);
    }
    
    private int helper(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        
        while (true) {
            int n = partition(nums, left, right);
            int rank = right - n + 1;
            if (k == rank)
                return nums[n];
            else if (k < rank)
                return helper(nums, n+1, right, k);
            else
                return helper(nums, left, n-1, k-rank);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right], l = left - 1, r = right;
        while (true) {
            while (nums[++l] < pivot)
                ;
            while (r > 0 && nums[--r] > pivot)
                ;
            if (l >= r)
                break;
            swap(nums, l, r);
        }
        swap(nums, l, right);
        return l;
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}

quick select. Order Statistics 利用quicksort中的selection algorithm 其实就是partition那步

O(n) worst case complexity is O(n^2)
n + n/2 + n/4 + n/8 + ... = n (1 + 1/2 + 1/4 + 1/8 + ...) = n * 1 * (1 - (1/2)^n) / (1 - 1/2) = 2n

explanation on the time complexity
http://stackoverflow.com/questions/8783408/why-is-the-runtime-of-the-selection-algorithm-on


Geometric Progression
sum = a1 * (1 - q^n) / (1 - q)
Arithmetic Sequence
sum = (a1 + an) * n / 2  or  n * a1 + n * (n - 1) * d / 2



这种做法的进一步优化就是median of medians 有空可以看下 worst performance仍是O(n)

http://www.ardendertat.com/2011/10/27/programming-interview-questions-10-kth-largest-element-in-array/

http://www.quora.com/What-is-the-most-efficient-algorithm-to-find-the-kth-smallest-element-in-an-array-having-n-elements

http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/


这题也可以扩展成find kth smallest number
http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/




public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        int i = 0;
        for (; i < k; i++)
            heap.offer(nums[i]);
        for (; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap.peek();
    }
}

堆排序 参考geeksforgeeks方法6 min heap

O(k + (n-k)Logk) without sorted output. If sorted output is needed then O(k + (n-k)Logk + kLogk)
http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/


