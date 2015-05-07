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
