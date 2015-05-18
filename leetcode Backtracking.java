Word Search
if(word==null || word.length()==0)
	return true;
if(board==null || board.length==0 || board[0].length==0)
	return false;
boolean[][] used = new boolean[board.length][board[0].length];
for(int i=0; i<board.length; i++) {
	for(int j=0; j<board[0].length; j++) {
		if(search(board, 0, i, j, used, word))
			return true;
	}
}
return false;

private boolean search(board[][], int index, int i, int j, boolean[][] used, String word) {
	if(index==word.length())
		return true;
	if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || word.charAt(index)!=board[i][j])
		return false;
	used[i][j] = true;
	boolean res = (search(board, index+1, i+1, j, used, word)
					|| search(board, index+1, i-1, j, used, word)
					|| search(board, index+1, i, j-1, used, word)
					|| search(board, index+1, i, j+1, used, word));
	used[i][j] = true;
	return res;
}

最坏O(m*n*4^(len(word))) O(m*n)




Word Break ii
List<String> res = new ArrayList<String>();
helper(s, wordDict, 0, "", res);
return res;

private void helper(String s, Set<String> wordDict, int start, String item, List<String> res) {
	if(start>=s.length()) {
		res.add(item);
		return;
	}
	StringBuilder tmp = new StringBuilder();
	for(int i=start; i<s.length(); i++) {
		tmp.append(s.charAt(i));
		if(wordDict.contains(tmp.toString())) {
			String newItem = item.length()>0?item+" "+tmp.toString:tmp.toString();
			helper(s, wordDict, i+1, newItem, res);
		}
	}
}

复杂度取决与结果的数量 最坏指数复杂度





Sudoku Solver
helper(board, 0, 0);

private boolean helper(char[][] board, int i, int j) {
	if(j==9)
		return helper(board, i+1, j);
	if(i==9)
		return true;
	if(board[i][j]=='.') {
		for(int k=1; k<=9; k++) {
			board[i][j] = (char)(k+'0');
			if(isValid(board, i, j)) {
				if(helper(board, i, j+1))
					return true;
			}
		}
		board[i][j] = '.';
	}
	else {
		return helper(board, i, j+1);
	}
	return false;
}

private boolean isValid(char[][] board, int row, int col) {
	char tmp = board[row][col];
	board[row][col]='#';
	for(int i=0; i<9; i++) {
		if(board[row][i]==tmp)
			return false;
	}
	for(int j=0; j<9; j++) {
		if(board[j][col]==tmp)
			return false;
	}
	for(int i=row/3*3; i<row/3*3+3; i++) {
		for(int j=col/3*3; j<col/3*3+3; j++) {
			if(board[i][j]==tmp)
				return false;
		}
	}
	board[i][j] = tmp
	return true;
}





Subsets
1 递归
递归到底 得到空集的base case然后backtrack回来构建
if(S==null || S.length==0)
	return null;
Arrays.sort(S);
return helper(S, S.length-1);

private List<List<Integer>> helper(int[] S, int len) {
	if(len==-1) {
		List<Integer> empty = new ArrayList<Integer>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(empty);
		return res;
	}
	List<List<Integer>> res = helper(S, len-1);
	int size = res.size();
	for(int i=0; i<size; i++) {
		List<Integer> item = new ArrayList<Integer>(res.get(i));
		item.add(S[len]);
		res.add(item);
	}
	return res;
}

O(2^n) O(2^n)

2 非递归
思路差不多 只是没有backtrack 从无到有构建出子集
List<List<Integer>> res = new ArrayList<List<Integer>>();
res.add(new ArrayList<Integer>());
if(S==null || S.length==0)
	return res;
Arrays.sort(S);
for(int i=0; i<S.length; i++) {
	int size = res.size();
	for(int j=0; j<size; j++) {
		List<Integer> item = new ArrayList<Integer>(res.get(j));
		item.add(S[i]);
		res.add(item);
	}
}
return res;





