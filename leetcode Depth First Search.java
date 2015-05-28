Leetcode Depth-First-Search

Validate Binary Search Tree
1 top-down recursion code ganker第二种解法以及cleanCode第二三种解法
public boolean isValidBST(TreeNode root) {
	return valid(root, null, null);
}

private boolean valid(TreeNode p, Integer low, Integer high) {
	if(p==null)
		return true;
	return (low==null || p.left>low) && (high==null || p.right<high)
		&& valid(p.left, low, p.val) && valid(p.right, p.val, high);
}

O(n) O(n)

2 In-order traversal
From code ganker
if(root==null)
	return true;
List<TreeNode> pre = new ArrayList<TreeNode>();
pre.add(null);
return helper(root, pre);

private boolean helper(TreeNode p, TreeNode pre) {
	if(p==null)
		return true;
	boolean left = helper(p.left, pre);
	if(pre.get(0)!=null && p.val<=pre.get(0))
		return false;
	pre.set(0, p);
	boolean right = helper(p.right, pre);
	return left&&right;
}

O(n) O(n)





Symmetric Tree
1 递归
if(root==null)
	return true;
return helper(root.left, root.right);

private boolean helper(TreeNode left, TreeNode right) {
	if(left==null && right==null)
		return true;
	else if(left.val==right.val)
		return helper(left.left, right.right) && helper(left.right, right.left);
	else
		return false;
}

O(n) O(logn)

2 非递归





Sum Root To Leaf
类似先序遍历
if(root==null)
	return 0;
return helper(root, 0);

private int helper(TreeNode p, int sum) {
	if(p==null)
		return 0;
	if(p.left==null && p.right==null)
		return sum;
	return helper(p.left, sum*10 + p.val) + helper(p.right, sum = sum*10 + p.val);
}

O(n) O(logn)





Same Tree
if(p==null && q==null)
    return true;
else if(p==null || q==null)
    return false;
else if(p.val!=q.val)
    return false;
return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

O(n) O(logn)





Recover Binary Tree
if(root==null)
	return;
List<TreeNode> pre = new ArrayList<TreeNode>();
List<TreeNode> res = new ArrayList<TreeNode>();
pre.add(null);
helper(root, pre, res);
int tmp = res.get(0).val;
res.get(0).val = res.get(1).val;
res.get(1).val = tmp;

private void helper(TreeNode root, List<TreeNode> pre, List<TreeNode> res) {
	if(root==null)
		return;
	helper(root.left, pre, res);
	if(pre.get(0)!=null && pre.get(0).val>=root.val) {
		if(res.size()==0) {
			if(res.size()==0) {
				res.add(pre.get(0));
				res.add(root);
			}
			else {
				res.set(1, root);
			}
		}
	}
	pre.set(0, root);
	helper(root.right, pre, res);
}

O(n) O(logn)





Populating Next Right Pointers
perfect binary tree 递归解
if(root==null)
	return;
root.left.next = root.right;
if(root.next!=null)
	root.right.next = root.next.left;
else
	root.right.next = null;
connect(root.left);
connect(root.right);

O(n) O(logn)






Populating Next Right Pointers
层序遍历套路 一层层扫 由prevLevel操作currLevel 也可以解第一个题
if(root==null)
	return;
TreeLinkNode currLeftMost = null;
TreeLinkNode currLevel = null;
TreeLinkNode prevHead = root;
while(prevHead!=null) {
    TreeLinkNode prevLevel = prevHead;
    while(prevLevel!=null) {
        if(prevLevel.left!=null) {
            if(currLeftMost==null) {
                currLeftMost = prevLevel.left;
                currLevel = currLeftMost;
            }
            else {
                currLevel.next = prevLevel.left;
                currLevel = currLevel.next;
            }
        }
        if(prevLevel.right!=null) {
            if(currLeftMost==null) {
                currLeftMost = prevLevel.right;
                currLevel = currLeftMost;
            }
            else {
                currLevel.next = prevLevel.right;
                currLevel = currLevel.next;
            }
        }
        prevLevel = prevLevel.next;
    }
    prevHead = currLeftMost;
    currLeftMost = null;
}

