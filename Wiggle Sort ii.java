public class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        int n = nums.length;
        int mid = n%2==0?n/2-1:n/2;
        int index = 0;
        
        for(int i=0; i<=mid; i++) {
            res[index] = nums[mid-i];
            if(index+1<n)
                res[index+1] = nums[n-1-i];
            index+=2;
        }
        for(int i=0; i<n; i++)
            nums[i] = res[i];
    }
}

https://discuss.leetcode.com/topic/41464/step-by-step-explanation-of-index-mapping-in-java
https://discuss.leetcode.com/topic/32920/o-n-time-o-1-space-solution-with-detail-explanations

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?



sort and then spread the numbers like in this example with nums=[0,1,...,9]

Small half:    4 . 3 . 2 . 1 . 0 .
Large half:    . 9 . 8 . 7 . 6 . 5
----------------------------------
Together:      4 9 3 8 2 7 1 6 0 5

https://leetcode.com/discuss/77122/simple-modulo-solution
https://leetcode.com/discuss/77496/ac-java-solution-7ms