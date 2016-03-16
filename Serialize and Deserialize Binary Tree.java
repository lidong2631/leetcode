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
        // write your code here
        StringBuffer res = new StringBuffer();
        if (root == null) return res.toString();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        res.append(root.val);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            res.append(",");

            if (cur.left != null) {                     // left child
                res.append(cur.left.val);
                queue.offer(cur.left);
            }
            else res.append("#");
            res.append(",");
            
            if (cur.right != null) {                    // right child
                res.append(cur.right.val);
                queue.offer(cur.right);
            }
            else res.append("#");
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // write your code here
        if (data==null || data.length()==0) return null;
        String[] arr = data.split(",");
        int len = arr.length;
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int count = 1;

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            String left="", right="";
            if (count < len) {                              // if we reach the bottom
                left = arr[count++];
                if (!left.equals("#")) {                    // if curr node's left child is null
                    cur.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(cur.left);
                }
                else cur.left = null;
            }
            else cur.left = null;
            
            if (count < len) {
                right = arr[count++];
                if (!right.equals("#")) {
                    cur.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(cur.right);
                }
                else cur.right = null;
            }
            else cur.right = null;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));