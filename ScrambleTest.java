import java.util.*;

public class ScrambleTest {
	public static void main(String[] args) {
		ScrambleString ss = new ScrambleString();
		String s1 = "abb";
		String s2 = "bba";
		if(ss.isScramble(s1, s2))
			System.out.println("True");
		else
			System.out.println("False");
		//char[] c1 = {'a', 'b'};
		//char[] c2 = {'b', 'a'};
		//System.out.println(String.valueOf(c1));
		//System.out.println(String.valueOf(c2));
		//if(String.valueOf(c1)==String.valueOf(c2))
			//System.out.println("c1 equals c2");
	}
}