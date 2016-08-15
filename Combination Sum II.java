public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] candidates, int target, int index, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0) return;
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i-1]) continue;
            list.add(candidates[i]);
            helper(candidates, target-candidates[i], i + 1, list, res);
            list.remove(list.size()-1);
        }
    }
}





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

