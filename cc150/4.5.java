public TreeNode inorderSuccessor(TreeNode node) {
	if(node!=null)
	{
		TreeNode p;
		if(node.parent==null || node.right!=null)
			p = leftMostChild(node.right);
		else
		{
			while((p=node.parent)!=null)
			{
				if(p.left==node)
					break;
				node = p;
			}
		}
		return p;
	}
	return null;
}

public TreeNode leftMostChild(TreeNode node) {
	if(node==null)
		return null;
	while(node.left!=null)
		node = node.left;
	return node;
}

Note: 这道题有3种情况 不难 看程序很好懂 主要是最后一种情况注意