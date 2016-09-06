/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";            // careful
        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        sb.append(root.val);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            sb.append(",");
            if (curr.left != null) {
                sb.append(curr.left.val);
                queue.add(curr.left);
            }
            else sb.append("#");
            sb.append(",");
            
            if (curr.right != null) {
                sb.append(curr.right.val);
                queue.add(curr.right);
            }
            else sb.append("#");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;    // careful
        String[] str = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        int i = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (!str[i].equals("#")) {
                curr.left = new TreeNode(Integer.parseInt(str[i]));
                queue.add(curr.left);
            }
            else curr.left = null;
            i++;
            
            if (!str[i].equals("#")) {
                curr.right = new TreeNode(Integer.parseInt(str[i]));
                queue.add(curr.right);
            }
            else curr.right = null;
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));





Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 

or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. 

You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5

as "1,2,3,#,#,4,5", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, 

so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.