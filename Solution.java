import java.util.*;

public class wordbreak {
    public List<String> wordBreak(String s, Set<String> dict) {
        if(s==null || s.length()==0) return null;
        ArrayList<String> res = new ArrayList<String>();
//System.out.println("the string is" + s);
        helper(s, dict, 0, "", res);
        return res;
    }
    
    private void helper(String s, Set<String> dict, int start, String tmpRes, ArrayList<String> res) {
        if(wordBreakI(s, dict))
        {
//System.out.println("wordbreak checks succesfully!");
            if(start>=s.length())
            {    
//System.out.println("finally tmpRes.substring(1)" + tmpRes.substring(1));
                res.add(tmpRes.substring(1));
                return;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<s.length()+1; i++)
            {
//System.out.println("Enter for i loop");
                sb.append(s.charAt(i-1));
//System.out.println("Stringbuilder is " + sb.toString());
                if(dict.contains(s.substring(0,i)))
                {
//System.out.println("dict contains this word");
                    String tmpNewRes = tmpRes + " " + sb.toString();
//System.out.println("tmpNewRes is" + tmpNewRes);
//System.out.println("s.substring(i) is" + s.substring(i));
                    helper(s, dict, start+1, tmpNewRes, res);
                }
            }
        }
    }
    
    public boolean wordBreakI(String s, Set<String> dict) {
        if(s==null || s.length()==0) return false;
        int strLen = s.length();
        boolean[] dp = new boolean[strLen+1];
        dp[0] = true;
        for(int i=1; i<strLen+1; i++)
        {
            for(int j=0; j<=i; j++)
            {
                if(dp[j] && dict.contains(s.substring(j, i)))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLen];
    }
}