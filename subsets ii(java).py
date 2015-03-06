class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup(self, S):
        def dfs(level, start, list):
            if list not in res:         #同上一题一样 只不过多加这一句剪枝条件
                res.append(list)
            if level == len(S):
                return
            for i in range(start, len(S)):
                dfs(level+1, i+1, list+[S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res





题意：

Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:

•Elements in a subset must be in non-descending order.
•The solution set must not contain duplicate subsets.
 

For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]解题思路：和上一道题一样，求一个集合的所有子集。和上一道题不一样的一点是集合可能有重复元素。这道题同样使用dfs来解题，只是需要在dfs函数里加一个剪枝的条件，排除掉同样的子集。

代码：


class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup(self, S):
        def dfs(depth, start, valuelist):
            if valuelist not in res: res.append(valuelist)
            if depth == len(S): return
            for i in range(start, len(S)):
                dfs(depth+1, i+1, valuelist+[S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res






public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if(num==null)
            return null;
        Arrays.sort(num);
        List<Integer> lastSize = new ArrayList<Integer>();
        lastSize.add(0);
        return helper(num, num.length-1, lastSize);
    }
    
    private List<List<Integer>> helper(int[] num, int index, List<Integer> lastSize) {
        if(index==-1) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> item = new ArrayList<Integer>();
            res.add(item);
            lastSize.set(0, 1); //这一句加不加都行 第一个元素肯定没有重复的可能 这样执行到最后lastSize就会被set为1
            return res;
        }
        List<List<Integer>> res = helper(num, index-1, lastSize);
        int size = res.size();
        int start = 0;
        if(index>0 && num[index]==num[index-1])
            start = lastSize.get(0);
        for(int i=start; i<size; i++) {
            List<Integer> newItem = new ArrayList<Integer>(res.get(i));
            newItem.add(num[index]);
            res.add(newItem);
        }
        lastSize.set(0, size);
        return res;
    }
}

第二遍写的










public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if(num==null)
            return null;
        Arrays.sort(num);
        ArrayList<Integer> lastSize = new ArrayList<Integer>();
        lastSize.add(0);
        return helper(num, num.length-1, lastSize);
    }
    
    private List<List<Integer>> helper(int[] num, int index, ArrayList<Integer> lastSize) {
        if(index==-1)           //这一步是递归到底 加空集
        {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> tmp = new ArrayList<Integer>();
            res.add(tmp);
            return res;
        }
        List<List<Integer>> res = helper(num, index-1, lastSize);   //从这里开始回溯
        int start = 0;
        if(index>0 && num[index]==num[index-1])     //判断下一个元素是否是重复元素 如果是更新start从上一元素处开始
        {
            start = lastSize.get(0);
        }
        int size = res.size();
        for(int i=start; i<size; i++)               //循环将新元素加到res中
        {
            List<Integer> tmp = new ArrayList<Integer>(res.get(i));
            tmp.add(num[index]);
            res.add(tmp);
        }
        lastSize.set(0, size);                      //最后更新lastSize
        return res;
    }
}

Note: 递归解法 跟subsets基本一样 除了加了个判断在88到91




public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        if(num==null)
            return res;
        Arrays.sort(num);
        int start = 0;
        for(int i=0; i<num.length; i++)
        {
            int size = res.size();
            for(int j=start; j<size; j++)
            {
                List<Integer> tmp = new ArrayList<Integer>(res.get(j));
                tmp.add(num[i]);
                res.add(tmp);
            }
            if(i<num.length-1 && num[i]==num[i+1])
            {
                start = size;
            }
            else
                start = 0;
        }
        return res;
    }
}

Note: 非递归解法 跟subsets差不多 除了126加了个判断



from code ganker:

这道题跟Subsets一样是经典的NP问题--求子集。比Subsets稍微复杂一些的是这里的集合中可能出现重复元素，因此我们在求子集的时候要避免出现重复的子集。

在Subsets中我们每次加进一个元素就会把原来的子集都加上这个元素，然后再加入到结果集中，但是这样重复的元素就会产生重复的子集。为了避免这样的重复，

需要用个小技巧。

其实比较简单，就是每当遇到重复元素的时候我们就只把当前结果集的后半部分加上当前元素加入到结果集中，因为后半部分就是上一步中加入这个元素的所有子集，

上一步这个元素已经加入过了，前半部分如果再加就会出现重复。所以算法上复杂度上没有提高，反而少了一些操作，就是遇到重复时少做一半，

不过这里要对元素集合先排序，否则不好判断重复元素。同样的还是可以用递归和非递归来解，不过对于重复元素的处理是一样的。递归的代码如下：

public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
    if(num == null)
        return null;
    Arrays.sort(num);
    ArrayList<Integer> lastSize = new ArrayList<Integer>();
    lastSize.add(0);
    return helper(num, num.length-1, lastSize);
}
private ArrayList<ArrayList<Integer>> helper(int[] num, int index, ArrayList<Integer> lastSize)
{
    if(index == -1)
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> elem = new ArrayList<Integer>();
        res.add(elem);
        return res;
    }
    ArrayList<ArrayList<Integer>> res = helper(num,index-1,lastSize);
    int size = res.size();
    int start = 0;
    if(index>0 && num[index]==num[index-1])
        start = lastSize.get(0);
    for(int i=start;i<size;i++)
    {
        ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
        elem.add(num[index]);
        res.add(elem);
    }
    lastSize.set(0,size);
    return res;
}

非递归的代码如下：

public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    res.add(new ArrayList<Integer>());
    if(num==null || num.length==0)
        return res;
    Arrays.sort(num);
    int start = 0;
    for(int i=0;i<num.length;i++)
    {
        int size = res.size();
        for(int j=start;j<size;j++)
        {
            ArrayList<Integer> newItem = new ArrayList<Integer>(res.get(j));
            newItem.add(num[i]);
            res.add(newItem);
        }
        if(i<num.length-1 && num[i]==num[i+1])
        {
            start = size;
        }
        else
        {
            start = 0;
        }
    }
    return res;
}

这种NP问题的重复处理在LeetCode有一定出现频率，比如还有Permutations II也是这样的，其实本质就是当一个重复元素进来时忽略上一个元素已经有的结果，

只考虑由重复元素所产生的新结果。

