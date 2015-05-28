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





Flatten Binary Tree to Linked List
List<TreeNode> pre = new ArrayList<TreeNode>();
pre.add(null);
helper(root, pre);

private void helper(TreeNode root, List<TreeNode> pre) {
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

O(n) O(logn)





Course Schedule
本质是检测有向图是否有cycle

if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
	return true;
boolean[] visited = new boolean[prerequisites.length];
boolean[] recStack = new boolean[prerequisites.length];
List<List<Integer>> res = new ArrayList<List<Integer>>();

for(int i=0; i<numCourses; i++) {
	res.add(new ArrayList<Integer>());
}
for(int i=0; i<prerequisites.length; i++) {
	for(int j=1; j<prerequisites[i].length; j++) {
		res.get(prerequisites[i][0]).add(prerequisites[i][j]);
	}
}
for(int i=0; i<numCourses; i++) {
	if(hasCycle(i, visited, recStack, res))
		return false;
}
return true;

private boolean hasCycle(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> res) {
	if(!visited[v]) {
		visited[v] = true;
		recStack[v] = true;

		List<Integer> item = res.get(v);
		for(int i=0; i<item.size(); i++) {
			if(!visited[item.get(i)] && hasCycle(item.get(i), visited, recStack, res))
				return true;
			else if(recStack[item.get(i)])
				return true;
		}
	}
	recStack[v] = false;
	return false;
}

O(E+V) O(n) n is numCourses





Course Schedule ii
上一题的扩展 不光判断有没有环 还要topological sort输出结果
int[] resArr = new int[numCourses];
for(int i=0; i<numCourses; i++)     //这里是为了应对没有先修课的情况 如2，[] 这时应该返回[0,1] 而不是[0,0]
    resArr[i] = i;
if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
    return resArr;
boolean[] visited = new boolean[numCourses];
boolean[] recStack = new boolean[numCourses];
List<Integer> check = new ArrayList<Integer>(); //check判断是否有cycle 0为无环 1有环
check.add(0);

List<List<Integer>> res = new ArrayList<List<Integer>>();
for(int i=0; i<numCourses; i++)
    res.add(new ArrayList<Integer>());
for(int i=0; i<prerequisites.length; i++) {
    for(int j=1; j<prerequisites[i].length; j++)
        res.get(prerequisites[i][0]).add(prerequisites[i][j]);
}

Queue<Integer> queue = new LinkedList<Integer>();
for(int i=0; i<numCourses; i++) {
    if(!visited[i] && check.get(0)==0)
        tps(i, visited, recStack, check, queue, res);
}
if(check.get(0)==1) {  //根据check的值输出结果
    int[] tmp = {};     //有环
    return tmp;
}
int i=0;            //正常无环情况
while(queue.size()>0) {
    resArr[i] = queue.poll();
    i++;
}
return resArr;

O(E+V) O(n)





Convert Sorted List to Binary Tree
利用中序遍历扫一遍 从无到有把一棵树构造出来
if(head==null)
	return null;
List<ListNode> list = new ArrayList<ListNode>();
list.add(head);
ListNode curr = head;
int count = 0;
while(curr!=null) {
	curr = curr.next;
	count++;
}
return helper(list, 0, count-1);

private TreeNode helper(List<ListNode> list, int left, int right) {
	if(left>right)
		return null;
	int mid = (left+right)/2;
	TreeNode left = helper(list, left, mid-1);
	TreeNode root = list.get(0);

	root.left = left;
	list.set(0, list.get(0).next);
	root.right = helper(list, mid+1, right);
}

O(n) O(logn)+O(n)





Convert Sorted Array to Binary Tree
比convert sorted list还简单
if(num==null || num.length==0)
	return null;
return helper(num, 0, num.length-1);

private TreeNode helper(int[] num, int left, int right) {
	if(left>right)
		return null;
	int mid = (left+right)/2;
	TreeNode root = new TreeNode(num[mid]);
	root.left = helper(num, left, mid-1)；
	root.right = helper(num, mid+1, right);
}

O(n) O(logn)+O(n)





Construct Binary Tree From Inorder and Preorder Traversal
if(preorder==null || preorder.length==0 || inorder==null || inorder.length==0)
	return null;
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<inorder.length; i++) {
	map.put(inorder[i], i);
}
return helper(preorder, 0， preorder.length-1, inorder, 0, inorder.length-1, map);