Subsets ii
对于重复数据的处理 只将集合后半部分的子集(上一轮新生成的子集)加上新的元素
1 递归
if(nums==null || nums.length==0)
        return null;
    List<Integer> lastSize = new ArrayList<Integer>();
    lastSize.add(0);
    Arrays.sort(nums);
    return helper(nums, nums.length-1, lastSize);

private List<List<Integer>> helper(int[] nums, int index, List<Integer> lastSize) {
    if(index==-1) {
        List<Integer> item = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(item);
        return res;
    }
    List<List<Integer>> res = helper(nums, index-1, lastSize);
    int size = res.size();
    int start = 0;
    if(index>0 && nums[index]==nums[index-1])
        start = lastSize.get(0);
    for(int i=start; i<size; i++) {
        List<Integer> item = new ArrayList<Integer>(res.get(i));
        item.add(nums[index]);
        res.add(item);
    }
    lastSize.set(0, size);
    return res;
}

2 非递归
List<List<Integer>> res = new ArrayList<List<Integer>>();
res.add(new ArrayList<Integer>());
if(nums==null || nums.length==0)
    return res;
Arrays.sort(nums);    
int start = 0;
for(int i=0; i<nums.length; i++) {
    int size = res.size();
    for(int j=start; j<size; j++) {
        List<Integer> item = new ArrayList<Integer>(res.get(j));
        item.add(nums[i]);
        res.add(item);
    }
    if(i>0 && nums[i]==nums[i-1])	//注意这里不能写i<nums.length-1 && num[i]==nums[i+1]
        start = size;
    else
        start = 0;
}
return res;

O(2^n) O(2^n)





Restore IP Address
List<String> res = new ArrayList<String>();
if(s==null || s.length()==0)
	return res;
helepr(s, 0, 1, "", res);
return res;

private void helper(String s, int index, int segment, String item, List<String> res) {
	if(index>=s.length())
		return;
	if(segment==4) {
		String tmp = s.substring(index);
		if(isValid(tmp)) {
			res.add(item+"."+tmp);
		}
		return;
	}
	for(int i=1; i<4&&(i+index<=s.length()); i++) {
		String tmp = s.substring(index, index+i);
		if(isValid(tmp)) {
			if(item.length()==0)
				helper(s, index+i, segment+1, tmp, res);
			else
				helper(s, index+i, segment+1, item+"."+tmp, res);
		}
	}
}

private boolean isValid(String str) {
	if(str==null || str.length()>3)
		return false;
	int intStr = Integer.parseInt(str);
	if(str.charAt(0)=='0' && str.length()>1)
		return false;
	if(intStr>=0 && intStr<=255)
		return true;
	return false;
}

O(2^k) k是常数





Permutation
1 递归1
用一个数组used标记元素是否访问过
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(num==null || num.length==0)
	return res;
helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
return res;

private void helper(int[] num, boolean[] used, List<Integer> item, List<List<Integer>> res) {
	if(item.length>=num.length) {
		res.add(item);
		return;
	}
	for(int i=0; i<num.length; i++) {
		if(!used[i]) {
			used[i] = true;
			item.add(num[i]);
			helper(num, used, item, res);
			used[i] = false;
			item.remove(item.size()-1);
		}
	}
}

2 非递归
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(num==null || num.length==0)
	return res;
List<Integer> first = new ArrayList<Integer>();
first.add(num[0]);
res.add(first);
for(int i=1; i<num.length; i++) {
	List<List<Integer>> curRes = new ArrayList<List<Integer>>();
	for(int j=0; j<res.size(); j++) {
		List<Integer> item = new ArrayList<Integer>(res.get(j));
		for(int k=0; k<item.size()+1; k++) {
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(k, num[i]);
			curRes.add(tmp);
		}
	}
	res = curRes;
}
return res;





