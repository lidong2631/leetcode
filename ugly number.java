public class Solution {
    public boolean isUgly(int num) {
        for(int i=2; i<6 && num>0; i++) {
            while(num%i==0)
                num/=i;
        }
        return num==1;
    }
}

https://leetcode.com/discuss/52703/2-4-lines-every-language
https://leetcode.com/discuss/52704/share-my-simple-java-solution