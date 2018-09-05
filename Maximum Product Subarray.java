Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.



Java:
public class Solution {
    public int maxProduct(int[] nums) {
        int minLocal = nums[0], maxLocal = nums[0], global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int maxCopy = maxLocal;
            maxLocal = Math.max(nums[i], Math.max(nums[i]*maxLocal, nums[i]*minLocal));
            minLocal = Math.min(nums[i], Math.min(nums[i]*maxCopy, nums[i]*minLocal));
            global = Math.max(global, maxLocal);
        }
        return global;
    }
}


这道题跟Maximum Subarray模型上和思路上都比较类似，还是用一维动态规划中的“局部最优和全局最优法”。

这里的区别是维护一个局部最优不足以求得后面的全局最优，这是由于乘法的性质不像加法那样，累加结果只要是正的一定是递增，

乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积。不过事实上也没有麻烦很多，

我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和，

这也是利用乘法的性质得到的。代码如下： 

public int maxProduct(int[] A) {
    if(A==null || A.length==0)
        return 0;
    if(A.length == 1)
        return A[0];
    int max_local = A[0];
    int min_local = A[0];
    int global = A[0];
    for(int i=1;i<A.length;i++)
    {
        int max_copy = max_local;
        max_local = Math.max(Math.max(A[i]*max_local, A[i]), A[i]*min_local);
        min_local = Math.min(Math.min(A[i]*max_copy, A[i]), A[i]*min_local);
        global = Math.max(global, max_local);
    }
    return global;
}

这道题是一道很不错的面试题目，因为Maximum Subarray这道题过于常见了，所以可能大部分人都做过，这道题模型类似，

但是又有一些新的考点，而且总体还是比较简单，无论是思路上还是实现上，又能考察动态规划