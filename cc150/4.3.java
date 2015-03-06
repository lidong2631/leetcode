see leetcode convert sorted array to binary tree



public TreeNode createTree(int[] num) {
    if(num==null || num.length==0)
        return null;
	return helper(num, 0, num.length-1);
}

public TreeNode helper(int[] num, int left, int right) {
    if(left>right)
        return null;
    int mid = (left+right)/2;
    TreeNode root = new TreeNode(num[mid]);
    root.left = helper(num, left, mid-1);
    root.right = helper(num, mid+1, right);
    return root;
}

also see leetcode convert sorted list to binary tree