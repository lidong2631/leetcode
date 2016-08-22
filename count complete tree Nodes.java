/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        while (root != null) {
            int llh = getLeftHeight(root.left);
            int lrh = getRightHeight(root.left);
            int rrh = getRightHeight(root.right);
            if (llh == rrh) {
                count += (1 << (llh + 1)) - 1;
                return count;
            }
            else if (llh > lrh) {
                count += 1 << rrh;
                root = root.left;
            }
            else {
                count += 1 << lrh;
                root = root.right;
            }
        }
        return count;
    }
    
    private int getLeftHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            root = root.left;
            height++;
        }
        return height;
    }
    
    private int getRightHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            root = root.right;
            height++;
        }
        return height;
    }
}

O((logn)^2) O(logn)

https://leetcode.com/discuss/38919/concise-java-iterative-solution-o-logn-2

In each loop, if the the total tree is complete, we add all the nodes and stop. If the left subtree is incomplete, 

add all nodes in the right subtree (plus the parent node) and let the left subtree be the new tree. If the left subtree is complete, 

add all nodes in the left subtree (plus the parent node) and let the right subtree be the new tree



public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int llh = getHeight(root.left, (r -> r.left));
        int rlh = getHeight(root.right, (r -> r.left));
        int rrh = getHeight(root.right, (r -> r.right));
        if (llh == rrh) {
            return (1 << (llh + 1)) - 1;
        } else if (rlh > rrh) {
            return countNodes(root.right) + (1 << llh);
        } else if (llh > rlh) {
            return countNodes(root.left) + (1 << rlh);
        } else {
            throw new IllegalArgumentException("non complete tree");
        }
    }

    private int getHeight(TreeNode root, UnaryOperator<TreeNode> nextNode) {
        return root==null?0:1+getHeight(nextNode.apply(root),nextNode);
    }   
}

用java lambda表达式


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = 1, currLevelNum = 1, nextLevelNum = 0;
        while(!queue.isEmpty()) {
            TreeNode p = queue.poll();
            currLevelNum--;
            if(p.left!=null) {
                count++;
                nextLevelNum++;
                queue.add(p.left);
            }
            if(p.right!=null) {
                count++;
                nextLevelNum++;
                queue.add(p.right);
            }
            if(currLevelNum==0) {
                currLevelNum = nextLevelNum;
                nextLevelNum = 0;
            }
        }
        return count;
    }
}

层序遍历 超时