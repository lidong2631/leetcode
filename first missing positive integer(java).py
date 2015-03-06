class Solution:
    # @param A, a list of integers
    # @return an integer
    def firstMissingPositive(self, A):
        if A == None or A == []:
            return 1
        i = 0
        while i < len(A):                               #这里注意要用while 用for循环在python里i无法被更改 i-=1将不起作用 for i in range(len(A)) i每次加1不受减1影响
            if 0 < A[i] <= len(A) and A[A[i]-1]!=A[i]:  #如果A[i]值在0 到len(A)之间 且A[A[i]-1]值不等于A[i] 交换A[A[i]-1]和A[i]
                A[A[i]-1], A[i] = A[i], A[A[i]-1]
                i-=1                #做完交换 i-=1 下次循环还是检查这个索引看它的满不满足A[i] = i + 1 直到它满足条件或是负数，0，超过数组长度的数才跳到下一个数判断
            i+=1
        for i in range(len(A)):     #最后遍历一遍A 不符合A[i] = i + 1 条件的即是first missing positive integer
            if A[i]!=i + 1:
                return i + 1
        return len(A) + 1           #否则如果都满足条件 如[1,2,3] 返回len(A)+1

Note: 这题要注意 python for循环i没法改变的特殊性 还要看看counting sort的算法




题意：

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

解题思路：要求时间复杂度为O(n)和常数空间。又是一个tricky的解法。


class Solution:
    # @param A, a list of integers
    # @return an integer
    # @a very subtle solution
    def firstMissingPositive(self, A):
        n=len(A)
        for i in range(n):
            if A[i]<=0: A[i]=n+2
        for i in range(n):
            if abs(A[i])<=n:
                curr=abs(A[i])-1
                A[curr]=-abs(A[curr])
        for i in range(n):
            if A[i]>0: return i+1
        return n+1





public class Solution {
    public int firstMissingPositive(int[] A) {
        if(A==null || A.length==0)
            return 1;
        for(int i=0; i<A.length; i++)
        {
            if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i]) //排除超出数组长度的数 负数 如果A[A[i]-1]!=A[i] 交换它和A[i]的值 把A[i]的值放到A[A[i]-1]里
            {
                int tmp = A[A[i]-1];
                A[A[i]-1] = A[i];   //每次将不符合要求的值交换到它应该放到的数组元素中
                A[i] = tmp;
                i--;            //记得这里要i--， 停留在原地继续判断交换过来的值是否符合要求
            }
        }
        for(int i=0; i<A.length; i++)       //最后再遍历一次数组 第一个不符合A[i]=i+1的即是所求
        {
            if(A[i]!=i+1)
                return i+1;
        }
        return A.length+1;
    }
}

Note： from code ganker 这题有点意思 借鉴了计数排序的思路 用数组的index作为数字本身的索引 要好好想明白

对于本题 默认数字从最小正整数1开始

注意 这题要看code ganker评论 首先62行那里的A[A[i]-1]!=A[i]那里不可以替换成A[i]-1!=i 因为如果有重复元素会出现死循环

第二64到66行不可以写成如下:
int tmp = A[i];
A[i] = A[A[i]-1];
A[A[i]-1] = tmp;
因为如果先更改A[i]后面A[A[i]-1]里的A[i]就会受影响被改变 所以这里只能先更改A[A[i]-1]的值

另外这题扩展可以想下如果不从1开始的正整数怎么做 看code ganker评论 大致就是扫一遍数组找出最大最小值 然后减去偏差即可








from code ganker:

这道题要求用线性时间和常量空间，思想借鉴到了Counting sort中的方法，不了解的朋友可以参见Counting sort - Wikipedia。

既然不能用额外空间，那就只有利用数组本身，跟Counting sort一样，利用数组的index来作为数字本身的索引，把正数按照递增顺序依次放到数组中。

即让A[0]=1, A[1]=2, A[2]=3, ... , 这样一来，最后如果哪个数组元素违反了A[i]=i+1即说明i+1就是我们要求的第一个缺失的正数。

对于那些不在范围内的数字，我们可以直接跳过，比如说负数，0，或者超过数组长度的正数，这些都不会是我们的答案。代码如下：

