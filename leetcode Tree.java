Validate Binary Search Tree
递归 利用中序遍历有序的特点
boolean helper(TreeNode root, List<Integer> res) {
    if(root==null)
        return true;
    boolean left = helper(root.left, res);
    if(res.get(0)!=null && res.get(0)>=root.val)
        return false;
    res.set(0, root.val);
    return left&&helper(root.right, res);
}

时间O(n) 空间O(1)





Unique Binary Search Trees
有点动态规划的思路 本质是卡特兰数
if n<=0
    return 0;
int[] res = new int[n+1];
res[0] = 1;
res[1] = 1;
for i=2; i<=n; i++
    for j=0; j<i; j++
        res[i]+=res[j]*res[i-1-j];
return res[n];

时间O(n^2) 空间O(n)





Unique Binary Search Trees II
NP问题

helper(int left, int right) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if(left>right) {
        res.add(null);
        return res;
    }
    for(int i=left; i<=right; i++) {
        List<TreeNode> leftTree = helper(left, i-1);
        List<TreeNode> rightTree = helper(i+1, right);
        for(int j=0; j<leftTree.size(); j++) {
            for(int k=0; k<rightTree.size(); k++) {
                TreeNode root = new TreeNode(i);
                root.left = leftTree.get(j);
                root.right = leftTree.get(k);
                res.add(root);
            }
        }
    }
    return root;
}

非多项式时间





Symmetric Tree
1 递归解
if root==null
    return true;
return helper(root.left, root.right);

helper(TreeNode left, TreeNode right) {
    if(left==null && right==null)
        return true;
    if(left!=null || right!=null && left.val==right.val)
        return helper(left.left, right.right) && helper(left.right, right.left);
    return false;
}

2 迭代解 太长看题目

时间O(n) 空间O(logn)





Same Tree
类似于Symmetric Tree
if(p==null && q==null)
    return true;
if((p==null && q!=null) || (p!=null && q==null))
    return false;
if(p.val!=q.val)
    return false;
return helper(p.left, q.left) && helper(p.right, q.right);
时间O(n) 空间O(logn)





Sum Root to Leaf Numbers
递归
return helper(root, 0);

helper(TreeNode root, int sum) {
    if(root==null)
        return 0;
    if(root.left==null && root.right==null)
        return 10*sum+root.val;
    return helper(root.left, 10*sum+root.val)+helper(root.right, 10*sum+root.val);
}

时间O(n) 空间O(logn)





Recover Binary Search Tree
1 利用中序遍历有序的性质 
helepr(root, pre, res);
int tmp = res.get(0).val;
res.get(0).val = res.get(1).val;
res.get(1).val = tmp;

helper(root, pre, res) {
    if(root==null)
        return;
    helper(root.left, pre, res);
    if(pre.get(0)!=null && pre.get(0).val>=root)
        if res.size()==0
            res.add(pre.get(0));
            res.add(root);
        else
            res.set(1, root);
    pre.set(0, root);
    helper(root.right, pre, res);
}

时间O(n) 空间O(1)

2 Morris遍历也可以在常数空间内找到错误点 并recover





Populating Next Right Pointers i ii
树的层序遍历的扩展 本质是广度遍历
prevhead = root; currLeftMost = null; currStr = null;
while(prevHead!=null) {
    TreeLinkNode prevLevel = prevHead;
    while(prevLevel!=null) {
        if(prevLevel.left!=null) {
            if(currLeftMost==null) {
                currLeftMost = prevLevel.left;
                currStr = currLeftMost;
            }
            else {
                currStr = prevLevel.left;
                currStr = currStr.next;
            }
        }
        if(prevLevel.right!=null) {
            if(currLeftMost==null) {
                currLeftMost = prevLevel.right;
                currStr = currLeftMost;
            }
            else {
                currStr = prevLevel.right;
                currStr = currStr.next;
            }
        }
        prevLevel = prevLevel.next;
    }
    prevHead = currLeftMost;
    currLeftMost = null;
}

时间O(n) 空间O(1)





Binary Tree Level Order Traversal i ii
Queue<TreeNode> queue = new LinkedList<TreeNode>();
queue.offer(root);
List<Integer> item = new ArrayList<Integer>();
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

时间O(n) 空间O(n) ii跟i一样 只是最后对res做了一次Collections.reverse()





Binary Tree Zigzag Level Order Traversal
层序遍历变体

currStack = new Stack<TreeNode>();
item = new ArrayList<Integer>();
item.add(root.val); res.add(item);
level = 1;
while(!currStack.isEmpty())
    nextStack = new Stack<TreeNode>();
    item = new ArrayList<Integer>();
    while(!currStack.isEmpty())
        TreeNode currNode = currStack.pop();
        if Level%2==0
            if(currNode.left!=null)
                nextStack.push(currNode.left);
                item.add(currNode.left.val);
            if(currNode.right!=null)
        else
            if(currNode.right!=null)

            if(currNode.left!=null)
    level++;
    currStack = nextStack;
    if(item.size()>0)
        res.add(item);

