public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(k, n, 1, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int k, int n, int index, List<Integer> list, List<List<Integer>> res) {
        if (n < 0) return;
        if (n == 0 && list.size() == k) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = index; i <= 9; i++) {
            list.add(i);
            helper(k, n-i, i+1, list, res);
            list.remove(list.size()-1);
        }
    }
}