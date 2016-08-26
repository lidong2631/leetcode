public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) map.put(c, map.get(c)+1);
            else map.put(c, 1);
        }
        int odd = 0;
        String mid = "";
        List<Character> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char k = entry.getKey();
            int v = entry.getValue();
            if (v % 2 == 1) {
                odd++;
                mid += k;
            }
            for (int i = 0; i < v / 2; i++) {
                list.add(k);
            }
        }
        if (odd > 1) return res;
        helper(list, mid, new StringBuffer(), new boolean[list.size()], res);
        return res;
    }
    
    private void helper(List<Character> list, String mid, StringBuffer sb, boolean[] used, List<String> res) {
        if (sb.length() == list.size()) {
            String str = sb.toString() + mid + sb.reverse().toString();
            res.add(str);
            sb.reverse();       // careful to reverse back
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i-1) && !used[i-1]) continue;  // careful for duplicate char
            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                helper(list, mid, sb, used, res);
                sb.deleteCharAt(sb.length()-1);     // careful StringBuffer does have remove() use deleteCharAt()
                used[i] = false;
            }
        }
    }
}

https://leetcode.com/discuss/53626/ac-java-solution-with-explanation