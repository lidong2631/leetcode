1 如果是二叉查找树 如果node1.val<root.val<node2.val 返回root

否则如果node1.val, node2.val都小于root.val 或 node1.val, node2.val都大于root.val 递归

直到找到一点大于其中一点的val 小于另一点的val 返回此点

2 如果节点有父指针 trace p and q paths up until they intersect

3 No parent link


public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	if(isCover(root.left, p) && isCover(root.left, q))
		return commonAncestor(root.left, p, q);
	if(isCover(root.right, p) && isCover(root.right, q))
		return commonAncestor(root.right, p, q);
	return root;
}

public boolean isCover(TreeNode root, TreeNode node) {
	if(root==null)
		return false;
	if(root==node)
		return true;
	return isCover(root.left, node) || isCover(root.right, node);
}