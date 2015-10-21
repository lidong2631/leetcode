public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if(s==null || s.length()<2)
            return res;
        for(int i=0; i<s.length(); i++) {
            if(s.startsWith("++", i))
                res.add(s.substring(0,i)+"--"+s.substring(i+2));
        }
        return res;
    }
}

O(n^2) O(1)

https://leetcode.com/discuss/64268/share-my-java-solution