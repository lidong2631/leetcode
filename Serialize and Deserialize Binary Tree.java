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
            if (cur.left != null)   queue.offer(cur.left); //add children to the queue
            if (cur.right != null)  queue.offer(cur.right);
            res.append(",");
            if (cur.left != null) {
                res.append(cur.left.val);
            }
            else res.append("#");
            res.append(",");
            if (cur.right != null) {
                res.append(cur.right.val);
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
            if (count < len) {
                left = arr[count++];
                if (!left.equals("#")) {
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