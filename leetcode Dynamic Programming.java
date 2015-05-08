Word Break i
boolean[] res = new boolean[s.length()+1];
res[0] = true;
for(int i=0; i<s.length(); i++) {
	StringBuilder tmp = new StringBuilder(s.substring(0, i+1));
	for(int j=0; j<=i; j++) {
		if(res[j] &&　wordDict.contains(tmp.toString())) {
			res[i+1] = true;
			break;
		}
		tmp.deleteCharAt(0);
	}
}
return res[s.length()];

O(n^2) O(n)





Word Break ii
List<String> res = new ArrayList<String>();
helper(s, wordDict, 0, "", res);
return res;

private void helper(String s, Set<String> wordDict, int start, String item, List<String> res) {
	if(start>=s.length()) {
		res.add(item);
		return;
	}
	StringBuilder tmp = new StringBuidler();
	for(int i=start; i<s.length(); i++) {
		tmp.append(s.charAt(i));
		String newItem = item.length()>0?item+" "+tmp.toString():tmp.toString();
		helper(s, wordDict, i+1, newItem, res);
	}
}





Unique Paths
int min = m>n?n:m;
int max = m>n?m:n;
int[] res = new int[min];
for(int i=0; i<max; i++) {
	for(int j=1; j<min; j++)
		res[j]+=res[j-1];
}
return res[min-1];

O(m*n) O(min(m,n))





Unique Paths ii
int max = obstacleGrid.length>obstacleGrid[0].length ? obstacleGrid.length:obstacleGrid[0].length;
int min = obstacleGrid.length>obstacleGrid[0].length ? obstacleGrid[0].length:obstacleGrid.length;
int[] res = new int[min];
res[0] = 1;
for(int i=0; i<max; i++) {
	for(int j=0; j<min; j++) {
		if(obstacleGrid.length>=obstacleGrid[0].length && obstacleGrid[i][j]==1)
			res[j] = 0;
		else if(obstacleGrid[0].length>obstacleGrid.length && obstacleGrid[j][i]==1)
			res[j] = 0;
		else if(j>0)
			res[j]+=res[j-1];
	}
}
return res[min-1];

O(m*n) O(min(m,n))





Unique Binary Search Tree
if(n<=0)
	return 0;
int[] res = new int[n+1];
res[0] = 1; res[1] = 1;
for(int i=2; i<=n; i++) {
	for(int j=0; j<i; j++)
		res[i] = res[j]*res[i-1-j];
}
return res[n];

O(n^2) O(n)





Unique Binary Search Tree ii
return helper(1, n);

private List<TreeNode> helper(int left, int right) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if(left>right) {
        res.add(null);
        return res;
    }
    for(int i=left; i<=right; i++) {
        List<TreeNode> leftsub = helper(left, i-1);
        List<TreeNode> rightsub = helper(i+1, right);
        for(int j=0; j<leftsub.size(); j++) {
            for(int k=0; k<rightsub.size(); k++) {
                TreeNode root = new TreeNode(i);
                root.left = leftsub.get(j);
                root.right = rightsub.get(k);
                res.add(root);
            }
        }
    }
    return res;
}

指数复杂度