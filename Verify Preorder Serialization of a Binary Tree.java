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

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false


O(n)

Use iterative preorder traversal, actually no need to use stack, just a integer to track the depth of the stack

https://leetcode.com/discuss/83809/simple-o-n-solution

https://leetcode.com/discuss/83824/7-lines-easy-java-solution