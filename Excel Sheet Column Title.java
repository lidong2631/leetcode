Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"




Java:
public class Solution {
    public String convertToTitle(int n) {
        StringBuffer res = new StringBuffer();
        while (n > 0) {
            res.append((char)('A' + (n - 1) % 26));     // careful it is n - 1
            n = (n - 1) / 26;                           // careful it is n - 1
        }
        return res.reverse().toString();
    }
}


Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 



进制转换的题目 注意这里将n－1后做计算处理起来会更方便
