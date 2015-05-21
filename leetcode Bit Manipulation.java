leetcode Bit Manipulation

Subsets
无重复数据
1 iterative
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

2 recursive
if(S==null)
    return null;
Arrays.sort(S);
return helper(S, S.length);
}

private List<List<Integer>> helper(int[] S, int index) {
	if(index==0) {
	    List<List<Integer>> res = new ArrayList<List<Integer>>();
	    List<Integer> item = new ArrayList<Integer>();
	    res.add(item);
	    return res;
	}
	List<List<Integer>> res = helper(S, index-1);
	int size = res.size();
	for(int i=0; i<size; i++) {
	    List<Integer> newItem = new ArrayList<Integer>(res.get(i));
	    newItem.add(S[index-1]);
	    res.add(newItem);
	}
	return res;
}

O(2^n) 





Single Number
利用xor运算符可以应付出现2次的特殊情况
int num = 0;
for(int x : A) {
	num^=x;
}
return num;

O(n) O(1)





Single Number ii
http://www.geeksforgeeks.org/find-the-element-that-appears-once/
int ones = 0, twos = 0, common = 0;
for(int i=0; i<A.length; i++) {
	twos |= ones&A[i];
	ones^=A[i];
	common = ones&twos;
	ones&=common;
	twos&=common;
}
return ones;

O(n) O(1)