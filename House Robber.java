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

动态规划 知道某一点i的最大利润maxRob[i] 求下一点i+1 

递推式为maxRob[i+1] = Math.max(maxRob[i], maxRob[i-1]+num[i]) 即取上一次

rob的最大利润和上上次rob的最大利润+当前的钱数的最大值

时间O(n) 空间O(1)