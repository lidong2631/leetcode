public class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    private List<String> helper(int n, int m) {
        if(n==0)
            return new ArrayList<String>(Arrays.asList(""));
        if(n==1)
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        List<String> item = helper(n-2, m);
        List<String> res = new ArrayList<String>();
        for(int i=0; i<item.size(); i++) {
            if(n!=m)                            // eliminate invalid case like "0xx0"
                res.add("0"+item.get(i)+"0");
            res.add("1"+item.get(i)+"1");
            res.add("6"+item.get(i)+"9");
            res.add("8"+item.get(i)+"8");
            res.add("9"+item.get(i)+"6");
        }
        return res;
    }
}

https://leetcode.com/discuss/50412/ac-clean-java-solution