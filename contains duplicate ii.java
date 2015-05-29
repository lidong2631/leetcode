public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int i=0, j=0;
        while(j-i<=k && j<nums.length) {    //先前进k步
            if(set.contains(nums[j]))
                return true;
            else
                set.add(nums[j]);
            j++;
        }
        while(j<nums.length) {      //然后以长度为k的区间移动
            set.remove(nums[i++]);
            if(set.contains(nums[j]))
                return true;
            else
                set.add(nums[j]);
            j++;
        }
        return false;
    }
}

以k为区间移动检查重复 思路类似于remove nth node from end of list的套路

O(n) O(n)