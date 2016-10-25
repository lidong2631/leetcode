public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int from = 0, to = 0;       // careful initialize to 0
        while (to < nums.length) {
            to++;               // every time add 1
            while (to < nums.length && nums[to] == nums[to-1] + 1) to++;
            res.add(range(nums[from], nums[to-1]));     // careful
            from = to;
        }
        return res;
    }
    
    private String range(int from, int to) {    
        return (from == to) ? String.valueOf(from) : String.valueOf(from) + "->" + String.valueOf(to);
    }
}

O(n) O(1)


Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].