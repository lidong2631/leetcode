public class Solution {
    public String toHex(int num) {
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        
        if (num == 0) return "0";
        String res = "";
        while (num != 0) {
            res = map[num & 15] + res;
            num >>>= 4;     // careful cannot use ">>=" will get "-1" time limit exceed because ">>" will put "1111" to leftmost bits
        }
        return res;
    }
}



https://discuss.leetcode.com/topic/60365/simple-java-solution-with-comment
Two Complement
-1 = 11111111111111111111111111111111 (32 '1')
http://baike.baidu.com/view/1809209.htm
">>" ">>>"
The unsigned right shift operator ">>>" shifts a zero into the leftmost position, while the leftmost position 
after ">>" depends on sign extension
There is no corresponding << operator because left-shift operations on signed and unsigned data types are identical



Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"