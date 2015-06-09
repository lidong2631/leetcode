public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<=0)
            return res;
        int[] num = {1,2,3,4,5,6,7,8,9};
        helper(num, k, 0, n, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, int k, int start, int target, List<Integer> item, List<List<Integer>> res) {
        if(target==0 && item.size()==k) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        if(target<0 || item.size()>k)
            return;
        for(int i=start; i<num.length; i++) {
            item.add(num[i]);
            helper(num, k, i+1, target-num[i], item, res);
            item.remove(item.size()-1);
        }
    }
}

跟combination sum i ii类似 只是这题限定了取值范围