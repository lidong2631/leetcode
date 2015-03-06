import java.util.*;

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length())
        {
            return false;
        }
        if(s1.equals(s2))
            return true;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        String str1 = String.valueOf(c1);
        String str2 = String.valueOf(c2);

        if(!str1.equals(str2))
        {
            System.out.println(str1 + " not equal " + str2);
            return false;
        }
        int length = s1.length();
        for(int i=1; i<length; i++)
        {

System.out.println("first first: " + s1.substring(0,i) + " " + s2.substring(0,i) + "    first second: " + s1.substring(i) + " " + s2.substring(i));
System.out.println();

            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;

System.out.println("second first: " + s1.substring(0,i) + " " + s2.substring(i) + "     second second: " + s1.substring(i) + " " + s2.substring(0,i));
System.out.println();
            
            if(isScramble(s1.substring(0,i), s2.substring(i)) && isScramble(s1.substring(i), s2.substring(0,i)))
                return true;
        }
        return false;
    }
}