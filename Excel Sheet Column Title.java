public class Solution {
    public String convertToTitle(int n) {
        String str = "";
        while(n>0) {
            int diff = (n-1)%26;
            str = (char)('A'+diff) + str;
            n = (n-1)/26;
        }
        return str;
    }
}

进制转换的题目 注意这里将n－1后做计算处理起来会更方便


public class Solution {
    public String convertToTitle(int n) {
        if(n<1)
            return "";
        StringBuilder res = new StringBuilder();
        while(n>0) {
            int diff = (n-1)%26;
            res.append((char)('A'+diff));
            n = (n-1)/26;
        }
        return res.reverse().toString();
    }
}

用StringBuilder 注意最后要reverse 时间O(n) 空间O(1)