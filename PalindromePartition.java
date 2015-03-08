<<<<<<< HEAD
public class PalindromePartition {
    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        pp.minCut("a");
    }

    public int minCut(String s) {
        if(s == null || s.length()==0)
            return 0;
        boolean[][] dict = getDict(s);
        int[] res = new int[s.length()+1];
        res[0] = 0;
        for(int i=0;i<s.length();i++)
        {
            res[i+1] = i+1;
            System.out.println("before res[" + i + "+1] = " + res[i+1]);
            for(int j=0;j<=i;j++)
            {
                if(dict[j][i])
                {
                    System.out.println("before math.min res[i+1] = " + res[i+1]);
                    System.out.println("before math.min res[j] = " + res[j]);
                    res[i+1] = Math.min(res[i+1],res[j]+1);
                    System.out.println("after res[i+1] = " + res[i+1]);
                }
            }
        }
        System.out.println(res[s.length()]-1);
        return res[s.length()]-1;
    }
    
    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for(int i=s.length()-1;i>=0;i--)
        {
            for(int j=i;j<s.length();j++)
            {
                if(s.charAt(i)==s.charAt(j) && (j-i<2 || dict[i+1][j-1]))
                    dict[i][j] = true;
            }
        }
        return dict;
    }
=======
public class PalindromePartition {
    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        pp.minCut("a");
    }

    public int minCut(String s) {
        if(s == null || s.length()==0)
            return 0;
        boolean[][] dict = getDict(s);
        int[] res = new int[s.length()+1];
        res[0] = 0;
        for(int i=0;i<s.length();i++)
        {
            res[i+1] = i+1;
            System.out.println("before res[" + i + "+1] = " + res[i+1]);
            for(int j=0;j<=i;j++)
            {
                if(dict[j][i])
                {
                    System.out.println("before math.min res[i+1] = " + res[i+1]);
                    System.out.println("before math.min res[j] = " + res[j]);
                    res[i+1] = Math.min(res[i+1],res[j]+1);
                    System.out.println("after res[i+1] = " + res[i+1]);
                }
            }
        }
        System.out.println(res[s.length()]-1);
        return res[s.length()]-1;
    }
    
    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for(int i=s.length()-1;i>=0;i--)
        {
            for(int j=i;j<s.length();j++)
            {
                if(s.charAt(i)==s.charAt(j) && (j-i<2 || dict[i+1][j-1]))
                    dict[i][j] = true;
            }
        }
        return dict;
    }
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
}