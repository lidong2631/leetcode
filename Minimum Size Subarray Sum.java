public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0, minLen = nums.length + 1, sum = 0;;
        boolean hasMinLen = false;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= s) {
                hasMinLen = true;
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return hasMinLen ? minLen : 0;
    }
}

典型two pointers思路




From leetcode discuss
Since the given array contains only positive integers, the subarray sum can only increase by including more elements. 

Therefore, you dont have to include more elements once the current subarray already has a sum large enough. 

This gives the linear time complexity solution by maintaining a minimum window with a two indices.

As to NLogN solution, logN immediately reminds you of binary search. In this case, 

you cannot sort as the current order actually matters. How does one get an ordered array then? 

Since all elements are positive, the cumulative sum must be strictly increasing. Then, 

a subarray sum can expressed as the difference between two cumulative sum. Hence, 

given a start index for the cumulative sum array, the other end index can be searched using binary search.

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        int[] sums = new int[nums.length+1];
        for(int i=1; i<sums.length; i++)
            sums[i] = sums[i-1] + nums[i-1];
        int minLen = nums.length+1;
        for(int i=0; i<sums.length; i++) {
            int end = BSearch(i+1, sums.length-1, sums[i]+s, sums);
            if(end==sums.length)
                break;
            minLen = Math.min(minLen, end-i);
        }
        return minLen==nums.length+1?0:minLen;
    }
    
    private int BSearch(int l, int r, int val, int[] sums) {
        while(l<=r) {
            int mid = (l+r)/2;
            if(sums[mid]>=val)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }
}

我一开始的想法是将数组排序然后从后往前逐个累加 一旦大于等于s 即返回结果 但是这样忽视了subarray的条件 这道题不可以排序

这种解法倒是可以用在找非subarray的最小长度上






