public class Solution {
    public int rob(int[] num) {
        if (num == null || num.length == 0) return 0;
        if (num.length == 1) return num[0];
        int prevRob = 0, currRob = num[0];
        for (int i = 1; i < num.length; i++) {
            int tmp = currRob;
            currRob = Math.max(currRob, prevRob + num[i]);
            prevRob = tmp;
        }
        return currRob;
    }
}

O(n) O(1)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 

the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 

it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money 

you can rob tonight without alerting the police.


2 - 10 - 5 - 3 - 8