public int firstMissingPositive(int[] A) {
    if(A==null || A.length==0)
    {
        return 1;
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i])
        {
            int temp = A[A[i]-1];
            A[A[i]-1] = A[i];
            A[i] = temp;
            i--;
        }
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]!=i+1)
            return i+1;
    }
    return A.length+1;
}

实现中还需要注意一个细节，就是如果当前的数字所对应的下标已经是对应数字了，那么我们也需要跳过，因为那个位置的数字已经满足要求了，

否则会出现一直来回交换的死循环。这样一来我们只需要扫描数组两遍，时间复杂度是O(2*n)=O(n)，而且利用数组本身空间，只需要一个额外变量，

所以空间复杂度是O(1)。

这道题个人还是比较喜欢的，既有一点算法思想，在实现中又有一些注意细节，而且整体来说模型比较简单，很适合在面试中出现。
















计数排序是一种算法复杂度 O(n) 的排序方法，适合于小范围集合的排序。比如100万学生参加高考，我们想对这100万学生的数学成绩（假设分数为0到100）做个排序。

我们如何设计一个最高效的排序算法。本文不光给出计数排序算法的传统写法，还将一步步深入讨论算法的优化，直到时间复杂度和空间复杂度最优。

先看看计数排序的定义

Counting sort (sometimes referred to as ultra sort or math sort[1]) is a sorting algorithm which (like bucket sort) 

takes advantage of knowing the range of the numbers in the array to be sorted (array A). It uses this range to create 

an array C of this length. Each index i in array C is then used to count how many elements in A have the value i; 

then counts stored in C can then be used to put the elements in A into their right position in the resulting sorted array.

The algorithm was created by Harold H. Seward in 1954.

计数排序是一个类似于桶排序的排序算法，其优势是对已知数量范围的数组进行排序。它创建一个长度为这个数据范围的数组C，

C中每个元素记录要排序数组中对应记录的出现个数。这个算法于1954年由 Harold H. Seward 提出。

下面以示例来说明这个算法

假设要排序的数组为 A = {1,0,3,1,0,1,1}

这里最大值为3，最小值为0，那么我们创建一个数组C，长度为4.

然后一趟扫描数组A，得到A中各个元素的总数，并保持到数组C的对应单元中。

比如0 的出现次数为2次，则 C[0] = 2;1 的出现次数为4次，则C[1] = 4

 

 

由于C 是以A的元素为下标的，所以这样一做，A中的元素在C中自然就成为有序的了，这里我们可以知道 顺序为 0,1,3 (2 的计数为0)

然后我们把这个在C中的记录按每个元素的计数展开到输出数组B中，排序就完成了。

也就是 B[0] 到 B[1] 为0  B[2] 到 B[5] 为1 这样依此类推。

这种排序算法，依靠一个辅助数组来实现，不基于比较，算法复杂度为 O(n) ，但由于要一个辅助数组C，所以空间复杂度要大一些，由于计算机的内存有限，

这种算法不适合范围很大的数的排序。

注：基于比较的排序算法的最佳平均时间复杂度为 O(nlogn)

 

Counting sort
Depends on a key assumption: numbers to be sorted are integers in{0, 1, . . . , k}.
Input: A[1 . . n], where A[ j ] ∈ {0, 1, . . . , k} for j = 1, 2, . . . , n. Array A and
values n and k are given as parameters.
Output: B[1 . . n], sorted. B is assumed to be already allocated and is given as a
parameter.
Auxiliary storage: C[0 . . k]
8-4 Lecture Notes for Chapter 8: Sorting in Linear Time
COUNTING-SORT(A, B, n, k)
for i ← 0 to k
do C[i ] ← 0
for j ← 1 to n
do C[A[ j ]] ← C[A[ j ]] + 1
for i ← 1 to k
do C[i ] ← C[i ] + C[i − 1]
for j ← n downto 1
do B[C[A[ j ]]] ← A[ j ]
C[A[ j ]] ← C[A[ j ]] − 1
Do an example for A = 21, 51, 31, 01, 22, 32, 02, 33
Counting sort is stable (keys with same value appear in same order in output as
they did in input) because of how the last loop works.


上面这段引自麻省理工大学计算机算法教材的技术排序部分，我不做翻译了。这个就是这个算法的典型解法，我把它作为方案1.

这个算法的实际扫描次数为 n+k （不包括写的次数）
