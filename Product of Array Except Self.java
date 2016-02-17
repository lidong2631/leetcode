public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i=1; i<nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }
        int right = 1;
        for(int i=nums.length-1; i>=0; i--) {
            res[i]*=right;
            right*=nums[i];
        }
        return res;
    }
}

左扫一遍记录每一个数前面的乘积 又扫一遍 记录每一个数后面的乘积相乘

O(n) O(1)

https://leetcode.com/discuss/46104/simple-java-solution-in-o-n-without-extra-space
