Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).



Python:
class Solution:
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        nums.sort()
        result = nums[0] + nums[1] + nums[2]
        for i in range(len(nums) - 2):
            j, k = i+1, len(nums) - 1
            while j < k:
                sum = nums[i] + nums[j] + nums[k]
                if sum == target:
                    return sum
                
                if abs(sum - target) < abs(result - target):
                    result = sum
                
                if sum < target:
                    j += 1
                elif sum > target:
                    k -= 1
            
        return result




class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i+1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target)
                    return sum;
                if (Math.abs(sum-target) < Math.abs(res-target))
                    res = sum;
                if (sum < target)
                    l++;
                else r--;
            }
        }
        return res;
    }
}

O(n^2)




Golang:
func threeSumClosest(nums []int, target int) int {
    sort.Ints(nums)
    res := nums[0] + nums[1] + nums[2]
    for i := 0; i < len(nums) - 2; i++ {
        l, r := i+1, len(nums) - 1
        for l < r {
            sum := nums[l] + nums[r] + nums[i]
            if sum == target {
                return sum
            }
            if Abs(sum - target) < Abs(res - target) {
                res = sum
            }
            if sum < target {
                l++
            } else {
                r--
            }
        }
    }
    return res
}

func Abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}







from code ganker:

这道题跟3Sum很类似，区别就是要维护一个最小的diff，求出和目标最近的三个和。brute force时间复杂度为O(n^3)，优化的解法是使用排序之后夹逼的方法，

总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n),代码如下：

public int threeSumClosest(int[] num, int target) {
    if(num == null || num.length<=2)
        return Integer.MIN_VALUE;
    Arrays.sort(num);
    int closest = num[0]+num[1]+num[2]-target;    
    for(int i=0;i<num.length-2;i++)
    {
        int cur = twoSum(num,target-num[i],i+1);
        if(Math.abs(cur)<Math.abs(closest))
            closest = cur; 
    }
    return target+closest;
}
private int twoSum(int[] num, int target, int start)
{
    int closest = num[start]+num[start+1]-target;
    int l = start;
    int r = num.length-1;
    while(l<r)
    {
        if(num[l]+num[r]==target)
            return 0;
        int diff = num[l]+num[r]-target;
        if(Math.abs(diff)<Math.abs(closest))
            closest = diff;    
        if(num[l]+num[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return closest;
}

这道题具体的考察点可以参见3Sum，稍微变体一下，其实区别不大。此题更加复杂的扩展是4Sum，请参见4Sum -- LeetCode.



