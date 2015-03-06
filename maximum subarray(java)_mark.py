class Solution:
    # @param A, a list of integers
    # @return an integer
    def maxSubArray(self, A):
        maxSum = -1000          #初始化maxSum, sum
        sum = 0
        for i in range(len(A)):         #从头开始循环数组A
            if sum < 0:             #此算法精髓 如果当前的sum为负数 则去掉它剩下的序列完全可以更大 具体见下面分析
                sum = 0             #设sum=0 如果它是负数
            sum += A[i]         #sum加上当前的元素值为新sum
            maxSum = max(sum, maxSum)       #如果sum比全局变量maxSum大舅更新maxSum
        return maxSum

Note: 最大连续子序列和问题 很经典 要完全搞懂 暴力法, 线性算法, divide and conquer





题意：

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice: 
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

解题思路：最大子序列和问题，直接上代码，不是我能想出来的。
代码：

class Solution:
    # @param A, a list of integers
    # @return an integer
    def maxSubArray(self, A):
        ThisSum = 0
        MaxSum = -10000
        
        for i in range( 0, len(A) ):
            if ThisSum < 0:
                ThisSum = 0
            ThisSum = ThisSum + A[ i ]
            MaxSum = max( ThisSum, MaxSum )

        return MaxSum






public class Solution {
    public int maxSubArray(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int local = A[0];
        int global = A[0];
        for(int i=1; i<A.length; i++)
        {
            local = Math.max(A[i], local+A[i]);     //这两句程序关键
            global = Math.max(local, global);
        }
        return global;
    }
}

Note: 看code ganker讲解 解释蛮清楚


from code ganker:

这是一道非常经典的动态规划的题目，用到的思路我们在别的动态规划题目中也很常用，以后我们称为”局部最优和全局最优解法“。

基本思路是这样的，在每一步，我们维护两个变量，一个是全局最优，就是到当前元素为止最优的解是，一个是局部最优，就是必须包含当前元素的最优的解。

接下来说说动态规划的递推式（这是动态规划最重要的步骤，递归式出来了，基本上代码框架也就出来了）。假设我们已知第i步的global[i]（全局最优）和local[i]（局部最优），

那么第i+1步的表达式是：
local[i+1]=Math.max(A[i], local[i]+A[i])，就是局部最优是一定要包含当前元素，所以不然就是上一步的局部最优local[i]+当前元素A[i]

（因为local[i]一定包含第i个元素，所以不违反条件），但是如果local[i]是负的，那么加上他就不如不需要的，所以不然就是直接用A[i]；

global[i+1]=Math(local[i+1],global[i])，有了当前一步的局部最优，那么全局最优就是当前的局部最优或者还是原来的全局最优（所有情况都会被涵盖进来，

因为最优的解如果不包含当前元素，那么前面会被维护在全局最优里面，如果包含当前元素，那么就是这个局部最优）。

接下来我们分析一下复杂度，时间上只需要扫描一次数组，所以时间复杂度是O(n)。空间上我们可以看出表达式中只需要用到上一步local[i]和global[i]就可以得到下一步的结果，

所以我们在实现中可以用一个变量来迭代这个结果，不需要是一个数组，也就是如程序中实现的那样，所以空间复杂度是两个变量（local和global），即O(2)=O(1)。

代码如下： 

public int maxSubArray(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int global = A[0];
    int local = A[0];
    for(int i=1;i<A.length;i++)
    {
        local = Math.max(A[i],local+A[i]);
        global = Math.max(local,global);
    }
    return global;
}

这道题虽然比较简单，但是用到的动态规划方法非常的典型，我们在以后的题目中还会遇到，大家还是要深入理解一下哈。我现在记得的用到的题目是Jump Game，以后有统计一下再继续更新










问题描述

给定（可能是负的）整数序列A1, A2,...,AN, 寻找（并标识）使Sum(Ak)(k >=i, k <= j)的值最大的序列。如果所有的整数都是负的，那么连续子序列的最大和是零。

 

最简单暴力的解法
        
这个问题有一个最简单直接的穷举解决法。我们看问题，既然要求里面最大的连续子序列。那么所有的连续子序列将由哪些组成呢？以数组的第一个元素为例，连续子序列必须是至少包含元素A1,

也可能包含从A1到A2...以及从A1到AN。这样就有N种可能。后面的元素也按照这样类似的办法，以该元素开始，包含该元素的单元素数组，两个元素数组...直到包含数组末尾的数组。

基于上面的分析，我们可以采用一个两重的循环，假设两个循环的循环变量分别为i, j。第一层循环从第一个元素遍历到后面，第二个元素从>=第一个元素的位置开始到最后。

这样就可以遍历到所有的点。然后，我们取所有从i到j的连续数组部分和再比较。这样最终就可以得到最大的连续和以及最大子序列的起始与结束点。

具体的实现代码如下：

Java代码  
1.public static int maxSubSum1( int [ ] a )   
2.{   
3.    int maxSum = 0;   
4.  
5.    for( int i = 0; i < a.length; i++ )   
6.        for( int j = i; j < a.length; j++ )   
7.        {   
8.            int thisSum = 0;   
9.  
10.            for( int k = i; k <= j; k++ )   
11.                thisSum += a[ k ];   
12.  
13.            if( thisSum > maxSum )   
14.            {   
15.                maxSum   = thisSum;   
16.                seqStart = i;   
17.                seqEnd   = j;   
18.            }   
19.        }   
20.  
21.    return maxSum;   
22.}  
public static int maxSubSum1( int [ ] a )
{
    int maxSum = 0;

    for( int i = 0; i < a.length; i++ )
        for( int j = i; j < a.length; j++ )
        {
            int thisSum = 0;

            for( int k = i; k <= j; k++ )
                thisSum += a[ k ];

            if( thisSum > maxSum )
            {
                maxSum   = thisSum;
                seqStart = i;
                seqEnd   = j;
            }
        }

    return maxSum;
} 

