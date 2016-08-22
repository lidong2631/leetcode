public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(k, n, new ArrayList<Integer>(), 1, res);
        return res;
    }
    
    private void helper(int k, int n, List<Integer> list, int index, List<List<Integer>> res) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (k == 0 || n == 0) return;
        for (int i = index; i <= 9; i++) {
            list.add(i);
            helper(k - 1, n - i, list, i + 1, res);
            list.remove(list.size()-1);
        }
    }
}