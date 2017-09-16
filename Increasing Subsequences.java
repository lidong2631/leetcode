Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.


class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        List<Integer> tmp = new ArrayList<>();
        dfs(res, tmp, 0, nums);
        List result = new ArrayList(res);
        return result;
    }
    
    private void dfs(Set<List<Integer>> res, List<Integer> tmp, int index, int[] nums) {
        if (tmp.size() >= 2) {
            res.add(new ArrayList<Integer>(tmp));
        }
        for (int i = index; i < nums.length; i++) {
            if (tmp.size() == 0 || tmp.get(tmp.size()-1) <= nums[i]) {
                tmp.add(nums[i]);
                dfs(res, tmp, i+1, nums);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}

backtracking