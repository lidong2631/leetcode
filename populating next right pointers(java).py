# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

class Solution:
    # @param root, a tree node
    # @return nothing
    def connect(self, root):
        if root == None:                #如果root为空 直接返回
            return
        if root and root.left:          #如果root和其左节点都存在 将左节点指向右节点
            root.left.next = root.right
            if root.next:               #如果root存在next指针 将右节点指向root next节点的左节点
                root.right.next = root.next.left
            else:                       #否则设root右孩子next为空
                root.right.next = None
            self.connect(root.left)         #递归调用
            self.connect(root.right)






题意：

         1
       /  \
      2    3
     / \  / \
    4  5  6  7
变为：
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
解题思路：看到二叉树我们就想到需要使用递归的思路了。直接贴代码吧，思路不难。

代码：

复制代码
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

class Solution:
    # @param root, a tree node
    # @return nothing
    def connect(self, root):
        if root and root.left:
            root.left.next = root.right
            if root.next:
                root.right.next = root.next.left
            else:
                root.right.next = None
            self.connect(root.left)
            self.connect(root.right)






/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        int next = 0, curr = 1;
        TreeLinkNode prev = null;
        while(!queue.isEmpty()) {
            TreeLinkNode p = queue.poll();
            curr--;
            if(p.left!=null) {
                if(prev!=null)
                    prev.next = p.left;
                p.left.next = p.right;
                queue.add(p.left);
                queue.add(p.right);
                next+=2;
                prev = p.right;
            }
            if(curr==0) {
                p.next = null;
                curr = next;
                next = 0;
                prev = null;
            }
        }
    }
}

很简单 层序遍历






/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root!=null && root.left!=null)
        {
            root.left.next = root.right;
            if(root.next!=null)
                root.right.next = root.next.left;
            else
                root.right.next = null;
            connect(root.left);
            connect(root.right);
        }
    }
}

Note: 这题很简单 但要注意几个条件 1 初始所有节点next指针都set null 2 要求用常数空间复杂度 所以递归对这题可能不适用

code ganker没用递归 对于这题应该用他的解法 递归的写法很简单易懂

这题和ii本质上是树的层序遍历 算是树里的一类问题







/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        TreeLinkNode prevHead = root;     //prevHead记录上一级的头节点 即最左端节点  每次一层遍历结束会更新为currLeftMost 它只会沿着最左点一路向下移动
        TreeLinkNode currPtr = null;      //currPtr用来在每一层遍历所有节点 把next指针连接到各个点
        TreeLinkNode currLeftMost = null;   //currLeftMost为当前遍历层最左点
        while(prevHead!=null) //prevHead会沿着每一层最左点一路向下移动直到底
        {
            TreeLinkNode prevLevel = prevHead;    //prevLevel指向当前遍历层的上一层 用来指向左右孩子节点供当前层连接next指针
            while(prevLevel!=null)
            {
                if(prevLevel.left!=null)
                {
                    if(currLeftMost==null)  //如果currLeftMost为空 说明我们还没开始遍历当前层
                    {
                        currLeftMost = prevLevel.left;  //从上一层的左孩子开始
                        currPtr = currLeftMost;   //当前层遍历指针从最左点currLeftMost开始
                    }
                    else
                    {
                        currPtr.next = prevLevel.left;  //否则移动currPtr 并做连接
                        currPtr = currPtr.next;
                    }
                }
                if(prevLevel.right!=null)
                {
                    if(currLeftMost==null)  //如果currLeftMost为空 说明左子树不存在 我们还没开始遍历当前层
                    {
                        currLeftMost = prevLevel.right; //同上
                        currPtr = currLeftMost;
                    }
                    else
                    {
                        currPtr.next = prevLevel.right;
                        currPtr = currPtr.next;
                    }
                }
                prevLevel = prevLevel.next; //上一层指针移动到上一层下一个节点 准备操作下一个子树的左右孩子
            }
            prevHead = currLeftMost;  //更新prevHead 将currLeftMost清空 准备遍历下一层
            currLeftMost = null;
        }
    }
}

Note: 改编自code ganker 四个变量要注意都是干什么的 实际上结构很清楚

顺便复习下层序遍历 结合相关题目看一下




from code ganker:

这道题是要将一棵树的每一层维护成一个链表，树本身是给定的。其实思路上很接近层序遍历Binary Tree Level Order Traversal，

只是这里不需要额外维护一个队列。因为这里每一层我们会维护成一个链表，这个链表其实就是每层起始的那个队列，

因此我们只需要维护一个链表的起始指针就可以依次得到整个队列了。接下来就是有左加左入链表，有右加右入链表，知道链表没有元素了说明到达最底层了。

算法的复杂度仍然是对每个结点访问一次，所以是O(n)，而空间上因为不需要额外空间来存储队列了，所以是O(1)。代码如下：

public void connect(TreeLinkNode root) {
    if(root == null)
        return;
    TreeLinkNode lastHead = root;
    TreeLinkNode pre = null;
    TreeLinkNode curHead = null;
    while(lastHead!=null)
    {
        TreeLinkNode lastCur = lastHead;
        while(lastCur != null)
        {
            if(lastCur.left!=null)
            {
                if(curHead == null)
                {
                    curHead = lastCur.left;
                    pre = curHead;
                }
                else
                {
                    pre.next = lastCur.left;
                    pre = pre.next;
                }
            }
            if(lastCur.right!=null)
            {
                if(curHead == null)
                {
                    curHead = lastCur.right;
                    pre = curHead;
                }
                else
                {
                    pre.next = lastCur.right;
                    pre = pre.next;
                }
            }                
            lastCur = lastCur.next;

        }
        lastHead = curHead;
        curHead = null;
    }
}

这道题是树的层序遍历Binary Tree Level Order Traversal的扩展，操作上会更加繁琐一些，因为是通过维护层链表来完成遍历，

不过本质上还是一次广度优先搜索。