Permutation ii
只对第一个未被使用的递归 如果第一个重复元素前面的元素没被访问过就跳过递归
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(nums==null || nums.length==0)
	return res;
Arrays.sort(nums);
helper(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
return res;

private helper(int[] nums, boolean[] used, List<Integer> item, List<List<Integer>> res) {
	if(item.size()>nums.length) {
		res.add(new ArrayList<Integer>(item));
		return;
	}
	for(int i=0; i<nums.length; i++) {
		if(i>0 && !used[i-1] && nums[i]==nums[i-1])
			continue;
		if(!used[i]) {
			used[i] = true;
			item.add(nums[i]);
			helper(nums, used, item, res);
			used[i] = false;
			item.remove(item.size()-1);
		}
	}
}





Permutation Sequence
if(n<=0)
	return "";
k--;
int fac = 1;
for(int i=2; i<n; i++)
	fac*=i;
StringBuilder res = new StringBuilder();
List<Integer> item = new ArrayList<Integer>();
for(int i=1; i<=n; i++)
	item.add(i);
int round = n-1;
while(round>=0) {
	int index = k/fac;
	k%=fac;
	res.append(item.get(index));
	item.remove(index);
	if(round>0)
		fac/=round;
	round--;
}
return res.toString();

O(n^2) O(n)





Palindrome Partitioning
word break ii + getDict()
List<List<String>> res = new ArrayList<List<String>>();
if(s==null || s.length()==0)
	return res;
helper(s, getDict(s), 0, new ArrayList<String>(), res);
return res;

private void helper(String s, boolean[][] dict, int start, List<String> item, List<List<String>> res) {
    if(start==s.length()) {
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
    boolean[][] res = new boolean[s.length()][s.length()];
    for(int i=s.length()-1; i>=0; i--) {
        for(int j=i; j<s.length(); j++) {
            if(s.charAt(i)==s.charAt(j) && (j-i<2 || res[i+1][j-1]))
                res[i][j] = true;
        }
    }
    return res;
}

指数级复杂度





N Queens
经典np问题 循环＋递归 一行一行扫 每一行一列列试 如果合法就递归 一样的套路
List<String[]> res = new ArrayList<String[]>();
helper(0, n, new int[n], res);
return res;

private void helper(int row, int n, int[] colForRow, List<String[]> res) {
	if(row==n) {
		String[] item = new String[n];
		for(int i=0; i<n; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<n; j++) {
				if(colForRow[i]==j)
					sb.append('Q');
				else
					sb.append('.');
			}
			item[i] = sb.toString();
		}
		res.add(item);
	}
	for(int i=0; i<n; i++) {
		colForRow[row] = i;
		if(isValid(row, colForRow)) {
			helper(row+1, n, colForRow, res);
		}
	}
}

private boolean isValid(int row, int[] colForRow) {
	for(int i=0; i<row; i++) {
		if(colForRow[i]==colForRow[row] || Math.abs(colForRow[i]-colForRow[row])==Math.abs(i-row))
			return false;
	}
	return true;
}

指数级复杂度





N Queens ii
套路跟上一题完全一样 只是找到一个结果时是累加数量而不保存整个结果 复杂度还是一样
List<Integer> res = new ArrayList<Integer>();
res.add(0);
helper(0, n, new int[n], res);
return res.get(0);

private void helper(int row, int n, int[] colForRow, List<Integer> res) {
	if(row==n) {
		res.set(0, res.get(0)+1);
		return;
	}
	for(int i=0; i<n; i++) {
		colForRow[row] = i;
		if(isValid(row, colForRow)) {
			helper(row+1, n, colForRow, res);
		}
	}
}

private boolean isValid(int row, int[] colForRow) {
	for(int i=0; i<row; i++) {
		if(colForRow[i]==colForRow[row] || Math.abs(colForRow[i]-colForRow[row])==Math.abs(i-row))
			return false;
	}
	return true;
}





Letter Combination of a Phone Number
1 非递归
List<String> res = new ArrayList<String>();
if(digits==null || digits.length()==0)
	return res;
res.add("");
for(int i=0; i<digits.length(); i++) {
	List<String> tmp = new ArrayList<String>();	//每次新建一个list
	String digit = map.get(digits.charAt(i));	//新的选择字符
	for(int j=0; j<res.size(); j++) {
		for(int k=0; k<digit.length(); k++) {
			tmp.add(res.get(j)+Character.toString(digit.charAt(k)));
		}
	}
	res = tmp;
}
return res;

private Map<Character, String> map = new HashMap<Character, String>() {{
	put('2', "abc");
    put('3', "def");
    put('4', "ghi");
    put('5', "jkl");
    put('6', "mno");
    put('7', "pqrs");
    put('8', "tuv");
    put('9', "wxyz");
    put('0', " ");
}};

2 递归
List<String> res = new ArrayList<String>();
if(digits==null || digits.length()==0)
	return res;
helper(digits, 0, "", res);
return res;

private void helper(String digits, int index, String item, List<String> res) {
	if(index==digits.length()) {
		res.add(item);
		return;
	}
	String curr = map.get(digits.charAt(index));
	for(int i=0; i<curr.length(); i++)
		helper(digits, index+1, item+Character.toString(curr.charAt(i)), res);
}

O(k^n) O(k^n)





Generate Parentheses
模型是catalan number
List<String> res = new ArrayList<String>();
if(n<=0)
	return res;
helper(n, n, "", res);
return res;

private void helper(int left, int right, String item, List<String> res) {
	if(right<left)
		return;
	if(left==0 && right==0) {
		res.add(item);
		return;
	}
	if(left>0)
		helper(left-1, right, item+"(", res);
	if(right>0)
		helper(left, right-1, item+")", res);
}





Combinations
求组合 元素只要到了k个就可以返回一个结果 实现的代码跟Combination Sum非常类似而且更加简练，因为不用考虑重复元素的情况
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(n<=0 || k<=0)
	return res;
helper(n, k, 1, new ArrayList<Integer>(), res);
return res;

private void helper(int n, int k, int start, List<Integer> item, List<List<Integer>> res) {
	if(item.size()==k) {
		res.add(new ArrayList<Integer>(item));
		return;
	}
	for(int i=1; i<=n; i++) {
		item.add(i);
		helper(n, k, i+1, item, res);
		item.remove(item.size()-1);
	}
}

O(n!/k!)





Combination Sum
for循环中第一步有一个判断 为了去除重复元素产生重复结果的影响 这里每个数可以重复使用 所以重复的元素也就没有作用了 应该跳过那层递归
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(candidates==null || candidates.length==0)
    return res;
Arrays.sort(candidates);
helper(candidates, 0, target, new ArrayList<Integer>(), res);
return res;

private void helper(int[] candidates, int index, int target, List<Integer> item, List<List<Integer>> res) {
    if(target<0)
        return;
    if(target==0) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=index; i<candidates.length; i++) {
        if(i>0 && candidates[i]==candidates[i-1])
            continue;
        item.add(candidates[i]);
        helper(candidates, i, target-candidates[i], item, res);
        item.remove(item.size()-1);
    }
}





Combination Sum ii
只对于第一次得到这个数进行递归，接下来就跳过这个元素了，因为接下来的情况会在上一层的递归函数被考虑到 ex 1 1 2
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(num==null || num.length==0)
    return res;
Arrays.sort(num);
helper(num, 0, target, new ArrayList<Integer>(), res);
return res;

private void helper(int[] num, int start, int target, List<Integer> item, List<List<Integer>> res) {
    if(target==0) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(target<0 || start>=num.length)
        return;
    for(int i=start; i<num.length; i++) {
        if(i>start && num[i]==num[i-1])
            continue;
        item.add(num[i]);
        helper(num, i+1, target-num[i], item, res);
        item.remove(item.size()-1);
    }
}













