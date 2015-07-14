public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums==null || nums.length==0)
            return false;
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0; i<nums.length; i++) {
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }
}

O(n) O(n)
简单题 用hashset记录重复

别的解法包括
1 每次取一个字符跟后面的字符比较看是否有重复 O(n^2) O(1)

2 先排序 再检查相邻元素是否有重复 O(nlogn) O(1)

这个题的扩展可以看一下

http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
You are given an array of n+2 elements. All elements of the array are in range 1 to n. And all elements occur 
once except two numbers which occur twice. Find the two repeating numbers.


http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
Given an array of n elements which contains elements from 0 to n-1, with any of these numbers appearing 
any number of times. Find these repeating numbers in O(n) and using only constant memory space.