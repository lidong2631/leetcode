public class Solution {
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";  // corner case 
        
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) { // if curr num is less than prev
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i++));
        }
        
        while (k > 0) {        // corner case "1111"
            stack.pop();
            k--;
        }
        
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();
        
        while (sb.length() > 1 && sb.charAt(0) == '0')  // remove leading 0
            sb.deleteCharAt(0);
        return sb.toString();
    }
}

O(n) O(n)

https://discuss.leetcode.com/topic/59646/straightforward-java-solution-using-stack



Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.