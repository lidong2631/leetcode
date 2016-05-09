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
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i)!=' ') || i==(s.length()-1)) {    // if current characte is operator or we reach end
                if(sign=='+')
                    stack.push(num);
                if(sign=='-')
                    stack.push(-num);
                if(sign=='*')
                    stack.push(stack.pop()*num);
                if(sign=='/')
                    stack.push(stack.pop()/num);
                sign = s.charAt(i);             // each time set sign so next time when we reach 11 line if we can calculate result
                num = 0;
            }
        }
        int res = 0;
        for(int i:stack)        // finally pop all elements out of stack
            res+=i;
        return res;
    }
}
O(n) O(n)

3+2*2

https://leetcode.com/discuss/41902/share-my-java-solution