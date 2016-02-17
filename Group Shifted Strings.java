public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String str : strings) {
            int offset = str.charAt(0)-'a';         // set up standard
            String tmp = "";
            for(int i=0; i<str.length(); i++) {
                char c = (char)(str.charAt(i)-offset);  // every character minus offset
                if(c<'a')                           // ex "ba"
                    c+=26;                          // add by 26
                tmp+=c;
            }
            if(!map.containsKey(tmp)) {
                List<String> item = new ArrayList<String>();
                map.put(tmp, item);
            }
            map.get(tmp).add(str);
        }
        for(List<String> list : map.values()) {
            Collections.sort(list);
            res.add(list);
        }
        return res;
    }
}


https://leetcode.com/discuss/50358/my-concise-java-solution