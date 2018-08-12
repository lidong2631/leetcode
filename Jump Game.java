Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.



Python:
class Solution:
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        reach, i = 0, 0
        while i < len(nums) and i <= reach:
            reach = max(reach, i + nums[i])
            i += 1
        if reach < len(nums) - 1:
            return False
        return True




Java:
public class Solution {
    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, i + nums[i]);
        }
        if (reach < nums.length - 1) return false;
        return true;
    }
}






from code ganker:

这道题是动态规划的题目，所用到的方法跟是在Maximum Subarray中介绍的套路，用“局部最优和全局最优解法”。我们维护一个到目前为止能跳到的最远距离，以及从当前一步出发能跳到的最远距离。

局部最优local=A[i]+i，而全局最优则是global=Math.max(global, local)。递推式出来了，代码就比较容易实现了。因为只需要一次遍历时间复杂度是O(n)，而空间上是O(1)。代码如下： 

public boolean canJump(int[] A) {
    if(A==null || A.length==0)
        return false;
    int reach = 0;
    for(int i=0;i<=reach&&i<A.length;i++)
    {
        reach = Math.max(A[i]+i,reach);
    }
    if(reach<A.length-1)
        return false;
    return true;
}

这也是一道比较经典的动态规划的题目，不过不同的切入点可能会得到不同复杂度的算法，比如如果维护的历史信息是某一步是否能够到达，

那么每一次需要维护当前变量的时候就需要遍历前面的所有元素，那么总的时间复杂度就会是O(n^2)。所以同样是动态规划，有时候也会有不同的角度，不同效率的解法。

这道题目还有一个扩展Jump Game II，有兴趣的朋友可以看看。


