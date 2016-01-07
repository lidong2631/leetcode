public class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int num : nums) {
            diff^=num;
        }
        diff&=-diff; //diff&=~(diff-1) or diff = Integer.highestOneBit(diff);
        int[] res = new int[2];
        for(int num : nums) {
            if((num&diff)==0)
                res[0]^=num;
            else
                res[1]^=num;
        }
        return res;
    }
}

Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:

In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. Note that since the two numbers are distinct, 

so there must be a set bit (that is, the bit with value '1') in the XOR result. Find out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with the aforementinoed bit unset. 

Two different numbers we need to find must fall into thte two distrinct groups. XOR numbers in each group, we can find a number in either group.


A Corner Case:

When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min(). Therefore, the value of diff after executing diff &= -diff is 
still numeric_limits<int>::min(). The answer is still correct.


O(n) O(1)

https://leetcode.com/discuss/52351/accepted-java-space-easy-solution-with-detail-explanations
https://leetcode.com/discuss/52913/my-java-solution-adapted-from-the-commonest-solution-here