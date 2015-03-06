class Solution:
    # @param candidates, a list of integers
    # @param target, integer
    # @return a list of lists of integers
    def combinationSum2(self, candidates, target):
        def dfs(num, start, tmpList):
            if num == target:
                res.append(tmpList)
                return
            for i in range(start, len(candidates)):
                if i > start and candidates[i] == candidates[i-1]:      #如果是相邻的重复元素 只递归第一个 因为后面重复元素的情况在上一层递归会被考虑到
                    continue
                if num < target:
                    dfs(num+candidates[i], i+1, tmpList+[candidates[i]])    #注意递归时i要加1 因为每个元素只能取一次
                elif num > target:
                    break
        
        candidates.sort()
        res = []
        num = 0
        dfs(0, 0, [])
        return res

Note: 这题跟上一题大致相同 区别是递归是从下一元素开始 并且还需要判断重复元素







public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length==0)
            return res;
        Arrays.sort(num);
        helper(num, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, int start, int target, List<Integer> item, List<List<Integer>> res) {
        if(target==0)                   //这里要注意！！一定要先写target==0的判断 再写target<0||start>=num.length 例如num = [1],如果先判断start>=length就会直接返回 res不会加上这个结果就错了
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        if(target<0 || start>=num.length)
            return;
        for(int i=start; i<num.length; i++)     //这里i从start开始
        {
            if(i>start && num[i]==num[i-1])     //i>start
                continue;
            item.add(num[i]);
            helper(num, i+1, target-num[i], item, res);     //每次i加1
            item.remove(item.size()-1);
        }
    }
}

Note: 根据code ganker 这题跟上一题大致相同 只是注意递归时i+1 还有注释的几个地方





from code ganker:

这道题跟Combination Sum非常相似，不了解的朋友可以先看看，唯一的区别就是这个题目中单个元素用过就不可以重复使用了。乍一看好像区别比较大，

但是其实实现上只需要一点点改动就可以完成了，就是递归的时候传进去的index应该是当前元素的下一个。代码如下：

public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num == null || num.length==0)
        return res;
    Arrays.sort(num);
    helper(num,0,target,new ArrayList<Integer>(),res);
    return res;
}
private void helper(int[] num, int start, int target, ArrayList<Integer> item,
ArrayList<ArrayList<Integer>> res)
{
    if(target == 0)         
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(target<0 || start>=num.length)
        return;
    for(int i=start;i<num.length;i++)       
    {
        if(i>start && num[i]==num[i-1]) continue;       
        item.add(num[i]);
        helper(num,i+1,target-num[i],item,res);     
        item.remove(item.size()-1);
    }
}

在这里我们还是需要在每一次for循环前做一次判断，因为虽然一个元素不可以重复使用，但是如果这个元素重复出现是允许的，但是为了避免出现重复的结果集，

我们只对于第一次得到这个数进行递归，接下来就跳过这个元素了，因为接下来的情况会在上一层的递归函数被考虑到，这样就可以避免重复元素的出现。

这个问题可能会觉得比较绕，大家仔细想想就明白了哈

