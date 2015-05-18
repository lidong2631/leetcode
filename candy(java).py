<<<<<<< HEAD
class Solution:
    # @param ratings, a list of integer
    # @return an integer
    def candy(self, ratings):
        candyNum = [1 for i in range(0, len(ratings))]         #创建一个list candyNum 初始每个小孩都有一个candy
        for i in range(1, len(ratings)):                    #第一遍循环 如果rating递增 candy+1
            if ratings[i]>ratings[i-1]:
                candyNum[i] = candyNum[i-1] + 1
        for i in range(len(ratings)-2, -1, -1):             #第二遍循环 反过来如果ratings递增 candy+1
            if ratings[i+1]<ratings[i] and candyNum[i+1]>=candyNum[i]:
                candyNum[i] = candyNum[i+1] + 1
        return sum(candyNum)





解题思路：求最少的蛋糕数。先从前到后扫描一遍数组，如果序列递增，就+1；然后从后到前扫描一遍数组，序列递增，+1。保证最低谷（ratings最小）永远是1就可以了。

代码：


class Solution:
    # @param ratings, a list of integer
    # @return an integer
    def candy(self, ratings):
        candynum = [1 for i in range(len(ratings))]
        for i in range(1, len(ratings)):
            if ratings[i] > ratings[i-1]:
                candynum[i] = candynum[i-1] + 1
        for i in range(len(ratings)-2, -1, -1):
            if ratings[i+1] < ratings[i] and candynum[i+1] >= candynum[i]:
                candynum[i] = candynum[i+1] + 1
        return sum(candynum)





public class Solution {
    public int candy(int[] ratings) {
        int[] candyNum = new int[ratings.length];
        candyNum[0] = 1;
        int res = 0;
        for(int i=1; i<ratings.length; i++)
        {    
            if(ratings[i]>ratings[i-1])
                candyNum[i] = candyNum[i-1] + 1;
            else
                candyNum[i] = 1;
        }
        res+=candyNum[ratings.length-1];
        for(int i=ratings.length-2; i>=0; i--)
        {
            if(ratings[i]>ratings[i+1] && candyNum[i]<=candyNum[i+1])   //注意这里candyNum[i]<=candyNum[i+1] 参考例子[5,3,1]
            {
                candyNum[i] = candyNum[i+1] + 1;
                res+=candyNum[i];
            }
            else
                res+=candyNum[i];
        }
        return res;
    }
}

Note: 扫两遍 第一遍从左往右 维护左边最小 递增就前面的值＋1 否则给1 第二遍从右往左 维护右边最小 递增且candy数比前一个小 就更新位前一个值＋1 否则维持原来值



from code ganker:

这道题用到的思路和Trapping Rain Water是一样的，用动态规划。基本思路就是进行两次扫描，一次从左往右，一次从右往左。

第一次扫描的时候维护对于每一个小孩左边所需要最少的糖果数量，存入数组对应元素中，第二次扫描的时候维护右边所需的最少糖果数，

并且比较将左边和右边大的糖果数量存入结果数组对应元素中。这样两遍扫描之后就可以得到每一个所需要的最最少糖果量，从而累加得出结果。

方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)。代码如下： 

public int candy(int[] ratings) {
    if(ratings==null || ratings.length==0)
    {
        return 0;
    }
    int[] nums = new int[ratings.length];
    nums[0]=1;
    
    for(int i=1;i<ratings.length;i++)
    {
        if(ratings[i]>ratings[i-1])
        {
            nums[i] = nums[i-1]+1;
        }
        else
        {
            nums[i] = 1;
        }
    }
    int res = nums[ratings.length-1];
    for(int i=ratings.length-2;i>=0;i--)
    {
        int cur = 1;
        if(ratings[i]>ratings[i+1])
        {
            cur = nums[i+1]+1;
        }
        res += Math.max(cur,nums[i]);
        nums[i] = cur;
    }
    return res;
} 

这种两边扫描的方法是一种比较常用的技巧，LeetCode中Trapping Rain Water和这道题都用到了，可以把这种方法作为自己思路的一部分，通常是要求的变量跟左右元素有关系的题目会用到哈。

