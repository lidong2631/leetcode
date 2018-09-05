Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]



Python:
class Solution:
    def subsets(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        res = []
        nums.sort()
        self.backtrack(res, [], nums, 0)
        return res
    
    def backtrack(self, res, tmp, nums, start):
        res.append(tmp[:])
        for i in range(start, len(nums)):
            tmp.append(nums[i])
            self.backtrack(res, tmp, nums, i+1)
            tmp.pop()
            


Java:
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(res, new ArrayList<>(), nums, 0);
    return res;
}

private void backtrack(List<List<Integer>> res, List<Integer> tmp, int [] nums, int start){
    res.add(new ArrayList<>(tmp));
    for (int i = start; i < nums.length; i++){
        tmp.add(nums[i]);
        backtrack(res, tmp, nums, i + 1);
        tmp.remove(tmp.size() - 1);
    }
}


general approach to backtracking (Subsets, Permutations, Combination Sum, Palindrome Partitionin)
https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)





Golang:
func subsets(nums []int) [][]int {
    var res [][]int
    sort.Ints(nums)
    backtrack(&res, &[]int{}, nums, 0)
    return res
}

func backtrack(res *[][]int, tmp *[]int, nums []int, start int) {
    l := make([]int, len(*tmp))
    copy(l, *tmp)
    *res = append(*res, l)
    for i := start; i < len(nums); i++ {
        *tmp = append(*tmp, nums[i])
        backtrack(res, tmp, nums, i+1)
        *tmp = (*tmp)[:len(*tmp)-1]
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