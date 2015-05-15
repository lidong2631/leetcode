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















