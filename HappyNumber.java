public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        int res = 0;
        
        while(!set.contains(n) && n!=1) {
            set.add(n);
            int k = 1;
            while(n/k>=10)
                k*=10;
            while(n!=0) {
                res+=Math.pow(n/k, 2);
                n%=k;
                k/=10;
            }
            n = res;
            res = 0;
        }
        return n==1 ? true:false;
    }
}