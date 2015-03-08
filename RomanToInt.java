<<<<<<< HEAD
import java.util.*;

public class RomanToInt {
    public static void main(String[] args) {
        RomanToInt rti = new RomanToInt();
        int num = rti.romanToInt("DCXII");
        System.out.println(num);
    }

    public int romanToInt(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
        
        char last = s.charAt(0);
        int num = dict.get(last);
        for(int i=1; i<s.length(); i++)
        {
            System.out.println(dict.get(s.charAt(i)) + " " + dict.get(last));
            if(dict.get(s.charAt(i))==dict.get(last))
                num+=dict.get(s.charAt(i));
            else if(dict.get(s.charAt(i))<dict.get(last))
            {
                num+=dict.get(s.charAt(i));
                last = s.charAt(i);
            }
            else
                num = num + dict.get(s.charAt(i)) - 2*dict.get(last);
        }
        return num;
    }
=======
import java.util.*;

public class RomanToInt {
    public static void main(String[] args) {
        RomanToInt rti = new RomanToInt();
        int num = rti.romanToInt("DCXII");
        System.out.println(num);
    }

    public int romanToInt(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
        
        char last = s.charAt(0);
        int num = dict.get(last);
        for(int i=1; i<s.length(); i++)
        {
            System.out.println(dict.get(s.charAt(i)) + " " + dict.get(last));
            if(dict.get(s.charAt(i))==dict.get(last))
                num+=dict.get(s.charAt(i));
            else if(dict.get(s.charAt(i))<dict.get(last))
            {
                num+=dict.get(s.charAt(i));
                last = s.charAt(i);
            }
            else
                num = num + dict.get(s.charAt(i)) - 2*dict.get(last);
        }
        return num;
    }
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
}