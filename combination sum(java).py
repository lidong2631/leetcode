<<<<<<< HEAD
class Solution:
    # @param candidates, a list of integers
    # @param target, integer
    # @return a list of lists of integers
    def combinationSum(self, candidates, target):
        def dfs(num, start, tmpList):
            if num == target:               #如果当前num等于target 则将解集append到res中 return
                res.append(tmpList)
                return
            for i in range(start, len(candidates)):         #否则 从当前start位置开始循环遍历后面的每一个数
                if i > start and candidates[i] == candidates[i-1]:  #在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了， 所以应该跳过那层递归
                    continue
                if num < target:                                    #如果num还小于target 递归调用dfs 否则如果大于target继续尝试下一个数
                    dfs(num+candidates[i], i, tmpList+[candidates[i]])
                elif num > target:                          #否则num大于target 后面的数就不用算了因为是递增排列 直接break 循环
                    break
        
        candidates.sort()           #题目要求输出序列是递增顺序
        res = []                        #res接收所有解集
        num = 0                      #num初始为0 因为所有数都是正整数 所以一开始num一定小于target
        dfs(0, 0, [])
        return res

Note: 这题思路跟n queens一样 属于np问题 都是一个套路 注意dfs(candidates[i], i, [candidates[i]])这里初始是'[candidates[i]]'不是[]







public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates==null || candidates.length==0)
            return res;
        Arrays.sort(candidates);            //记得要排序
        helper(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] candidates, int start, int target, List<Integer> item, List<List<Integer>> res) {
        if(target<0)
            return;
        if(target==0)
        {
            res.add(new ArrayList(item));       //这里是要new一个相当于copy一个item 如果直接用item它后面还会循环递归下去 每次会更改item里的内容 进而影响到这个res里的结果
            return;
        }
        for(int i=start; i<candidates.length; i++)
        {
            if(i>0 && candidates[i]==candidates[i-1])   //对于重复数字要跳过 因为这里每个数字可以重复用无限次 后面的递归一样会重复用这个数字
                continue;
            item.add(candidates[i]);
            helper(candidates, i, target-candidates[i], item, res);
            item.remove(item.size()-1);             //记的每一次循环完都要都要把这次加到item的数字移除
        }
    }
}

Note： NP问题套路 几个点注意下 1排序 2 对重复数字处理

第16行为什么不能直接res.add(item)；要重新建个新的附值。

因为这个item到其他递归层中还要用的，如果直接用这个等会他会被改变， 这个加进去的也就不对了哈～









from code ganker:

这个题是一个NP问题，方法仍然是N-Queens中介绍的套路。基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，

然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。算法复杂度因为是NP问题，所以自然是指数量级的。代码如下：

public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(candidates == null || candidates.length==0)
        return res;
    Arrays.sort(candidates);
    helper(candidates,0,target,new ArrayList<Integer>(),res);
    return res;
}
private void helper(int[] candidates, int start, int target, ArrayList<Integer> item, 
ArrayList<ArrayList<Integer>> res)
{
    if(target<0)
        return;
    if(target==0)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=start;i<candidates.length;i++)
    {
        if(i>0 && candidates[i]==candidates[i-1])
            continue;
        item.add(candidates[i]);
        helper(candidates,i,target-candidates[i],item,res);
        item.remove(item.size()-1);
    }
}


注意在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，

所以应该跳过那层递归。这道题有一个非常类似的题目Combination Sum II，有兴趣的朋友可以看看，一次搞定两个题哈。
=======
class Solution:
    # @param candidates, a list of integers
    # @param target, integer
    # @return a list of lists of integers
    def combinationSum(self, candidates, target):
        def dfs(num, start, tmpList):
            if num == target:               #如果当前num等于target 则将解集append到res中 return
                res.append(tmpList)
                return
            for i in range(start, len(candidates)):         #否则 从当前start位置开始循环遍历后面的每一个数
                if i > start and candidates[i] == candidates[i-1]:  #在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了， 所以应该跳过那层递归
                    continue
                if num < target:                                    #如果num还小于target 递归调用dfs 否则如果大于target继续尝试下一个数
                    dfs(num+candidates[i], i, tmpList+[candidates[i]])
                elif num > target:                          #否则num大于target 后面的数就不用算了因为是递增排列 直接break 循环
                    break
        
        candidates.sort()           #题目要求输出序列是递增顺序
        res = []                        #res接收所有解集
        num = 0                      #num初始为0 因为所有数都是正整数 所以一开始num一定小于target
        dfs(0, 0, [])
        return res

Note: 这题思路跟n queens一样 属于np问题 都是一个套路 注意dfs(candidates[i], i, [candidates[i]])这里初始是'[candidates[i]]'不是[]







public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates==null || candidates.length==0)
            return res;
        Arrays.sort(candidates);            //记得要排序
        helper(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] candidates, int start, int target, List<Integer> item, List<List<Integer>> res) {
        if(target<0)
            return;
        if(target==0)
        {
            res.add(new ArrayList(item));       //这里是要new一个相当于copy一个item 如果直接用item它后面还会循环递归下去 每次会更改item里的内容 进而影响到这个res里的结果
            return;
        }
        for(int i=start; i<candidates.length; i++)
        {
            if(i>0 && candidates[i]==candidates[i-1])   //对于重复数字要跳过 因为这里每个数字可以重复用无限次 后面的递归一样会重复用这个数字
                continue;
            item.add(candidates[i]);
            helper(candidates, i, target-candidates[i], item, res);
            item.remove(item.size()-1);             //记的每一次循环完都要都要把这次加到item的数字移除
        }
    }
}

Note： NP问题套路 几个点注意下 1排序 2 对重复数字处理

第16行为什么不能直接res.add(item)；要重新建个新的附值。

因为这个item到其他递归层中还要用的，如果直接用这个等会他会被改变， 这个加进去的也就不对了哈～









from code ganker:

这个题是一个NP问题，方法仍然是N-Queens中介绍的套路。基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，

然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。算法复杂度因为是NP问题，所以自然是指数量级的。代码如下：

public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(candidates == null || candidates.length==0)
        return res;
    Arrays.sort(candidates);
    helper(candidates,0,target,new ArrayList<Integer>(),res);
    return res;
}
private void helper(int[] candidates, int start, int target, ArrayList<Integer> item, 
ArrayList<ArrayList<Integer>> res)
{
    if(target<0)
        return;
    if(target==0)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=start;i<candidates.length;i++)
    {
        if(i>0 && candidates[i]==candidates[i-1])
            continue;
        item.add(candidates[i]);
        helper(candidates,i,target-candidates[i],item,res);
        item.remove(item.size()-1);
    }
}


注意在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，

所以应该跳过那层递归。这道题有一个非常类似的题目Combination Sum II，有兴趣的朋友可以看看，一次搞定两个题哈。
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
