public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        int from = 0, to = 0;
        while(to<nums.length) {
            to++;
            while(to<nums.length && nums[to]-nums[to-1]==1)
                to++;
            res.add(getRange(nums[from], nums[to-1]));
            from = to;
        }
        return res;
    }
    
    private String getRange(int from , int to) {
        return (from==to)?String.valueOf(from):String.valueOf(from)+"->"+String.valueOf(to);
    }
}

O(n) O(1)