时间O(n) 空间O(n)





Binary Tree Right Side View
层序遍历的变体





Path Sum i
递归
if(root==null)
    return false;
if(root.left==null && root.right==null && root.val==sum)
    return true;
return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);

时间O(n) 空间O(logn)





Path Sum ii
还是递归 但是需要数据结构来维护中间路径结果以及保存满足条件的所有路径
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(root==null)
    return res;
List<Integer> item = new ArrayList<Integer>();
item.add(root.val);
helper(root, sum, item, res);
return res;

helper(TreeNode root, int sum, List<Integer> item, List<List<Integer>> res) {
    if(root==null)
        return;
    if(root.left==null && root.right==null && sum==0) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(root.left!=null) {
        item.add(root.left.val);
        helper(root.left, sum-root.left.val, item, res);
        item.remove(item.size()-1);
    }
    if(root.right!=null) {
        item.add(root.right.val);
        helper(root.right, sum-root.right, item, res);
        item.remove(item.size()-1);
    }
}

时间O(n) 空间O(klogn)假设有k条路径





Binary Tree Maximum Path Sum
递归 注意这里返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可
helper(root, res);
return res.get(0);

helper(TreeNode root, List<Integer> res) {
    if(root==null)
        return 0;
    int left = helper(root.left, res);
    int right = helper(root.right, res);
    int curr = root.val + (left>0?left:0)+(right>0?right:0);
    if(curr>res.get(0))
        res.set(0, curr);
    return root.val + Math.max(left, Math.max(right, 0));
}

时间O(n) 空间O(logn)





Minimum Depth of Binary Tree
1 递归解
if(root==null)
    return 0;
if(root.left==null)
    return minDepth(root.right)+1;
if(root.right==null)
    return minDepth(root.left)+1;
return Math.min(minDepth(root.left), minDepth(root.right))+1;

2 非递归 层序遍历的套路
queue.offer(root);
int currNum = 1, nextNum = 0, level = 1;
while(!queue.isEmpty()) {
    TreeNode curr = queue.poll();
    currNum--;
    if(curr.left==null && curr.right==null)
        return level;
    if(curr.left!=null)
        queue.offer(curr.left);
        nextNum++;
    if(curr.right!=null)
        queue.offer(curr.right);
        nextNum++;
    if(currNum==0)
        currNum = nextNum;
        nextNum = 0;
        level++;
}

时间O(n) 空间O(logn)





Maximum Depth of Binary Tree
1 递归
if(root==null)
    return 0;
return Math.max(maxDepth(root.left), maxDepth(root.right))+1;

2 非递归 层序遍历套路 在Minimum Depth of Binary Tree的基础上增加一个判断在340行 queue.isEmpty()即可 时间空间一样





Flatten Binary Tree to Linked List
递归本质是先序遍历 维护前一个结点pre，然后每次把pre的左结点置空，右结点设为当前结点 这里需要注意要先把右子结点保存一下
pre.add(null);
helper(root, pre);

helper(TreeNode root, List<Integer> pre) {
    if(root==null)
        return;
    TreeNode right = root.right;
    if(pre.get(0)!=null) {
        pre.get(0).left = null;
        pre.get(0).right = root;
    }
    pre.set(0, root);
    helper(root.left, pre);
    helper(right, pre);
}

时间O(n) 空间O(logn)





Convert Sorted Array to Binary Search Tree
递归 借用二分法和二叉查找书的思路
private TreeNode helper(int[] num, int start, int end) {
    if(start>end)
        return null;
    int mid = (start+end)/2;
    TreeNode root = new TreeNode(num[mid]);
    root.left = helper(num, start, mid-1);
    root.right = helper(num, mid+1, end);
    return root;
}





Construct Binary Tree from Preorder and Inorder Traversal
树题 递归
HashMap<Integer, Integer> map 保存中序值和索引的对应关系
helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);

TreeNode helepr() {
    if inL>inR || preL>preR
        return null;
    TreeNode root = new TreeNode(preorder[preL]);
    int index = map.get(preorder[preL]);
    root.left = helper(preorder, preL+1, preL+index-inL, inorder, inL, index-1, map);
    root.right = helper(preorder, index-inL+preL+1, preR, inorder, index+1, inR, map);
    return root;
}





Construct Binary Tree from Inorder and Postorder Traversal
同上思路
HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<inorder.length; i++)
    map.put(inorder[i], i);
return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, map);

private TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR, HashMap<Integer, Integer> map) {
    if(inL>inR || postL>postR)
        return null;
    TreeNode root = new TreeNode(postorder[postR]);
    int index = map.get(postorder[postR]);
    root.left = helper(inorder, inL, index-1, postorder, postL, postL+index-inL-1, map);
    root.right = helper(inorder, index+1, inR, postorder, postL+index-inL, postR-1, map);
    return root;
}





Binary Tree Upside Down
有点类似于反转LinkedList
1 递归解
return upsideDown(root, null);

private TreeNode upsideDown(TreeNode p, TreeNode parent) {
    if(p==null)
        return parent;
    TreeNode root = upsideDown(p.left, p);
    root.left = parent.right;
    root.right = parent;
    return root;
}

时间O(n) 空间O(logn)

2 非递归
TreeNode p = root, parent = null, parentRight = null;
while(p!=null) {
    TreeNode left = p.left;
    p.left = parentRight;
    parentRight = p.right;
    p.right = parent;
    parent = p;
    p = left;
}

时间O(n) 空间O(1)





Binary Search Tree Iterator





Balanced Binary Tree
递归 过程维护深度并判断左右子树的深度差 0或正数代表深度 －1表示已不平衡

return helper(root)>=0;

int helper(TreeNode root)
    if(root==null)
        return 0;
    int left = helper(root.left);
    int right = helper(root.right);
    if(left<0 || right<0)
        return -1;
    if(Math.abs(left-right)>=2)
        return -1;
    return Math.max(left, right)+1;

时间O(n) 空间O(1)





Binary Tree Inorder Traversal
1 递归
private void helper(TreeNode root, List<Integer> res) {
    if(root==null)
        return;
    helper(root.left, res);
    res.add(root.val);
    helper(root.right, res);
}

时间O(n) 空间O(logn)

2 非递归
while(!stack.empty() || root!=null)
    if(root!=null) {
        root = root.left;
        stack.push(root);
    }
    else {
        TreeNode root = stack.pop();
        res.add(root.val);
        root = root.right;
    }

时间O(n) 空间O(logn)

3 Morris
TreeNode curr = root; TreeNode pre = null;
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
            pre.right=null;
            res.add(curr.val);
            curr = curr.right;
        }
    }
    else {
        res.add(curr.val);
        curr = curr.right;
    }
}

时间O(n) 空间O(1)





Binary Tree Preorder Traversal
1 递归
private void helper(TreeNode root, List<Integer> res) {
    if(root==null)
        return;
    res.add(root.val);
    helper(root.left, res);
    helper(root.right, res);
}

时间O(n) 空间O(logn)

2 非递归
while(!stack.empty() || root!=null) {
    if(root!=null) {
        res.add(root.val);
        stack.push(root);
        root = root.left;
    }
    else {
        root= stack.pop();
        root = root.right;
    }
}

时间O(n) 空间O(logn)

3 Morris
while(curr!=null) {
    if(curr.left!=null) {
        pre = curr.left;
        while(pre.right!=null && pre.right!=curr)
            pre = pre.right;
        if(pre.right==null) {
            pre.right = curr;
            res.add(curr.val);
            curr = curr.left;
        }
        else {
            pre.right=null
            curr =curr.right;
        }
    }
    else {
        res.add(curr.val);
        curr = curr.right;
    }
}

时间O(n) 空间O(1)





Binary Tree Postorder Traversal
1 递归
helper(TreeNode root, List<Integer> res) {
    if(root==null)
        return;
    helper(root.left, res);
    helper(root.right, res);
    res.add(root.val);
}

2 非递归
while(!stack.empty() ||　root!=null) {
    if(root!=null) {
        stack.push(root);
        root = root.left;
    }
    else {
        TreeNode peek = stack.peek();
        if(peek.right!=null && peek.right!=pre)
            root = peek.right;
        else {
            stack.pop();
            res.add(peek.val);
            pre = peek;
        }
    }
}

3 Morris
while(curr!=null) {
    if(curr.left!=null) {
        pre = curr.left;
        while(pre.right!=null && pre.right!=curr)
            pre = pre.right;
        if(pre.right==null) } {
            pre.right = curr;
            curr = curr.left;
        }
        else {
            reverse(curr.left, pre);
            TreeNode tmp = pre;
            while(tmp!=curr.left) {
                res.add(tmp.val);
                tmp = tmp.right;
            }
            res.add(tmp.val);
            reverse(pre, curr.left);
            pre.right = null;
            curr = curr.right;
        }
    }
    else {
        curr = curr.right;
    }
}

void reverse(TreeNode start, TreeNode end) {
    if(start==end)
        return;
    TreeNode pre = start;
    TreeNode curr = start.right;
    TreeNode next = null;
    while(pre!=end) {
        next = curr.right;
        curr.right = pre;
        pre = curr;
        curr = next;
    }
}

时间O(n) 空间O(1)