笼统的来说，这种方法有一个3重循环，时间复杂度有O(N^3)。

 

改进

前面那个最简单暴力的方法虽然看起来能解决问题，但是循环遍历的次数太多了。里面还是有不少可以改进的空间。比如说，每次我们用变量k遍历i到j的时候，都要计算求和。

实际上当每次j增加1时，k把前面计算的结果在循环里又计算了一遍。这是完全没必要的，完全可以重复利用前面的计算结果。这样，通过这么一点，改进，我们可以得到如下的算法代码：

Java代码  
1.public static int maxSubSum2( int [ ] a )   
2.{   
3.    int maxSum = 0;   
4.  
5.    for( int i = 0; i < a.length; i++ )   
6.    {   
7.        int thisSum = 0;   
8.        for( int j = i; j < a.length; j++ )   
9.        {   
10.            thisSum += a[ j ];   
11.  
12.            if( thisSum > maxSum )   
13.            {   
14.                maxSum = thisSum;   
15.                seqStart = i;   
16.                seqEnd   = j;   
17.            }   
18.        }   
19.    }   
20.  
21.    return maxSum;   
22.}  
public static int maxSubSum2( int [ ] a )
{
    int maxSum = 0;

    for( int i = 0; i < a.length; i++ )
    {
        int thisSum = 0;
        for( int j = i; j < a.length; j++ )
        {
            thisSum += a[ j ];

            if( thisSum > maxSum )
            {
                maxSum = thisSum;
                seqStart = i;
                seqEnd   = j;
            }
        }
    }

    return maxSum;
} 

这种方法更进一步的在于，没必要在i和j之间进行遍历，所以时间复杂度为O(N^2)。对于每个外围循环i来说，当内层的循环j走完一遍，则获得了从i开头到j结束的所有子序列中最大的那个。

线性算法

这个问题还是存在着一个线性时间复杂度的解法。需要我们对数组的序列进行进一步的分析。我们在数组中间找到的连续子序列，可能存在和为负的序列。如果需要找到一个最大的子数组的话，

肯定该序列不是在最大子序列当中的。我们可以通过反证的方式来证明。

假设数组A[i...j]，里面的元素和为负。如果A[i....j]在一个最大子序列的数组中间，假定为A[i...k]，k > j。那么既然从i到j这一段是负的，

我把这一段去掉剩下的部分完全比我们假定的这个最大子序列还要大。这就和我的假设矛盾了。

这个假设还有一个限制，就是该数组就是从i开头的。否则有人可能会这么问，如果我A[i...j]这一部分确实是一个负数，但是在A[i]前面是一个很大的正数，使得他们的和为正数。

那不就使得我们的结果不成立了么？如果我们是从某个数组的开头i开始的话，就不存在这个情况。

结合前面的讨论，我们就可以发现一个有意思的事情，就是假设我们从数组的开头A[0]开始，不断的往后面走，每一步判断是否当前和最大，并保存结果。

当发现当前字串和为负数的时候，我们可以直接跳过。假设当前的索引为i的话，从0到i这一段的和是负数，可以排除。然后再从当前元素的后面开始找。这样可以得到最终最大子串和以及串的起点和终点。

详细的实现代码如下：

 

Java代码  
1.public static int maxSubSum3( int [ ] a )   
2.{   
3.    int maxSum = 0;   
4.    int thisSum = 0;   
5.  
6.    for( int i = 0, j = 0; j < a.length; j++ )   
7.    {   
8.        thisSum += a[ j ];   
9.  
10.        if( thisSum > maxSum )   
11.        {   
12.            maxSum = thisSum;   
13.            seqStart = i;   
14.            seqEnd   = j;   
15.        }   
16.        else if( thisSum < 0 )   
17.        {   
18.            i = j + 1;   
19.            thisSum = 0;   
20.        }   
21.    }   
22.  
23.    return maxSum;   
24.}  
public static int maxSubSum3( int [ ] a )
{
    int maxSum = 0;
    int thisSum = 0;

    for( int i = 0, j = 0; j < a.length; j++ )
    {
        thisSum += a[ j ];

        if( thisSum > maxSum )
        {
            maxSum = thisSum;
            seqStart = i;
            seqEnd   = j;
        }
        else if( thisSum < 0 )
        {
            i = j + 1;
            thisSum = 0;
        }
    }

    return maxSum;
}

该方法只需要遍历数组一遍，通过跳过这些中间和为负的结果。算法时间复杂度为O(N).

总结分析
        
这是一个比较有意思的问题。以前和朋友们曾经讨论过。当然，是在没看过书上的那么多分析之后自己也想到了一个近似O(N)的解法。当时还利用了一种情形，

将所有为相邻为正的元素以及为负的元素分别累加起来构成一个新的数组。因为如果要达到最大的子数组，要么就要覆盖所有相邻的正整数，

要么会包含一段相邻的负整数子序列。然后形成一个正负数相间的数组。这样再利用前面的线性算法特性进行比较。虽然不如前面的好，但是这是自己思考的结果，总是有价值的。

另外，在某些情况下会出现上述问题的一个变种，就是假设我们需要支持负数的情况。最坏的情况就是所有元素都是负的，那么就相当于在里面找到最大的那个元素。

我们的代码就需要稍微做一点修改。至于怎么改，如果你看明白了分析和代码的话，你懂的:)

还有一个需要说明一点的就是，在实现的代码中用到了seqStart和seqEnd两个变量。可以将这两个元素定义为类的static变量。这样就构成一个完整的程序了。
