import java.math.BigInteger;

public class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for(int i=1; i<=n/2; i++) { //first number cannot larger than half of the string
            if(num.charAt(0)=='0' && i>1)
                return false;
            BigInteger num1 = new BigInteger(num.substring(0,i));   //get first number
            for(int j=1; Math.max(i,j)<=n-i-j; j++) {
                if(num.charAt(i)=='0' && j>1)
                    break;
                BigInteger num2 = new BigInteger(num.substring(i,i+j)); //get second number
                if(isValid(num1, num2, i+j, num))
                    return true;
            }
        }
        return false;
    }
    
    private boolean isValid(BigInteger num1, BigInteger num2, int start, String num) {
        if(start>=num.length())
            return true;
        num2 = num2.add(num1);
        num1 = num2.subtract(num1);
        String tmp = num2.toString();
        return num.startsWith(tmp, start) && isValid(num1, num2, start+tmp.length(), num);  //recursive check validity
    }
}



https://leetcode.com/discuss/70102/java-recursive-and-iterative-solutions