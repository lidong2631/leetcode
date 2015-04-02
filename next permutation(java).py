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
    public void nextPermutation(int[] num) {
        if(num==null || num.length<2)
            return;
        int i=num.length-2;
        while(i>=0 && num[i]>=num[i+1])
            i--;
        if(i<0) {
            reverse(num, 0);
            return;
        }
        int mark = i;
        i=num.length-1;
        while(num[i]<=num[mark])
            i--;
        int tmp = num[mark];
        num[mark] = num[i];
        num[i] = tmp;
        reverse(num, mark+1);
    }
    
    private void reverse(int[] num, int left) {
        int right = num.length-1;
        while(left<right) {
            int tmp = num[left];
            num[left] = num[right];
            num[right] = tmp;
            left++;
            right--;
        }
    }
}





public class Solution {
    public void nextPermutation(int[] num) {
        if(num==null || num.length<2)   //注意length<2就不用做了
            return;
        int i = num.length-2;
        while(i>=0 && num[i]>=num[i+1]) {   //这里注意等于也要往前走 因为要找的是小于后面的数然后交换 等于交换没意义 5 2 4 2 2 2 1
            i--;
        }
        if(i<0) {
            reverse(num, 0);
            return;     //这里reverse完直接返回就可以了 不用再继续
        }
        int index = i;  //否则记录这个位置 i++跳到下一个位置开始
        i++;
        while(i<num.length && num[i]>num[index])    //这里不可以有等于因为一旦等于就可以交换它前面的数了 5 2 4 2 2 2 1
            i++;
        i--;        //找到下一个就比num[index]小的数
        int tmp = num[index];   //交换这两个数并reverse index后面所有的数
        num[index] = num[i];
        num[i] = tmp;
        reverse(num, index+1);
    }
    
    private void reverse(int[] num, int start) {
        int end = num.length-1;
        while(start<end) {
            int tmp = num[start];
            num[start] = num[end];
            num[end] = tmp;
            start++;
            end--;
        }
    }
}

这题要注意实现细节 38 41 46 50



public class Solution {
    public void nextPermutation(int[] num) {
        if(num==null || num.length==0)
            return;
        int i = num.length-2;
        while(i>=0 && num[i]>=num[i+1])     //这里可以有等于号
            i--;
        if(i>=0)            //如果i小于0 说明这就是最大的排列 直接reverse就好了
        {
            int j = i+1;
            while(j<num.length && num[j]>num[i])        //这里要注意不可以有等于号 参考例子[1,5,1] 如果等于会得到[1,1,5] 正确答案是[5,1,1]
                j++;
            j--;
            int tmp = num[j];
            num[j] = num[i];
            num[i] = tmp;
        }
        reverse(num, i+1);
    }
    
    private void reverse(int[] num, int index) {
        int left = index;
        int right = num.length-1;
        while(left<right)
        {
            int tmp = num[left];
            num[left] = num[right];
            num[right] = tmp;
            left++;
            right--;
        }
    }
}

Note: reverse函数的实现要牢记 其他根据那4不就好了 具体看图片




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