O(n) O(1)





Path Sum
只要找出一条root到leaf路径和等于sum即可 用递归很简单
if(root==null)
	return false;
if(root.left==null && root.right==null)
	return sum==root.val;
else
	return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);

O(n) O(logn)





Path Sum ii
找出所有符合要求的路径
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(root==null)
	return res;
helper(root, sum, new ArrayList<Integer>(), res);
return res;

private void helper(TreeNode root, int sum, List<Integer> item, List<List<Integer>> res) {
	if(root==null)
		return;
	if(root.left==null && root.right==null && sum==0) {
		res.add(new ArrayList<Integer>(item));
		return;
	}
	if(root.left!=null) {
		item.add(root.val);
		helper(root.left, sum-root.val, item, res);
		item.remove(item.size()-1);
	}
	if(root.right!=null) {
		item.add(root.val);
		helper(root.right, sum-root.val, item, res);
		item.remove(item.size()-1);
	}
}

O(n) O(klogn)假设有k条路径符合结果





Number of Islands
This is an variation of the standard problem: “Counting number of connected components in a undirected graph”.
dfs解法
if(grid==null || grid.length==0 || grid[0].length==0)
	return 0;
boolean[][] visited = new boolean[grid.length][grid[0].length];
int count = 0;
for(int i=0; i<grid.length; i++) {
	for(int j=0; j<grid[0].length; j++) {
		if(isValid(grid, i, j, visited)) {
			dfs(grid, i, j, visited);
			count++;
		}
	}
}
return count;

private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
	int[] rowNum = {1, 0, 0, -1};
	int[] colNum = {0, 1, -1, 0};

	visited[i][j] = true;
	for(int k=0; k<4; k++) {
		if(isValid(grid, i+rowNum[k], i+colNum[k], visited))
			dfs(grid, i+rowNum[k], i+colNum[k], visited);
	}
}

private boolean isValid(char[][] grid, int i, int j, boolean[][] visited) {
	if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j]=='1' && !visited[i][j])
        return true;
    return false;
}

O(row*col)





Maximum Depth of Binary Tree
1 递归
if(root==null)
	return 0;
return Math.max(maxDepth(root.left), maxDepth(root.right))+1;

O(n) O(logn)

2 非递归
if(root==null)
	return 0;
Queue<TreeNode> queue = new LinkedList<TreeNode>();
queue.add(root);
int level = 1;
int curr = 1, next = 0;
while(!queue.isEmpty()) {
	TreeNode node = queue.poll();
	curr--;
	if(node.left!=null) {
		queue.add(node.left);
		next++;
	}
	if(node.right!=null) {
		queue.add(node.right);
		next++;
	}
	if(curr==0) {
		curr = next;
		next = 0;
		level++;
	}
}
return level;

O(n) O(n)





Minimum Depth of Binary Tree
1 递归
if(root==null)
    return 0;
if(root.left==null)
    return minDepth(root.right)+1;
if(root.right==null)
    return minDepth(root.left)+1;
return Math.min(minDepth(root.left), minDepth(root.right))+1;

O(n) O(logn)

2 非递归
if(root == null)
    return 0;
Queue<TreeNode> queue = new LinkedList<TreeNode>();
int curr = 1, next = 0;
int level = 1;
queue.offer(root);
while(!queue.isEmpty())
{
    TreeNode cur = queue.poll();
    if(cur.left==null && cur.right==null)
        return level;
    curr--;
    if(cur.left!=null)
    {
        queue.offer(cur.left);
        next++;
    }
    if(cur.right!=null)
    {
        queue.offer(cur.right);
        next++;
    }
    if(lastNum==0)
    {
        curr = next;
        next = 0;
        level++;
    }
}
return 0;






































