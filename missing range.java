public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(getRange(lower, upper));
            return res;
        } 
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i == nums.length) ? upper + 1 : nums[i];
            if (curr - prev >= 2) res.add(getRange(prev+1, curr-1));
            prev = curr;
        }
        return res;
    }
    
    private String getRange(int l, int r) {
        return (l == r) ? String.valueOf(l) : String.valueOf(l) + "->" + String.valueOf(r);
    }
}

O(n)