public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            sb.append(str.length() + "/" + str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        int i = 0;
        List<String> res = new ArrayList<>();
        while (i < s.length()) {
            int slash = s.indexOf("/", i);  // careful s.indexOf("/", i)
            int len = Integer.parseInt(s.substring(i, slash));
            String str = s.substring(slash+1, slash+1+len);
            i = slash + 1 + len;
            res.add(str);
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));


https://leetcode.com/discuss/55020/ac-java-solution