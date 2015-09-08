<<<<<<< HEAD
class Solution:
    # @param num, a list of integer
    # @return a list of lists of integers
    def permuteUnique(self, num):
        if len(num) == 0:
            return []
        if len(num) == 1:
            return [num]
        num.sort()
        res = []
        prev = None
        for i in range(len(num)):
            if num[i] == prev:
                continue
            prev = num[i]
            for j in self.permuteUnique(num[:i]+num[i+1:]):
                res.append([num[i]] + j)
        return res

Note： 这题跟permutation i差别不大 只是每次要先sort下list 然后用一个变量prev记录前一个值 如果相等即代表重复值 需要剪枝

python的sort()没有返回值






题意：

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

解题思路：这道题也是穷举全排列，只是集合中可能有重复的元素。分两步：1，对集合进行排序。2，进行剪枝，如果元素重复，直接跳过这一元素，决策树的这一枝被剪掉。

代码：


class Solution:
    # @param num, a list of integer
    # @return a list of lists of integers
    def permuteUnique(self, num):
        length = len(num)
        if length == 0: return []
        if length == 1: return [num]
        num.sort()
        res = []
        previousNum = None
        for i in range(length):
            if num[i] == previousNum: continue
            previousNum = num[i]
            for j in self.permuteUnique(num[:i] + num[i+1:]):
                res.append([num[i]] + j)
        return res




public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length==0)
            return res;
        Arrays.sort(num);           //先将数组排序 使重复元素相邻
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, boolean[] used, List<Integer> item, List<List<Integer>> res) {
        if(item.size()==num.length)
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            if(i>0 && !used[i-1] && num[i]==num[i-1])        //这里加判断 如果相邻元素相同 且前一个元素没有被用过 说明是重复结果 不再继续递归
                continue;
            if(!used[i])
            {
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size()-1);
                used[i] = false;
            }
        }
    } 
}

Note: 这题跟上一题基本一样 只有两个地方要改一个是num先要排序 一个是要加个判断条件 这个题其实是上一题的一般情况 面试可以直接写这个

一般都要求有重复元素的







from code ganker:

这个题跟Permutations非常类似，唯一的区别就是在这个题目中元素集合可以出现重复。这给我们带来一个问题就是如果不对重复元素加以区别，

那么类似于{1,1,2}这样的例子我们会有重复结果出现。那么如何避免这种重复呢？方法就是对于重复的元素循环时跳过递归函数的调用，

只对第一个未被使用的进行递归，我们那么这一次结果会出现在第一个的递归函数结果中，而后面重复的会被略过。如果第一个重复元素前面的元素还没在当前结果中，

那么我们不需要进行递归。想明白了这一点，代码其实很好修改。首先我们要对元素集合排序，从而让重复元素相邻，

接下来就是一行代码对于重复元素和前面元素使用情况的判断即可。代码如下： 

public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num==null && num.length==0)
        return res;
    Arrays.sort(num);
    helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
    return res;
}
private void helper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
{
    if(item.size() == num.length)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=0;i<num.length;i++)
    {
        if(i>0 && !used[i-1] && num[i]==num[i-1]) continue;
        if(!used[i])
        {
            used[i] = true;
            item.add(num[i]);
            helper(num, used, item, res);
            item.remove(item.size()-1);
            used[i] = false;
        }
    }
}

这样的解法是带有一般性的，把这个代码放到Permutations中也是正确的，所以如果熟悉的话，面试时如果碰到这个题目建议直接实现这个代码，

不要假设元素没有重复，当然可以跟面试官讨论，不过一般来说都是要考虑这个情况的哈。
