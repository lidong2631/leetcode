A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]



Java:
public class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    private List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));    // careful need to add an empty string
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (n != m)
                res.add("0" + list.get(i) + "0");
            res.add("1" + list.get(i) + "1");
            res.add("6" + list.get(i) + "9");
            res.add("9" + list.get(i) + "6");
            res.add("8" + list.get(i) + "8");
        }
        return res;
    }
}

https://leetcode.com/discuss/50412/ac-clean-java-solution