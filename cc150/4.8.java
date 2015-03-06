public ArrayList<ArrayList<Integer>> findSum(TreeNode root, int sum) {
	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

	if(root==null)
		return res;
	helper(root, sum, new ArrayList<Integer>(), 0, res);
	return res;
}

public void helper(TreeNode root, int sum, ArrayList<Integer> path, int level, ArrayList<ArrayList<Integer>> res) {
	if(root==null)
		return;
	int tmp = sum;
	path.add(root.val);

	for(int i=level; i>=0; i--)
	{
		tmp-=path.get(i);
		//System.out.println("tmp = " + tmp);
		if(tmp==0)
		{
			ArrayList<Integer> match = new ArrayList();
			for(int j=level; j>=i; j--)
				match.add(path.get(j));
			res.add(match);
		}
	}
	ArrayList<Integer> newPathLeft = (ArrayList<Integer>)path.clone();
	ArrayList<Integer> newPathRight = (ArrayList<Integer>)path.clone();

	helper(root.left, sum, newPathLeft, level+1, res);
	helper(root.right, sum, newPathRight, level+1, res);
}

Note: 这一题两点注意 1 它所谓的路径path不包括从叶子结点到叶子结点 都是指从根到叶子中的路径 不垮子树的

2 着重注意 20-25 每次for循环都是从当前节点判断 如果sum减去当前节点的值等于0则得到一组解 然后继续如果后面还能等于0则得到另一组解 也要add到res中去


PeterB • a year ago
方案2有错误吧？ 因为规定路径不必从根节点开始，即中间一截的路径和为sum也可，那么就要在for循环中判断vector的和与sum的大小了吧?
 • Reply•Share › 
Avatar
betterfish  PeterB • a year ago
没问题，中间一截即可的话for循环里tmp就等于sum，然后打引出来；这截满足之后，再往上如果还能是tmp等于sum的路径也能打印出来，方法很好