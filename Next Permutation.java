class Solution:
    # @param num, a list of integer
    # @return a list of integer
    def nextPermutation(self, num):
        if len(num) <= 1:       #如果长度小于等于1 返回num
            return num
        partition = -1
        for i in range(len(num)-2, -1, -1):     #从后往前遍历数组 如果找到第一对升序对 将partition标记为第一个违反升序的值的索引
            if num[i] < num[i+1]:
                partition = i
                break;
        if partition == -1:     #如果partition等于－1 说明全部降序排列 将num反转为最小排列
            num.reverse()
            return num
        for i in range(len(num)-1, partition, -1):      #否则从后往前遍历 找到第一个比partition标记的值大的值 交换它和partition的值
            if num[partition] < num[i]:
                num[partition], num[i] = num[i], num[partition]
                break
        num[partition+1:len(num)] = num[partition+1:len(num)][::-1]     #将partition后面的序列逆序即可
        return num

Note: python reverse()没有返回值 不可以用这种形式 return num.reverse() 只可以先num.reverse() 之后直接返回num

因为降序序列是没法变的更大的，所以从后往前找到第一个升序对的位置。

然后就存在调整大小排列顺序的可能，从后往前找到比第一个当前位置大的元素，交换之。

当前位置后面的元素还是降序排列，将他们反转得到最小顺序排列。其实就是原来当前位置元素后面是最大的排列，而交换后的新元素之后是最小的排列，他们就是相邻的顺序。

当不存在升序，则当前排列是最大排列，只要旋转整个序列变成最小排列。




public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i-1] >= nums[i])   //from end find first non-increasing element
            i--;
        if (i == 0) {                           // if non reverse whole array
            reverse(nums, 0);
            return;
        }
        int j = i - 1;                          // remember first non-increasing position
        while (i < nums.length && nums[i] > nums[j])    // find smallest item that larger than it
            i++;
        swap(nums, i-1, j);                     // swap the two and reverse the rest
        reverse(nums, j+1);
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    private void reverse(int[] nums, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++; r--;
        }
    }
}




from code ganker:

这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，主要的问题是经常不是一见到这个题就能马上理清思路。下面我们用一个例子来说明，

比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：

1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;

2) 接下来分两种情况：

    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));

    (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数小的数字，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，

        比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。

以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。代码如下：

public void nextPermutation(int[] num) {
    if(num==null || num.length==0)
        return;
    int i = num.length-2;
    while(i>=0 && num[i]>=num[i+1])
    {
        i--;
    }
    if(i>=0)
    {
        int j=i+1;
        while(j<num.length && num[j]>num[i])
        {
            j++;
        }
        j--;
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    reverse(num, i+1);
}
private void reverse(int[] num, int index)
{
    int l = index;
    int r = num.length-1;
    while(l<r)
    {
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
        l++;
        r--;
    }
}
