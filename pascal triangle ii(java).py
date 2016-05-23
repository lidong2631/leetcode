class Solution:
    # @return a list of integers
    def getRow(self, rowIndex):
        actualRow =[1]                      #杨辉三角第一行[1]
        for row in range(0, rowIndex):      #循环每一行
            previousRow = actualRow         #上一行附值为上一次的actualRow
            actualRow = [1]                 #每一行第一个元素为1
            for i in range(1,len(previousRow)):                     #循环 从第二个元素开始附值 到上一行结尾个数为止
                actualRow.append(previousRow[i-1] + previousRow[i]) #每一个元素为上一行相邻两个元素相加
            actualRow.append(1)             #最后加上一个1为最后元素
        return actualRow



The following solution use O(k^2) space but still accepted by OJ
class Solution:
    # @return a list of integers
    def getRow(self, rowIndex):
        if rowIndex == 0:
            return [1]
        if rowIndex == 1:
            return [1,1]
        pascalTri = [[] for i in range(rowIndex+1)]
        pascalTri[0] = [1]
        pascalTri[1] = [1,1]
        for i in range(2, rowIndex+1):
            pascalTri[i] = [1 for j in range(i+1)]
            for j in range(1, i):
                pascalTri[i][j] = pascalTri[i-1][j-1] + pascalTri[i-1][j]
        return pascalTri[rowIndex]






public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if(rowIndex<=0)
            return res;
        res.add(1);
        for(int i=1; i<rowIndex+1; i++) {
            for(int j=res.size()-1; j>0; j--) {
                res.set(j, res.get(j) + res.get(j-1));
            }
            res.add(1);
        }
        return res;
    }
}

第二遍写法 j从res.size()-1开始 这题主要体会从后往前更新元素值 省空间的思想 因为需要的res[i]只在当前步用，下一步就不需要了。这个技巧在动态规划省空间时也经常使用

k = 3
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],





public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if(rowIndex<0)
            return res;
        res.add(1);         //第一行 [1]
        for(int i=1; i<=rowIndex; i++)      //从第二行开始
        {
            for(int j=res.size()-2; j>=0; j--)      //这里是逆序遍历
                res.set(j+1, res.get(j+1)+res.get(j));      //还是那个公式 j+1那个位置只在计算下一行j+1位置元素时用 所以计算完就可以被覆盖掉 利用这点这里我们只用O(k)空间就可以了
            res.add(1);             //最后每次循环结束当前行尾元素给1
        }
        return res;
    }
}





from code ganker:

这道题跟Pascal's Triangle很类似，只是这里只需要求出某一行的结果。Pascal's Triangle中因为是求出全部结果，

所以我们需要上一行的数据就很自然的可以去取。而这里我们只需要一行数据，就得考虑一下是不是能只用一行的空间来存储结果而不需要额外的来存储上一行呢？

这里确实是可以实现的。对于每一行我们知道如果从前往后扫，第i个元素的值等于上一行的res[i]+res[i+1]，可以看到数据是往前看的，

如果我们只用一行空间，那么需要的数据就会被覆盖掉。所以这里采取的方法是从后往前扫，这样每次需要的数据就是res[i]+res[i-1]，

我们需要的数据不会被覆盖，因为需要的res[i]只在当前步用，下一步就不需要了。这个技巧在动态规划省空间时也经常使用，

主要就是看我们需要的数据是原来的数据还是新的数据来决定我们遍历的方向。时间复杂度还是O(n^2)，而空间这里是O(k)来存储结果，仍然不需要额外空间。

代码如下：

public ArrayList<Integer> getRow(int rowIndex) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(rowIndex<0)
        return res;
    res.add(1);
    for(int i=1;i<=rowIndex;i++)
    {
        for(int j=res.size()-2;j>=0;j--)
        {
            res.set(j+1,res.get(j)+res.get(j+1));
        }
        res.add(1);
    }
    return res;
}

这道题相比于Pascal's Triangle其实更有意思一些，因为有一个考点就是能否省去额外空间，在面试中出现的可能性大一些，不过总体比较简单，电面中比较合适。


