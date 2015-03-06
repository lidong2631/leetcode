class Solution:
    # @param S, a list of integer
    # @return a list of lists of integer
    def subsets(self, S):
        def getSubsets(level, start, list):
            res.append(list)                    #每次递归调用先将上一list append到res
            if level == len(S):                 #如果level和len(S)相等 说明已经到底 结束递归
                return
            for i in range(start, len(S)):      #否则循环len(S) - start次 对每一个数分别做深度遍历
                getSubsets(level+1, i+1, list+[S[i]])   #递归 每次level＋1 代表又用完了一个子集合 i每次循环加1代表对于当前的数 开始位置又后移一个 每次将当前list和S[i]加到一起组成新的子集合进行下次递归
        S.sort()                                #题目要求elements in a subset must be in non-descending order 所以要先排序
        res = []                                #初始res为[]
        getSubsets(0, 0, [])                    #开始调用getSubsets函数 从空子集［］开始
        return res

Note: 这题要看图理解



题意：枚举所有子集。

解题思路：碰到这种问题，一律dfs。

代码：

复制代码
class Solution:
    # @param S, a list of integer
    # @return a list of lists of integer
    
    def subsets(self, S):
        def dfs(depth, start, valuelist):
            res.append(valuelist)
            if depth == len(S): return
            for i in range(start, len(S)):
                dfs(depth+1, i+1, valuelist+[S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res






public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        if(S==null)
            return null;
        Arrays.sort(S);
        return helper(S, S.length);
    }
    
    private List<List<Integer>> helper(int[] S, int index) {
        if(index==0) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> item = new ArrayList<Integer>();
            res.add(item);
            return res;
        }
        List<List<Integer>> res = helper(S, index-1);
        int size = res.size();
        for(int i=0; i<size; i++) {
            List<Integer> newItem = new ArrayList<Integer>(res.get(i));
            newItem.add(S[index-1]);
            res.add(newItem);
        }
        return res;
    }
}

第二遍的写法









public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        if(S==null)
            return null;
        Arrays.sort(S);
        return helper(S, S.length-1);
    }
    
    private List<List<Integer>> helper(int[] S, int index) {
        if(index==-1) {
            List<Integer> item = new ArrayList<Integer>();
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            res.add(item);
            return res;
        }
        List<List<Integer>> res = helper(S, index-1);
        int size = res.size();
        for(int i=0; i<size; i++) {
            List<Integer> newItem = new ArrayList<Integer>(res.get(i));
            newItem.add(S[index]);
            res.add(newItem);
        }
        return res;
    }
}








from code ganker:

求子集问题是经典的NP问题，复杂度上我们就无法强求了哈，肯定是非多项式量级的。一般来说这个问题有两种解法：递归和非递归。

我们先来说说递归解法，主要递推关系就是假设函数返回递归集合，现在加入一个新的数字，我们如何得到包含新数字的所有子集。其实就是在原有的集合中对每集合中的每个元素都加入新元素得到子集，

然后放入原有集合中（原来的集合中的元素不用删除，因为他们也是合法子集）。而结束条件就是如果没有元素就返回空集（注意空集不是null，而是没有元素的数组）就可以了。

时间和空间都是取决于结果的数量，也就是O(2^n)，代码如下：

public ArrayList<ArrayList<Integer>> subsets(int[] num) {
    if(num == null)
        return null;
    Arrays.sort(num);
    return helper(num, num.length-1);
}
private ArrayList<ArrayList<Integer>> helper(int[] num, int index)
{
    if(index == -1)
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> elem = new ArrayList<Integer>();
        res.add(elem);
        return res;
    }
    ArrayList<ArrayList<Integer>> res = helper(num,index-1);
    int size = res.size();
    for(int i=0;i<size;i++)
    {
        ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
        elem.add(num[index]);
        res.add(elem);
    }
    return res;
}

其实非递归解法的思路和递归是一样的，只是没有回溯过程，也就是自无到有的一个个元素加进来，然后构造新的子集加入结果集中，代码如下：

public ArrayList<ArrayList<Integer>> subsets(int[] S) {
     ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>>();
     res.add(new ArrayList<Integer>());
     if(S == null || S.length == 0)
        return res;
    Arrays.sort(S);
    for(int i=0;i<S.length;i++)
    {
        int size = res.size();
        for(int j=0;j<size;j++)
        {
            ArrayList<Integer> item = new ArrayList<Integer>(res.get(j));
            item.add(S[i]);
            res.add(item);
        }
    }
    return res;
}

这种NP问题算法上都没有什么大的提高，基本上就是考察对于递归和非递归解法的理解，都是和N-Queens一个套路，掌握之后就没什么难度哈。