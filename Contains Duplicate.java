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
http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/