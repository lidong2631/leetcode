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