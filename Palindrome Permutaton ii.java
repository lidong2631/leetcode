public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()==0)
            return res;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        List<Character> item = new ArrayList<Character>();
        String mid = "";
        int odd = 0;
        for(int i=0; i<s.length(); i++) {   //记录每个字符次数
            map.put(s.charAt(i), map.containsKey(s.charAt(i))?map.get(s.charAt(i))+1:1);
        }
        for(Map.Entry<Character, Integer> entry:map.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            if(count%2!=0) {    //记录奇数个字符的数量 并设置mid值
                odd++;
                mid+=key;
            }
            for(int j=0; j<count/2; j++)    //将一半的字符加入list中
                item.add(key);
        }
        if(odd>1)   //如果奇数字符数多于1个 无法得到palindrome
            return res;
        helper(item, mid, new boolean[s.length()], new StringBuilder(), res);   //完全permutation ii的套路
        return res;
    }
    
    private void helper(List<Character> item, String mid, boolean[] used, StringBuilder sb, List<String> res) {
        if(sb.length()==item.size()) {
            String str = sb.toString() + mid + sb.reverse().toString();
            res.add(str);
            sb.reverse();   //这里要再reverse sb 因为之前一句reverse了 要再reverse回来
            return;
        }
        
        for(int i=0; i<item.size(); i++) {
            if(i>0 && !used[i-1] && item.get(i)==item.get(i-1))
                continue;
            if(!used[i]) {
                used[i] = true;
                sb.append(item.get(i));
                helper(item, mid, used, sb, res);
                sb.deleteCharAt(sb.length()-1);
                used[i] = false;
            }
        }
    }
}

https://leetcode.com/discuss/53626/ac-java-solution-with-explanation