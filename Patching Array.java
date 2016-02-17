public class Solution {
    public int minPatches(int[] nums, int n) {
        long missed = 1;
        int res = 0, i = 0;
        while(missed<=n) {
            if(i<nums.length && nums[i]<=missed)
                missed+=nums[i++];
            else {
                missed+=missed;
                res+=1;
            }
        }
        return res;
    }
}

https://leetcode.com/discuss/82822/solution-explanation

https://leetcode.com/discuss/83272/share-my-thinking-process