public class Solution {
    public int strobogrammaticInRange(String low, String high) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('1','1');
        map.put('6','9');
        map.put('9','6');
        map.put('8','8');
        map.put('0','0');
        int res = 0;
        for(int i=low.length(); i<=high.length(); i++) {
            int[] count = new int[1];
            helper(map, low, high, count, new char[i], 0, i-1);
            res+=count[0];
        }
        return res;
    }
    
    private void helper(Map<Character,Character> map, String low, String high, int[] count, char[] charArr, int l, int h) {
        if(l>h) {
            String str = new String(charArr);
            if((charArr[0]!='0' || charArr.length==1) && 
                ((str.length()==low.length()?str.compareTo(low)>=0:str.length()>low.length()) && 
                (str.length()==high.length()?str.compareTo(high)<=0:str.length()<high.length()))) {
                count[0]++;
            }
            return;
        }
        for(Character c : map.keySet()) {
            charArr[l] = c;
            charArr[h] = map.get(c);
            if((l==h && c==map.get(c)) || l<h)
                helper(map, low, high, count, charArr, l+1, h-1);
        }
    }
}

类似于NP问题的套路
https://leetcode.com/discuss/50624/clean-and-easy-understanding-java-solution