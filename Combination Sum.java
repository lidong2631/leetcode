Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]




Python:
class Solution:
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        res = []
        candidates.sort()
        self.helper(candidates, target, 0, [], res)
        return res
    
    def helper(self, candidates, target, index, l, res):
        if target == 0:
            res.append(l)
            return
        elif target < 0:
            return
        else:
            for i in range(index, len(candidates)):
                self.helper(candidates, target - candidates[i], i, l+[candidates[i]], res)

https://leetcode.com/problems/combination-sum/discuss/16510/Python-dfs-solution.

O(2^n)




Java:
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);    // can be omitted
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
            list.add(candidates[i]);
            helper(candidates, target - candidates[i], i, list, res);
            list.remove(list.size()-1);
        }
    }
}




Golang:
func combinationSum(candidates []int, target int) [][]int {
    var res [][]int
    sort.Ints(candidates)
    helper(candidates, target, 0, &[]int{}, &res)
    return res
}

func helper(candidates []int, target, index int, l *[]int, res *[][]int) {
    if target == 0 {
        tmp := make([]int, len(*l))
        copy(tmp, *l)
        *res = append(*res, tmp)
        return
    } else if target < 0 {
        return
    }
    for i := index; i < len(candidates); i++ {
        *l = append(*l, candidates[i])
        helper(candidates, target-candidates[i], i, l, res)
        *l = (*l)[:len(*l)-1]
    }
}





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