private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, inL, inR, Map<Integer, Integer> map) {
	if(preL>preR || inL>inR)
		return null;
	TreeNode root = new TreeNode(preorder[preL]);
	int index = map.get(preorder[preL]);
	root.left = helper(preorder, preL+1, preL+index-inL, inorder, inL, index-1, map);
	root.right = helper(preorder, preL+1+index-inL, preR, inorder, index+1, inR, map);
	return root;
}

O(n) O(n)





Construct Binary Tree From Inorder and Postorder Traversal
if(postorder==null || postorder.length=0 || inorder==null || inorder.length==0)
	return null;
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<inorder.length; i++) {
	map.put(inorder[i], i);
}
return helper(postorder, 0， postorder.length-1, inorder, 0, inorder.length-1, map);

private TreeNode helper(int[] postorder, int postL, int postR, int[] inorder, inL, inR, Map<Integer, Integer> map) {
	if(postL>postR || inL>inR)
		return null;
	TreeNode root = new TreeNode(postorder[postR]);
	int index = map.get(postorder[postR]);
	root.left = helper(postorder, postL, postL+index-inL-1, inorder, inL, index-1, map);
	root.right = helper(postorder, postR-(inR-index), preR-1, inorder, index+1, inR, map);
	return root;
}

O(n) O(n)





Clone Graph
1 dfs dfs一般是for loop遍历一个点所有分支 然后里面一个dfs递归每一条路
if(node==null)
    return null;
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
return DFS(node, map);
}

private UndirectedGraphNode DFS(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
if(map.containsKey(node))
    return map.get(node);
UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
map.put(node, nodeCopy);
for(UndirectedGraphNode neighbor : node.neighbors) {
    nodeCopy.neighbors.add(DFS(neighbor, map));
}
return nodeCopy;
}

O(n) O(n)

2 bfs bfs一般是用一个queue储存一层的点 一层层往下扫
if(node==null)
    return null;
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
map.put(node, nodeCopy);
queue.add(node);
while(!queue.isEmpty()) {
    UndirectedGraphNode curr = queue.poll();
    for(UndirectedGraphNode p : curr.neighbors) {    
        if(map.containsKey(p)) {
            map.get(curr).neighbors.add(map.get(p));
        }
        else {
            UndirectedGraphNode pCopy = new UndirectedGraphNode(p.label);
            map.put(p, pCopy);
            map.get(curr).neighbors.add(pCopy);
            queue.add(p);
        }
    }
}
return nodeCopy;





Binary Tree Right Side View
树的层序遍历套路 唯一不同是当currLevel为0时要将结果加入res 即只保留最右侧的节点
List<Integer> res = new ArrayList<Integer>();
if(root==null)
    return res;
LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
queue.offer(root);
int currLevel = 1;
int nextLevel = 0;
while(!queue.isEmpty()) {
    currLevel--;
    TreeNode node = queue.poll();
    if(node.left!=null) {
        nextLevel++;
        queue.offer(node.left);
    }
    if(node.right!=null) {
        nextLevel++;
        queue.offer(node.right);
    }
    if(currLevel==0) {
        currLevel = nextLevel;
        nextLevel = 0;
        res.add(node.val);
    }
}
return res;





Binary Tree Maximum Path Sum
一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。

而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。

在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新
if(root==null)
	return 0;
List<Integer> res = new ArrayList<Integer>();
res.add(null);
helper(root, res);
return res.get(0);

private int helper(TreeNode root, List<Integer> res) {
	if(root==null)
		return 0;
	int left = helper(root.left, res);
	int right = helper(root.right, res);
	int curr = root.val+(left>0?left:0)+(right>0?right:0);
	if(res.get(0)==null)
		res.set(0, curr);
	else if(res.get(0)<curr)
		res.set(0, curr);
	return root.val+Math.max(left, Math.max(right, 0));
}

O(n) O(logn)





Balanced Binary Tree
return helper(root)>=0;

private int helper(TreeNode root) {
	if(root==null)
		return 0;
	int left = helper(root.left);
	int right = helper(root.right);
	if(left<0 || right<0)
		return -1;
	else if(Math.abs(left-right)>=2)
		return -1;
	return Math.max(left, right) + 1;
}

O(n) O(logn)







 