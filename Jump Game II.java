Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.




Python:
class Solution:
    def jump(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        reach, lastReach, step, i = 0, 0, 0, 0
        while i < len(nums) and i <= reach:
            if i > lastReach:
                lastReach = reach
                step += 1
            reach = max(reach, i + nums[i])
            i += 1
        if reach < len(nums) - 1:
            return -1
        return step




Java:
public class Solution {
    public int jump(int[] nums) {
        int reach = 0, lastReach = 0, step = 0;
        for (int i = 0; i < nums.length && i <= reach; i++) {
            if (i > lastReach) {                    // check is before update reach
                lastReach = reach;
                step++;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        if (nums.length - 1 > reach) return -1;     // careful
        return step;
    }
}

O(n)



from code ganker:

这道题是Jump Game的扩展，区别是这道题不仅要看能不能到达终点，而且要求到达终点的最少步数。其实思路和Jump Game还是类似的，

只是原来的全局最优现在要分成step步最优和step-1步最优（假设当前步数是step）。当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，我们就可以更新步数，

将step+1。时间复杂度仍然是O(n)，空间复杂度也是O(1)。代码如下：

public int jump(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int lastReach = 0;
    int reach = 0;
    int step = 0;
    for(int i=0;i<=reach&&i<A.length;i++)
    {
        if(i>lastReach)
        {
            step++;
            lastReach = reach;
        }
        reach = Math.max(reach,A[i]+i);
    }
    if(reach<A.length-1)
        return 0;
    return step;
}

动态规划是面试中特别是onsite中非常重要的类型，一般面试中模型不会过于复杂，所以大家可以熟悉一下比较经典的几个题，比如Jump Game，Maximum Subarray等。