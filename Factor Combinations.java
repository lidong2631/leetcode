public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(n, 2, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int n, int fac, List<Integer> list, List<List<Integer>> res) {
        if (n == 1) {
            if (list.size() > 1) res.add(new ArrayList<Integer>(list)); // careful need to check list.size() > 1
            return;
        }
        for (int i = fac; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                helper(n/i, i, list, res);  // careful it is i
                list.remove(list.size()-1);
            }
        }
    }
}

backtrack典型问题 跟combination sum类似

https://leetcode.com/discuss/51250/my-recursive-dfs-java-solution



Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]