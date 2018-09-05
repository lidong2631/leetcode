Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5




# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a list node
    # @return a tree node
    def sortedArrayToBST(self, array):
        length = len(array)
        if length==0: return None
        if length==1: return TreeNode(array[0])
        root = TreeNode(array[length/2])
        root.left = self.sortedArrayToBST(array[:length/2])
        root.right = self.sortedArrayToBST(array[length/2+1:])
        return root
        
    def sortedListToBST(self, head):
        array = []
        p = head
        while p:
            array.append(p.val)
            p = p.next
        return self.sortedArrayToBST(array)




Java:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) 
            return null;
        List<ListNode> l = new LinkedList<ListNode>()
        l.add(head);
        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
            len++;
        }
        
        return helper(l, 0, len-1);
    }
    
    private TreeNode helper(List<ListNode> l, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode leftChild = helper(l, left, mid - 1);
        TreeNode root = new TreeNode(l.get(0).val);
        l.set(0, l.get(0).next);
        root.left = leftChild; 
        root.right = helper(l, mid + 1, right);
        return root;
    }
}


1 - 2 - 3 - 4 - 5
        3
       / \
      2   4
     /     \
    1       5


from code ganker:

这个题是二分查找树的题目，要把一个有序链表转换成一棵二分查找树。其实原理还是跟Convert Sorted Array to Binary Search Tree这道题相似，

我们需要取中点作为当前函数的根。这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。这时候就要利用到树的中序遍历了，

按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。思路就是先对左子树进行递归，

然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。整体过程就是一次中序遍历，时间复杂度是O(n)，

空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n)。代码如下： 

public TreeNode sortedListToBST(ListNode head) {
    if(head == null)
        return null;
    ListNode cur = head;
    int count = 0;
    while(cur!=null)
    {
        cur = cur.next;
        count++;
    }
    ArrayList<ListNode> list = new ArrayList<ListNode>();
    list.add(head);
    return helper(list,0,count-1);
}
private TreeNode helper(ArrayList<ListNode> list, int l, int r)
{
    if(l>r)
        return null;
    int m = (l+r)/2;
    TreeNode left = helper(list,l,m-1);
    TreeNode root = new TreeNode(list.get(0).val);
    root.left = left;
    list.set(0,list.get(0).next);
    root.right = helper(list,m+1,r);
    return root;
}

这道题是不错的题目，不过这种构造的方式比较绕，因为一般来说我们都是对于存在的树进行遍历，这里是模拟一个中序遍历的过程把树从无到有地构造出来。

过程比较不常规，不过多想想就明白了哈。

