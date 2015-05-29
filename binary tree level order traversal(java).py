# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrderTraversal(self, root, level, res):
        if root:                            #当节点存在执行append和递归部分 否则直接结束本次调用
            if len(res) < level + 1:        #此条件判断当前是遍历该节点的左子树还是右子树 在遍历该节点左子树时 需要先每次建立对应的子list在res里以存放子树的数值
                res.append([])              #在遍历该节点右子树时 已经建立了对应list 直接append进去就好
            res[level].append(root.val)     #将root.val append进对应level的list中
            self.levelOrderTraversal(root.left, level+1, res)       #递归调用左右子树
            self.levelOrderTraversal(root.right, level+1, res)
    
    # @param root, a tree node
    # @return a list of lists of integers
    def levelOrder(self, root):
        res = []
        self.levelOrderTraversal(root, 0, res)
        return res





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
        if(root!=null) {
            if(res.size()<level+1) {    //如果是第一次到这一层 要新加一个list进res
                res.add(new ArrayList<Integer>());
            }
            res.get(level).add(root.val);
            helper(root.left, level+1, res);
            helper(root.right, level+1, res);
        }
    }
}

Note: python版改编 下面的code ganker的版本 python版用递归比较简练 要经常对比他们的写法不同

这题跟Populating Next Right Pointers in Each Node可以一起看都用到了层序遍历 要好好掌握




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
        if(root==null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); //java中实现Queue接口的类很多 用LinkedList就可以了
        List<Integer> item = new ArrayList<Integer>();
        queue.offer(root);
        int currLevel = 1, nextLevel = 0;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            currLevel--;
            item.add(curr.val);
            if(curr.left!=null) {
                queue.offer(curr.left);
                nextLevel++;
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
                nextLevel++;
            }
            if(currLevel==0) {
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

