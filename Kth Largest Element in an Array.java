public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length-1, k);
    }
    
    private int helper(int[] nums, int left, int right, int k) {
        if(left==right)
            return nums[left];
        while(true) {
            int pivotIndex = right;             //这里pivotIndex我取最右边的值
            pivotIndex = partition(nums, left, right, pivotIndex);  
            int rank = right-pivotIndex+1;      //跟原文不一样 每次按pivot分组 分成小于pivot和大于pivot
            if(rank==k)                         //如果k落在pivotIndex上则返回结果 否则如果k大就递归去pivotIndex右边找 k小去pivotIndex左边找
                return nums[pivotIndex];
            else if(k>rank)
                return helper(nums, left, pivotIndex-1, k-rank);
            else
                return helper(nums, pivotIndex+1, right, k);
        }
    }
    
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int index = left;
        int pivot = nums[pivotIndex];   //pivot是数组最右边元素的值
        for(int i=left; i<right; i++) {
            if(nums[i]<pivot) {     //从左扫到右 如果nums[i]小于pivot就跟index指的元素交换 且index右移一位
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                index++;
            }
        }
        int tmp = nums[right];      //最后将最右边的pivot交换到对应位置index 返回index
        nums[right] = nums[index];
        nums[index] = tmp;
        return index;
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


