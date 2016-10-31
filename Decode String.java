Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is 

guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, 

there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".



public class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<String> resStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {   // accumulate number and push to numStack
                int tmp = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    tmp = 10*tmp + s.charAt(i) - '0';
                    i++;
                }
                numStack.push(tmp);
            }
            else if (s.charAt(i) == '[') {  // left bracket
                resStack.push(res);
                res = "";
                i++;
            }
            else if (s.charAt(i) == ']') {  // right bracket
                StringBuffer sb = new StringBuffer(resStack.pop());     // careful
                int n = numStack.pop();
                for (int j = 0; j < n; j++) {
                    sb.append(res);
                }
                res = sb.toString();
                i++;
            }
            else {          // String accumulate each character
                res += s.charAt(i++);
            }
        }
        return res;
    }
}


https://discuss.leetcode.com/topic/57159/simple-java-solution-using-stack

using regex cool!
https://discuss.leetcode.com/topic/57145/3-lines-python-2-lines-ruby-regular-expression