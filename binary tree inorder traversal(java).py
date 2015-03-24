<<<<<<< HEAD
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def iterativeInorder(self, root, list):
        stack = []
        while root or stack:            #只要栈里还有元素或root不为空 就可以继续循环下去
            if root:                    #如果root存在 就push root进栈 去其左节点
                stack.append(root)
                root = root.left
            else:                       #如果root为空 说明已经到底没有左节点了 这时我们pop栈顶的元素出来把它的值加进list里
                root = stack.pop()      #并去它的右子节点 如果它没有右节点继续pop元素 否则重复if root部分
                list.append(root.val)
                root = root.right
    
    # @param root, a tree node
    # @return a list of integers
    def inorderTraversal(self, root):
        list = []
        self.iterativeInorder(root, list)
        return list





题意：二叉树的中序遍历。这道题用递归比较简单，考察的是非递归实现二叉树中序遍历。中序遍历顺序为：左子树，根，右子树。如此递归下去。

解题思路：假设树为：

　　　　　　　　　　　　　　　　1

　　　　　　　　　　　　　　　/　  \

　　　　　　　　　　　　　　 2　　  3

　　　　　　　　　　　　　  /   \　 /   \

   　　　　　　　　　　　　4     5  6    7

我们使用一个栈来解决问题。步骤如下：

　　　　一，我们将根节点1入栈，如果有左孩子，依次入栈，那么入栈顺序为：1，2，4。由于4的左子树为空，停止入栈，此时栈为{1，2，4}。

　　　　二，此时将4出栈，并遍历4，由于4也没有右孩子，那么根据中序遍历的规则，我们显然应该继续遍历4的父亲2，情况是这样。所以我们继续将2出栈并遍历2，2存在右孩子，将5入栈，此时栈为{1，5}。

　　　　三，5没有孩子，则将5出栈并遍历5，这也符合中序遍历的规则。此时栈为{1}。

　　　　四，1有右孩子，则将1出栈并遍历1，然后将右孩子3入栈，并继续以上三个步骤即可。

　　　　栈的变化过程：{1}->{1,2}->{1,2,4}->{1,2}->{1}->{1,5}->{1}->{}->{3}->{3,6}->{3}->{}->{7}->{}。

代码：


# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def iterative_inorder(self, root, list):
        stack = []
        while root or stack:
            if root:
                stack.append(root)
                root = root.left
            else:
                root = stack.pop()
                list.append(root.val)
                root = root.right
        return list
                
    def recursive_inorder(self, root, list):
        if root:
            self.inorder(root.left, list)
            list.append(root.val)
            self.inorder(root.right, list)
        
    def inorderTraversal(self, root):
        list = []
        self.iterative_inorder(root, list)
        return list




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
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode curr = root;
        TreeNode pre = null;
        List<Integer> res = new ArrayList<Integer>();
        while(curr!=null) {
            if(curr.left!=null) {
                pre = curr.left;
                while(pre.right!=null && pre.right!=curr)
                    pre = pre.right;
                if(pre.right==null) {
                    pre.right = curr;
                    curr = curr.left;
                }
                else {
                    pre.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
            else {
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }
}

Morris遍历 常熟空间复杂度 时间复杂度是O(2n) = O(n) 利用叶子节点的空孩子指针做成线索 实现向上走的功能

用线索二叉树实现

这题树的中序遍历要会三种方式 递归 栈＋迭代 Morris遍历

如果用常量空间来中序遍历一颗二叉树。这种方法叫 Morris Traversal。想用O(1)空间进行遍历，因为不能用栈作为辅助空间来保存付节点的信息，重点在于当访问到子节点的时候如何重新回到父节点（当然这里是指没有父节点指针，如果有其实就比较好办，一直找遍历的后驱结点即可）。Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
算法具体分情况如下：
1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
 a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
 b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。 

http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html






Recursion: 时间O(n) 空间O(logn) 递归栈大小

public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    helper(root, res);
    return res;
}

private void helper(TreeNode root, ArrayList<Integer> res) {
    if(root==null)
        return;
    helper(root.left, res);
    res.add(root.val);
    helper(root.right, res);
}


Iteration: 时间空间跟递归一样    Morris遍历有时间看

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
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        while(!stack.isEmpty() || root!=null)
        {
            if(root!=null)
            {
                stack.push(root);
                root = root.left;
            }
            else
            {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}