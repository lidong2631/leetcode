public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        while (!set.contains(n) && n != 1) {
            set.add(n);
            int k = 1;
            while (n / k >= 10) {
                k *= 10;
            }
            while (n != 0) {
                int digit = n / k;
                res += Math.pow(digit, 2);
                n = n % k;
                k /= 10;
            }
            n = res;
            res = 0;
        }
        return (n == 1) ? true : false;
    }
}