inorder:

ArrayList<Integer> res = new ArrayList<Integer>();

public void inorder(TreeNode root) {
	if(root!=null)
	{
		inorder(root.left);
		res.add(root.val);
		inorder(root.right);
	}
}




public ArrayList<Integer> inorder(TreeNode root) {
	ArrayList<Integer> res = new ArrayList<Integer>();
	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

	while(!stack.isEmpty() || root!=null)
	{
		if(root!=null)
		{
			stack.push(root)
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








preorder:

ArrayList<Integer> res = new ArrayList<Integer>();

public void preorder(TreeNode root) {
	if(root!=null)
	{
		res.add(root.val);
		preorder(root.left);
		preorder(root.right);
	}
}




public ArrayList<Integer> preorder(TreeNode root) {
	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
	ArrayList<Integer> res = new ArrayList<Integer>();

	while(!stack.isEmpty() || root!=null)
	{
		if(root!=null)
		{
			stack.push(root);
			res.add(root.val);
			root = root.left;
		}
		else
		{
			root = stack.pop();
			root = root.right;
		}
	}
	return res;
}








postorder:

ArrayList<Integer> res = new ArrayList<Integer>();

public void postorder(TreeNode root) {
	if(root!=null)
	{
		postorder(root.left);
		postorder(root.right);
		res.add(root.val);
	}
}




public ArrayList<Integer> postorder(TreeNode root) {
	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
	ArrayList<Integer> res = new ArrayList<Integer>();

	stack.push(root);
	TreeNode prev = null;

	while(!stack.isEmpty())
	{
		TreeNode node = stack.peek();
		if((node.left==null&&node.right==null) || (prev!=null&&(prev==node.left || prev==node.right)))
		{
			stack.pop();
			res.add(node.val);
			prev = node;
		}
		else
		{
			if(root.right!=null)
				stack.push(root.right);
			if(root.left!=null)
				stack.push(root.left);
		}
	}
	return res;
}