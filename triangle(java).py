class Solution:
    # @param triangle, a list of lists of integers
    # @return an integer
    def minimumTotal(self, triangle):
        DP = [0 for i in range(len(triangle))]
        for row in triangle:            #遍历triangle的每一行
            prevDP = DP[:]              #prevDP总是复制上一次DP的结果 即当前DP的上一行
            for i in range(len(row)):       #对每一行的元素逐个遍历
                if i == 0:                      #如果是第一个元素 它的和只有一种情况 即所有行第一个元素相加
                    DP[i] = prevDP[i] + row[i]
                elif i == len(row) - 1:             #如果是最后一个元素 它也只有一种情况 即所有行最后一个元素相加
                    DP[i] = prevDP[i-1] + row[i]
                else:                                   #正常情况 当前元素最小路径和 = 当前元素值 + 上一行与当前元素相邻的两个元素的路径和较小的那个
                    DP[i] = min(prevDP[i-1], prevDP[i]) + row[i]
        return min(DP)      #最后返回最后一行中最小的值即为所求

Note: 动态规划, 使用两个数组, DP记录当前行信息, prevDP记录上一行信息。DP[i]的含义是从顶端走到当前行DP[i]这个元素的最小路径和。

看照片






public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0)
            return 0;
        int[] sum = new int[triangle.size()];
        sum[0] = triangle.get(0).get(0);
        for(int i=1; i<triangle.size(); i++) {
            sum[i] = sum[i-1] + triangle.get(i).get(i);     //从末尾元素开始
            for(int j=i-1; j>=1; j--) {                     //动态规划处理中间元素
                sum[j] = sum[j]<sum[j-1]?sum[j]:sum[j-1] + triangle.get(i).get(j);
            }
            sum[0] += triangle.get(i).get(0);       //最后处理第一个元素
        }
        int min = sum[0];           //到三角形底部最后一行比较所有元素取最小的路径和
        for(int i=1; i<sum.length; i++)
            min = Math.min(min, sum[i]);
        return min;
    }
}

动态规划的简单题 从上往下扫 每次从行尾开始 每个元素存储上一行相邻的较小值加自身




public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0)
            return 0;
        int sum[] = new int[triangle.size()];
        for(int i=0; i<triangle.size(); i++) {
            sum[i] = triangle.get(triangle.size()-1).get(i);
        }
        for(int i=triangle.size()-2; i>=0; i--) {
            for(int j=0; j<=i; j++)
                sum[j] = Math.min(sum[j], sum[j+1]) + triangle.get(i).get(j);
        }
        return sum[0];
    }
}

此题的扩展 自下向上找最小路径和 不需要最后对于所有路径找最小的，而且第一个元素和最后元素也不需要单独处理了，所以代码简洁了很多







public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] DP = new int[triangle.size()];                //取list size
        int minimum = 10000;
        for(int row=0; row<triangle.size(); row++)
        {
            int[] prevDP = Arrays.copyOf(DP, DP.length);            //copy一个数组可以这么写
            for(int col=0; col<triangle.get(row).size(); col++)
            {
                if(col==0)
                    DP[col] = prevDP[col] + triangle.get(row).get(col);     //这里拿元素值得写法要注意
                else if(col==triangle.get(row).size()-1)
                    DP[col] = prevDP[col-1] + triangle.get(row).get(col);
                else
                    DP[col] = Math.min(prevDP[col-1], prevDP[col]) + triangle.get(row).get(col);
            }
        }
        for(int i=0; i<DP.length; i++)
            if(DP[i]<minimum)
                minimum = DP[i];
        return minimum;
    }
}

Note: 这个解法按照python版本改的 code ganker的解法没太看明白 这题有写好的完整java程序 记得看

对于二维arraylist的调用要熟练掌握









from code ganker:

这是一道动态规划的题目，求一个三角形二维数组从顶到低端的最小路径和。思路是维护到某一个元素的最小路径和，

那么在某一个元素i，j的最小路径和就是它上层对应的相邻两个元素的最小路径和加上自己的值，

递推式是sum[i][j]=min(sum[i-1][j-1],sum[i-1][j])+triangle[i][j]。最后扫描一遍最后一层的路径和，取出最小的即可。每个元素需要维护一次，

总共有1+2+...+n=n*(n+1)/2个元素，所以时间复杂度是O(n^2)。而空间上每次只需维护一层即可（因为当前层只用到上一层的元素），

所以空间复杂度是O(n)。代码如下：

public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    if(triangle == null || triangle.size() == 0)
        return 0;
    if(triangle.size()==1)
        return triangle.get(0).get(0);
    int[] sums = new int[triangle.size()];
    sums[0] = triangle.get(0).get(0);
    for(int i=1;i<triangle.size();i++)
    {
        sums[i] = sums[i-1]+triangle.get(i).get(i);
        for(int j=i-1;j>=1;j--)
        {
            sums[j] = (sums[j]<sums[j-1]?sums[j]:sums[j-1]) + triangle.get(i).get(j);
        }
        sums[0] = sums[0]+triangle.get(i).get(0);
        
    }
    int minimum = sums[0];
    for(int i=1;i<sums.length;i++)
    {
        if(sums[i]<minimum)
        {
            minimum = sums[i];
        }
    }
    return minimum;
}

上述代码实现时要注意每层第一个和最后一个元素特殊处理一下。这道题是不错的动态规划题目，模型简单，比较适合在电面中出现