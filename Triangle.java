Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.




Java:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                res[j] = Math.min(res[j], res[j+1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}

O(n^2) O(n)

https://leetcode.com/problems/triangle/discuss/38724/7-lines-neat-Java-Solution



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