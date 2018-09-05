Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]



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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, 0, res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root != null) {
            if (res.size() < level + 1) res.add(new ArrayList<Integer>());
            res.get(level).add(root.val);
            helper(root.left, level+1, res);
            helper(root.right, level+1, res);
        }
    }
}

like Binary Search Tree Preorder Traversal



Java:
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> item = new ArrayList<Integer>();
        queue.offer(root);
        int currLevel = 1, nextLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            currLevel--;
            item.add(curr.val);
            if (curr.left != null) {
                queue.offer(curr.left);
                nextLevel++;
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                nextLevel++;
            }
            if (currLevel == 0) {
                currLevel = nextLevel;
                nextLevel = 0;
                res.add(item);
                item = new ArrayList<Integer>();
            }
        }
        return res;
    }
}

这题要看code ganker的问题解答 解释了一些很基础的问题

时间O(n) 空间O(n)




from code ganker:

这道题要求实现树的层序遍历，其实本质就是把树看成一个有向图，然后进行一次广度优先搜索，这个图遍历算法是非常常见的，这里同样是维护一个队列，

只是对于每个结点我们知道它的邻接点只有可能是左孩子和右孩子，具体就不仔细介绍了。算法的复杂度是就结点的数量，O(n)，空间复杂度是一层的结点数，

也是O(n)。代码如下： 

public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(root == null)
        return res;
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int curNum = 0;
    int lastNum = 1;
    ArrayList<Integer> list = new ArrayList<Integer>();
    while(!queue.isEmpty())
    {
        TreeNode cur = queue.poll();
        lastNum--;
        list.add(cur.val);
        if(cur.left!=null)
        {
            queue.add(cur.left);
            curNum ++;
        }
        if(cur.right!=null)
        {
            queue.add(cur.right);
            curNum++;
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            res.add(list);
            list = new ArrayList<Integer>();
        }
    }
    return res;
}

层序遍历也是树的一种遍历方式，在某些题目中会比较实用，不过这样其实更接近于图的问题了，在有些树的题目中层序遍历提供了更好的方法，

所以还是得熟悉哈。这道题还有一个变体Binary Tree Zigzag Level Order Traversal，其实也是进行广度优先搜索，不过因为要求不同，

要换一种数据结构来记录层节点，有兴趣可以看看。

