# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrderBottomTraversal(self, root, level, res):
        if root:
            if len(res) < level + 1:
                res.append([])
            res[level].append(root.val)
            self.levelOrderBottomTraversal(root.left, level+1, res)
            self.levelOrderBottomTraversal(root.right, level+1, res)
    
    
    # @param root, a tree node
    # @return a list of lists of integers
    def levelOrderBottom(self, root):
        res = []
        self.levelOrderBottomTraversal(root, 0, res)
        res.reverse()                   #only one line difference
        return res

Note: 这题只需要在i的基础上加一句res.reverse()倒序就好了






/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, 0, res);
        Collections.reverse(res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if(root!=null)
        {
            if(res.size()<level+1)
            {
                List<Integer> tmpList = new ArrayList<Integer>();
                res.add(new ArrayList(tmpList));
            }
            res.get(level).add(root.val);
            helper(root.left, level+1, res);
            helper(root.right, level+1, res);
        }
    }
}

Note： 这题只是在上一题的基础上加了个Collections.reverse(res);









/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currLevel = 1;
        int nextLevel = 0;
        List<Integer> tmpList = new ArrayList<Integer>();
        while(!queue.isEmpty())
        {
            currLevel--;
            TreeNode node = queue.poll();
            tmpList.add(node.val);
            if(node.left!=null)
            {
                nextLevel++;
                queue.offer(node.left);
            }
            if(node.right!=null)
            {
                nextLevel++;
                queue.offer(node.right);
            }
            if(currLevel==0)
            {
                currLevel = nextLevel;
                nextLevel = 0;
                res.add(tmpList);
                tmpList = new ArrayList<Integer>();
            }
        }
        Collections.reverse(res);
        return res;
    }
}



