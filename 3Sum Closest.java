class Solution:
    # @return an integer
    def threeSumClosest(self, num, target):
        num.sort()                  #先将序列排序
        minDiff = 10000; res = 0    #minDiff存放最小的差值 res存放最终结果sum
        for i in range(len(num)):       #循环每一个数字
            left = i + 1; right = len(num) - 1  #第一个数字已确定 第二个数字从第一个数右边开始 第三个数从最后一个数开始
            while left < right:                     #内循环 只要第二个数没和第三个数碰到
                sum = num[i] + num[left] + num[right]   #计算三个数和和target的差值
                diff = abs(sum - target)
                if diff < minDiff:      #如果新的差值比minDiff小 更新minDiff和res值
                    minDiff = diff
                    res = sum
                if sum == target:   #如果sum直接等于target 则差值为0 不需要再继续找下去已经得到答案 返回res
                    return res
                elif sum < target:  #否则 如果sum小于target 移动第二个数让它大一点继续计算 sum大于target的话移动第三个数让它取小一点
                    left+=1
                else:
                    right-=1
        return res






题意：数组中每三个元素进行求和，找出所有和中大小最接近target的和，并返回这个和与target之间的差值。

解题思路：使用一个变量mindiff来监测和与target之间的差值，如果差值为0，直接返回sum值。

代码：

复制代码
class Solution:
    # @return an integer
    def threeSumClosest(self, num, target):
        num.sort()
        mindiff=100000
        res=0
        for i in range(len(num)):
            left=i+1; right=len(num)-1
            while left<right:
                sum=num[i]+num[left]+num[right]
                diff=abs(sum-target)
                if diff<mindiff: mindiff=diff; res=sum
                if sum==target: return sum
                elif sum<target: left+=1
                else: right-=1
        return res



public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return -1;
        int minDiff = nums[0] + nums[1] + nums[2] - target;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i+1]) continue;
            int currDiff = twoSumDiff(nums, i-1, target-nums[i]);
            if (currDiff == 0) return target;       // careful if 0 then we are done
            if (Math.abs(currDiff) < Math.abs(minDiff)) minDiff = currDiff;
        }
        return target + minDiff;
    }
    
    private int twoSumDiff(int[] nums, int index, int target) {
        int minDiff = nums[index] + nums[index-1] - target;
        int left = 0, right = index;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return 0;
            }
            int diff = nums[left] + nums[right] - target;
            if (Math.abs(diff) < Math.abs(minDiff)) minDiff = diff;
            if (diff > 0) right--;
            else left++;
        }
        return minDiff;
    }
}

Note: 根据code ganker改编 这题跟3sum区别不大 注意要取绝对值






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



