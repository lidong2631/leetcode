You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.




Java:
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



2 - 10 - 5 - 3 - 8