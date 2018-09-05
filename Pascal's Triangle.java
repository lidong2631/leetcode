Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]





class Solution:
    # @return a list of lists of integers
    def generate(self, numRows):
        if numRows == 0:
            return []
        if numRows == 1:
            return [[1]]
        if numRows == 2:
            return [[1], [1, 1]]
        if numRows > 2:
            list = [[] for i in range(numRows)]
            for i in range(0, numRows):
                list[i] = [1 for j in range(i + 1)]
            for i in range(2, numRows):
                for j in range(1, i):
                    list[i][j] = list[i - 1][j - 1] + list[i - 1][j]
            return list




Java:
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j+1));
            res.add(new ArrayList<>(row));
        }
        return res;
    }
}

O(n^2) O(n^2)




from code ganker:

这道题比较简单，属于基础的数组操作。基本思路是每层保存前一行的指针，然后当前航数据根据上一行来得到，

每个元素就是上一行两个相邻元素相加（第一个和最后一个元素是1）。算法时间复杂度应该是O(1+2+3+...+n)=O(n^2)，空间上只需要二维数组来存储结果，

不需要额外空间。代码如下

public ArrayList<ArrayList<Integer>> generate(int numRows) {
     ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
     if(numRows<=0)
        return res;
     ArrayList<Integer> pre = new ArrayList<Integer>();
     pre.add(1);
     res.add(pre);
     for(int i=2;i<=numRows;i++)
     {
         ArrayList<Integer> cur = new ArrayList<Integer>();
         cur.add(1);
         for(int j=0;j<pre.size()-1;j++)
         {
             cur.add(pre.get(j)+pre.get(j+1));
         }
         cur.add(1);
         res.add(cur);
         pre = cur;
     }
     return res;
}

这道题因为是求解每一行结果，所以空间上没什么好讲究的，Pascal's Triangle II只求解某一行的数据，反而可以在空间上进行精简，有兴趣的朋友可以看看哈。

