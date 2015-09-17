public class Solution {
    public int calculate(String s) {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i)))  //if the char is a number 
                num=num*10+s.charAt(i)-'0';
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i)!=' ') || i==(s.length()-1)) {    //运算符
                if(sign=='+')
                    stack.push(num);
                if(sign=='-')
                    stack.push(-num);
                if(sign=='*')
                    stack.push(stack.pop()*num);
                if(sign=='/')
                    stack.push(stack.pop()/num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for(int i:stack)
            res+=i;
        return res;
    }
}
O(n) O(n)

https://leetcode.com/discuss/41902/share-my-java-solution