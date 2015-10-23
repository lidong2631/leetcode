public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(res, new ArrayList<Integer>(), 2, n);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> item, int start, int n) {
        if(n==1) {
            if(item.size()>1)   //判断item个数 等于1则只有自身为factor 大于1才加到res中
                res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start; i<=n; i++) {   //注意这里要写i<=n
            if(n%i==0) {
                item.add(i);
                helper(res, item, i, n/i);
                item.remove(item.size()-1);
            }
        }
    }
}

backtrack典型问题 跟combination sum类似

https://leetcode.com/discuss/51250/my-recursive-dfs-java-solution