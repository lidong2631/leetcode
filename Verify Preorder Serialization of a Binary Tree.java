public class Solution {
    public boolean isValidSerialization(String preorder) {
        if(preorder==null || preorder.length()==0)
            return true;
        String[] str = preorder.split(",");
        int i = 0, level = 0;
        while(i<str.length-1) {
            if(str[i++].equals("#")) {
                if(level==0) return false;
                else level--;
            }
            else level++;
        }
        if(level!=0) return false;
        return str[str.length-1].equals("#");
    }
}

O(n)

Use iterative preorder traversal, actually no need to use stack, just a integer to track the depth of the stack

https://leetcode.com/discuss/83809/simple-o-n-solution

https://leetcode.com/discuss/83824/7-lines-easy-java-solution