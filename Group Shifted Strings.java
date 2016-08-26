public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            int offset = strings[i].charAt(0) - 'a';
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < strings[i].length(); j++) {
                char tmp = (char)(strings[i].charAt(j) - offset);
                if (tmp < 'a') tmp += 26;
                sb.append(tmp);
            }
            String str = sb.toString();
            if (map.containsKey(str)) map.get(str).add(strings[i]);
            else {
                List<String> list = new ArrayList<>();
                list.add(strings[i]);
                map.put(str, list);
            }
        }
        for (List<String> l : map.values()) {
            Collections.sort(l);
            res.add(l);
        }
        return res;
    }
}


https://leetcode.com/discuss/50358/my-concise-java-solution