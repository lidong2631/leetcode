public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new LinkedList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strings.length; i++) {
            int offset = strings[i].charAt(0) - 'a';
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < strings[i].length(); j++) {
                char curr = (char)(strings[i].charAt(j) - offset);
                if (curr < 'a') curr += 26;
                sb.append(curr);
            }
            String str = sb.toString();
            if (!map.containsKey(str)) map.put(str, new LinkedList<String>());
            map.get(str).add(strings[i]);
        }
        for (List<String> l : map.values()) {
            Collections.sort(l);
            res.add(l);
        }
        return res;
    }
}


https://leetcode.com/discuss/50358/my-concise-java-solution