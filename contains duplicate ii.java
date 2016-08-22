public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int i = 0, j = 0;
        Set<Integer> set = new HashSet<>();
        while (i < nums.length) {
            if (set.contains(nums[i])) return true;
            else set.add(nums[i]);
            i++;
            if (i - j > k) set.remove(nums[j++]);
        }
        return false;
    }
}

以k为区间移动检查重复 思路类似于remove nth node from end of list的套路

O(n) O(n)