题意：

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

解题思路：这题是说一颗二叉查找树中的某两个节点被错误的交换了，需要恢复成原来的正确的二叉查找树。

算法一：思路很简单，一颗二叉查找树的中序遍历应该是升序的，而两个节点被交换了，那么对这个错误的二叉查找树中序遍历，肯定不是升序的。

那我们只需把顺序恢复过来然后进行重新赋值就可以了。开辟两个列表，list用来存储被破坏的二叉查找树的节点值，listp用来存储二叉查找树的节点的指针。

然后将list排序，再使用listp里面存储的节点指针赋值就可以了。

代码：


# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a tree node
    def inorder(self, root, list, listp):
        if root:
            self.inorder(root.left, list, listp)
            list.append(root.val); listp.append(root)
            self.inorder(root.right, list, listp)
    def recoverTree(self, root):
        list = []; listp = []
        self.inorder(root, list, listp)
        list.sort()
        for i in range(len(list)):
            listp[i].val = list[i]
        return root




算法二：

题目有一个附加要求就是要求空间复杂度为常数空间。而算法一的空间复杂度为O(N)，还不够省空间。以下的解法也是中序遍历的写法，只是非常巧妙，使用了一个prev指针。例如一颗被破坏的二叉查找树如下：

　　　　　　　　4

　　　　　　　/     \

　　    2        6

    /   \    /   \

    1    5  3    7

很明显3和5颠倒了。那么在中序遍历时：当碰到第一个逆序时：为5->4，那么将n1指向5，n2指向4，注意，此时n1已经确定下来了。
然后prev和root一直向后遍历，直到碰到第二个逆序时：4->3，此时将n2指向3，那么n1和n2都已经确定，只需要交换节点的值即可。
prev指针用来比较中序遍历中相邻两个值的大小关系，很巧妙。 



# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def findTwoNodes(self, root):
        if root:
            self.findTwoNodes(root.left)            #中序遍历
            if self.prev and self.prev.val > root.val:  #如果不是增序 则说明有错位
                self.n2 = root                      #n2赋值当前的root
                if self.n1 == None:                 #如果n1为None 则说明这是第一个错位 将prev赋值给它 得到第一个错位节点
                    self.n1 = self.prev
            self.prev = root                        #如果是增序 则prev等于当前root节点 继续遍历
            self.findTwoNodes(root.right)
        
    # @param root, a tree node
    # @return a tree node
    def recoverTree(self, root):
        self.n1 = self.n2 = None        #初始化n1, n2, prev. n1, n2用来保存错位的两个节点 prev用来在做比较时指向上一节点
        self.prev = None
        self.findTwoNodes(root)         #调用函数
        self.n1.val, self.n2.val = self.n2.val, self.n1.val     #交换两个数值 python独有
        return root

Note: python交换数值


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
    public void recoverTree(TreeNode root) {
        List<TreeNode> pre = new ArrayList<TreeNode>();
        List<TreeNode> res = new ArrayList<TreeNode>();
        
        pre.add(null);
        helper(root, pre, res);
        
        int tmp = res.get(0).val;
        res.get(0).val = res.get(1).val;
        res.get(1).val = tmp;
    }
    private void helper(TreeNode root, List<TreeNode> pre, List<TreeNode> res) {
        if (root != null) {
            helper(root.left, pre, res);
            if (pre.get(0) != null && pre.get(0).val > root.val) {
                if (res.size() == 0) {
                    res.add(pre.get(0));
                    res.add(root);
                }
                else
                    res.set(1, root);
            }
            pre.set(0, root);
            helper(root.right, pre, res);
        }
    }
}


from code ganker:

这道题是要求恢复一颗有两个元素调换错了的二叉查找树。一开始拿到可能会觉得比较复杂，其实观察出规律了就比较简单。主要还是利用二叉查找树的主要性质，就是中序遍历是有序的性质。

那么如果其中有元素被调换了，意味着中序遍历中必然出现违背有序的情况。那么会出现几次呢？有两种情况，如果是中序遍历相邻的两个元素被调换了，很容易想到就只需会出现一次违反情况，

只需要把这个两个节点记录下来最后调换值就可以；如果是不相邻的两个元素被调换了，举个例子很容易可以发现，会发生两次逆序的情况，那么这时候需要调换的元素应该是第一次逆序前面的元素，

和第二次逆序后面的元素。比如1234567,1和5调换了，会得到5234167，逆序发生在52和41，我们需要把4和1调过来，那么就是52的第一个元素，41的第二个元素调换即可。

搞清楚了规律就容易实现了，中序遍历寻找逆序情况，调换的第一个元素，永远是第一个逆序的第一个元素，而调换的第二个元素如果只有一次逆序，则是那一次的后一个，

如果有两次逆序则是第二次的后一个。算法只需要一次中序遍历，所以时间复杂度是O(n)，空间是栈大小O(logn)。代码如下：

public void recoverTree(TreeNode root) {
    if(root == null)
        return;
    ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
    pre.add(null);
    ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    helper(root,pre, res);
    if(res.size()>0)
    {
        int temp = res.get(0).val;
        res.get(0).val = res.get(1).val;
        res.get(1).val = temp;
    }
}
private void helper(TreeNode root, ArrayList<TreeNode> pre, ArrayList<TreeNode> res)
{
    if(root == null)
    {
        return;
    }
    helper(root.left, pre, res);
    if(pre.get(0)!=null && pre.get(0).val>root.val)
    {
        if(res.size()==0)
        {
            res.add(pre.get(0));
            res.add(root);
        }
        else
        {
            res.set(1,root);
        }
    }
    pre.set(0,root);
    helper(root.right,pre,res);
}

可以看到实现中pre用了一个ArrayList来存，这样做的原因是因为java都是值传递，所以我们要全局变化pre的值（而不是在当前函数里），只能传一个数组，才能改变结点的地址，

这一点非常重要，也是java和C++一个比较大的区别，不了解的朋友可以研究一下哈。

这道题还是考察二叉树遍历，不过应用题目要求套了一个不同的外壳，需要我们利用二叉查找树的性质观察出规律之后才能求解。


