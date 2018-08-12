Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]



Python:
class Solution:
    def permuteUnique(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        used = set()
        res = []
        nums.sort()
        self.helper(nums, used, [], res)
        return res
    
    def helper(self, nums, used, l, res):
        if len(l) == len(nums):
            res.append(l)
            return
        for i in range(len(nums)):
            if i > 0 and nums[i] == nums[i-1] and (i-1 not in used):
                continue
            if i not in used:
                used.add(i)
                self.helper(nums, used, l+[nums[i]], res)
                used.remove(i)




Java:
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        helper(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                helper(nums, used, list, res);
                list.remove(list.size()-1);
                used[i] = false;
            }
        }
    }
}





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
