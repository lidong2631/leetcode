public boolean containsTree(TreeNode large, TreeNode small) {
	if(small==null)
		return true;
	if(large==null)
		return false;
	return subTree(large, small);
}

public boolean subTree(TreeNode large, TreeNode small) {
	if(large.val==small.val)
		return isMatch(large, small);
	return subTree(large.left, small) || subTree(large.right, small);
}

public boolean isMatch(TreeNode large, TreeNode small) {
	if(large==null && small==null)
		return true;
	if(large==null || small==null)
		return false;
	if(large.val!=small.val)
		return false;
	return isMatch(large.left, small.left) && isMatch(large.right, small.right);
}