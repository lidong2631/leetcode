public class Solution {
    public int rob(int[] num) {
        if(num==null || num.length==0)
            return 0;
        int[] maxRob = new int[2];
        maxRob[1] = num[0];
        for(int i=1; i<num.length; i++) {
            int tmp = maxRob[1];
            maxRob[1] = Math.max(maxRob[1], maxRob[0]+num[i]);
            maxRob[0] = tmp;
        }
        return maxRob[1];
    }
}

2 - 10 - 5 - 3 - 8

动态规划 知道某一点的最大利润maxRob[1] 求下一点 

递推式为maxRob[1] = Math.max(maxRob[1], maxRob[0]+num[i]) 即取上一次

rob的最大利润和上上次rob的最大利润+当前的钱数的最大值

时间O(n) 空间O(1)