class Solution {
    /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        // write your code here
        if(n==1)
            return 0;
        if(n==2)
            return 1;
        int a = 0, b = 1;
        for(int i=3; i<=n; i++) {
            int tmp = a+b;
            a = b;
            b = tmp;
        }
        return b;
    }
}



递归解 超时

class Solution {
    /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        // write your code here
        if(n==1)
            return 0;
        if(n==2)
            return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
