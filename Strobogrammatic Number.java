A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false



Java:
public boolean isStrobogrammatic(String num) {
    for (int i=0, j=num.length()-1; i <= j; i++, j--)
        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
            return false;
    return true;
}

O(n) O(1)

https://leetcode.com/discuss/50594/4-lines-in-java


my solution

public class Solution {
    public boolean isStrobogrammatic(String num) {
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if ((num.charAt(left) == '0' && num.charAt(right) == '0') ||
                (num.charAt(left) == '1' && num.charAt(right) == '1') ||
                (num.charAt(left) == '8' && num.charAt(right) == '8') ||
                (num.charAt(left) == '6' && num.charAt(right) == '9') ||
                (num.charAt(left) == '9' && num.charAt(right) == '6')) {
                    left++;
                    right--;
                }
            else
                return false;
        }
        return true;
    }
}