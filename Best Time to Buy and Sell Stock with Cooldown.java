public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int s2 = 0, s1 = 0, s0 = 0;
        int b0 = -prices[0], b1 = b0;           // careful
        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0; s2 = s1; s1 = s0;
        }
        return s0;  //we cannot end with a buy
    }
}

O(n) O(1)

Transition function:
buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);   
sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);


https://leetcode.com/discuss/71391/easiest-java-solution-with-explanations
https://leetcode.com/discuss/71354/share-my-thinking-process
https://leetcode.com/discuss/73617/7-line-java-only-consider-sell-and-cooldown
https://leetcode.com/discuss/72892/very-easy-to-understand-one-pass-solution-with-no-extra-space