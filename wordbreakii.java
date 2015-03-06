import java.util.*;

public class wordbreakii {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "a";
        Set<String> dict = new HashSet<String>();
        dict.add("a");
        if(dict.contains("a"))
            System.out.println("Yes");
        List<String> res = new ArrayList<String>();
        res = s.wordBreak(str, dict);
        System.out.println(res.get(0));
    }
}