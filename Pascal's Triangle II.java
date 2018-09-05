Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?



Java:
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
        }
        return row;
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


