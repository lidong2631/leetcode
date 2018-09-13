Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false




Java:
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < nums.length) {
            if (set.contains(nums[i])) 
                return true;
            else 
                set.add(nums[i++]);
            if (i - j > k) 
                set.remove(nums[j++]);
        }
        return false;
    }
}




public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        while (j < nums.length && j <= k) {     // careful k could be larger than nums.length
            if (set.contains(nums[j])) return true;
            set.add(nums[j++]);
        }
        while (j < nums.length) {
            set.remove(nums[i++]);
            if (set.contains(nums[j])) return true;
            set.add(nums[j++]);
        }
        return false;
    }
}

以k为区间移动检查重复 思路类似于remove nth node from end of list的套路

O(n) O(n)