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
一维dp res[i][j] = res[i-1][j] + res[i][j-1]
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
加了障碍的一维dp
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
一维dp 原型是catalan number 更优的解是直接用catalan number通项公式
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
递归构建左右子树 再接到root上
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





Triangle
int[] sum = new int[triangle.size()];
sum[0] = triangle.get(0).get(0);
for(int i=1; i<triangle.size(); i++) {
    sum[i] = sum[i-1] + triangle.get(i).get(i);
    for(int j=i-1; j>=1; j--)
        sum[j] = (sum[j]<sum[j-1]?sum[j]<sum[j-1]) + triangle.get(i).get(j);
    sum[0]+=triangle.get(i).get(0);
}

O(n^2) O(n)





Scramble String
boolean[][][] res = new boolean[s1.length()][s2.length()][s1.length()+1];
for(int i=0; i<s1.length(); i++) {
    for(int j=0; j<s2.length(); j++) {
        res[i][j][1] = (s1.charAt(i)==s2.charAt(j));
    }
}
for(int len=2; len<s1.length()+1; len++) {
    for(int i=0; i<s1.length()-len+1; i++) {
        for(int j=0; j<s2.length()-len+1; j++) {
            for(int k=1; k<len; k++)
                res[i][j][len] |= (res[i][j][k] && res[i+k][j+k][len-k]) || (res[i][j+len-k][k] && res[i+k][j][len-k]);
        }
    }
}
return res[0][0][s1.length()];

O(n^4) O(n^3)





Palindrome Partitioning
Longest Palindrome + Word Break ii 先用Longest Palindrome套路建立字典 然后用word break ii方法保存所有结果

helper(s, getDict(s), 0, new ArrayList<String>(), res);

private void helper(String s, boolean[][] dict, int start, List<String> item, List<List<String>> res) {
    if(start>=s.length()) {
        res.add(new ArrayList<String>(item));
        return;
    }
    for(int i=start; i<s.length(); i++) {
        if(dict[start][i]) {
            item.add(s.substring(start, i+1));
            helper(s, dict, i+1, item, res);
            item.remove(item.size()-1);
        }
    }
}

private boolean[][] getDict(String s) {
    boolean[][] dict = new boolean[s.length()][s.length()];
    for(int i=s.length()-1; i>=0; i--) {
        for(int j=i; j<s.length(); j++) {
            if((s.charAt(i)==s.charAt(j)) && ((j-i<2) || dict[i+1][j-1]))
                dict[i][j] = true;
        }
    }
    return dict;
}

时间复杂度取决结果的数量 最坏指数 空间O(n^2)





Partition Palindrome ii
跟word break一个套路
boolean[][] dict = new boolean[s.length()][s.length()];
dict = getDict(s);
int[] res = new int[s.length()+1];
for(int =0; i<s.length(); i++) {
    for(int j=0; j<=i; j++) {
        res[i+1] = Math.min(res[j]+1, res[i+1]);
    }
}
return res[s.length()]-1;

private boolean[][] getDict(String s) {
    boolean[][] dict = new boolean[s.length()][s.length()];
    for(int i=s.length()-1; i>=0; i--) {
        for(int j=i; j<s.length(); j++) {
            if((s.charAt(i)==s.charAt(j)) && ((j-i<2) || dict[i+1][j-1]))
                dict[i][j] = true;
        }
    }
    return dict;
}

O(n^2) O(n^2)





Minimum Path Sum
int[] res = new int[grid[0].length];
res[0] = grid[0][0];
for(int i=1; i<grid[0].length; i++)
    res[i]+=res[i-1];
for(int i=1; i<grid.length; i++) {
    for(int j=0; j<grid[0].length; j++) {
        if(j==0)
            res[j]+=res[j-1]
        else
            res[j] = Math.min(res[j], res[j-1]) + grid[i][j];
    }
}
return res[grid[0].length-1];

O(m*n) O(m)





Maximum Subarray
一维dp
int local = nums[0], global = nums[0];
for(int i=1; i<nums.length; i++) {
    local = Math.max(local+nums[i], nums[i]);
    global = Math.max(local, global);
}

O(n) O(1)

还有个divide and conquer解法 O(nlogn)





Maximum Product Subarray




















































