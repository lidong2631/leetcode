public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for(int p : preorder) {
            if(p<low)
                return false;
            while(!stack.empty() && p>stack.peek())
                low = stack.pop();
            stack.push(p);
        }
        return true;
    }
}

2 3 1 5 false

3 1 2 5
         3
       /   \
     1       5
      \
       2


Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?


public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for(int p : preorder) {
            if(p<low)
                return false;
            while(i>=0 && p>preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
}

https://leetcode.com/discuss/51543/java-o-n-and-o-1-extra-space