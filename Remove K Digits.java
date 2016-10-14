public class Solution {
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";  // corner case 
        
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) { // if curr num is larger than prev
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