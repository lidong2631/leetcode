public class Solution {
    public int trailingZeroes(int n) {
        if(n<=0)
            return 0;
        int count = 0;
        for(int i=5; n/i>0; i*=5) {
            count+=n/i;
        }
        return count;
    }
}

注意上面的解法不对 when n=1808548329, there should be 452137076 tailing zeros, however it gives 452137080

也就是说它没有考虑到integer overflow的情况 所以要用下面的解法 用除法而不是乘法

public class Solution {
    public int trailingZeroes(int n) {
        if(n<=0)
            return 0;
        int count = 0;
        while(n>0) {
            count+=n/5;
            n/=5;
        }
        return count;
    }
}

此解法来自geekforgeek 时间O(logn) 空间O(1) 这个题在cc150和programmerInterview中都有 不过解法也是用的第一个解