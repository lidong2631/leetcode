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
