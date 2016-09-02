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



Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and 
each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]