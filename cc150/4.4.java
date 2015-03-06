
public ArrayList<LinkedList<TreeNode>> LevelList(TreeNode root) {
	int level = 0;
	ArrayList<LinkedList<TreeNode>> res = new ArrayList<LinkedList<TreeNode>>();

	if(root==null)
		return res;

	LinkedList list = new LinkedList();
	list.add(root);
	res.add(list);

	while(true)
	{
		LinkedList l = new LinkedList();
		for(int i=0; i<res.get(level).size(); i++)
		{
			TreeNode n = res.get(level).get(i);
			if(n!=null)
			{
				if(n.left!=null)
					l.add(n.left);
				if(n.right!=null)
					l.add(n.right);
			}
		}
		if(l!=null)
			res.add(l);
		else
			break;
		level++;
	}
	return res;
}


Note: 这题扩展看leetcode populating next right