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
注意这题的扩展 数字出现n次 只有一个出现一次 https://oj.leetcode.com/discuss/857/constant-space-solution?show=2542#a2542
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





Reverse Bits
位运算操作
1 基本解法 每次将结果左移以为 然后取出元数字1位添加到结果中
int i = 0, res = 0;
while(i<32) {
	res = (res<<1) + ((n>>1)&1);
	i++
}
return res;

O(n) O(1)

2 优化的做法是不按位转 而是按字节转 将一字节的数存到hashmap中 再出现重复的直接取就好了
private final Map<Byte, Integer> map = new HashMap<Byte, Integer>();

public int reverseBits(int n) {
	Byte[] bytes = new Byte[4];
	for(int i=0; i<4; i++)
		bytes[i] = (Byte)((n>>>8*i)&0xFF);
	int res = 0;
	for(int i=0; i<4; i++) {
		res+=reverseByte(byte[i]);
		if(i<3)
			res<<=8;
	}
	return res;
}

private int reverseByte(Byte b) {
	if(map.containsKey(b))
		return map.get(b);
	int res = 0;
	for(int i=0; i<8; i++){
		res+=((b>>i)&1);
		if(i<7)
			res<<=1;
	}
	map.put(b, res);
	return res;
}

O(n) O(1)





Repeated DNA Sequences
rolling hash 位操作+HashMap
List<String> res = new ArrayList<String>();
if(s==null || s.length()<10)
	return res;
int mask = 0x3FFFF;
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
int sequence = toInt(s.substring(0, 10));
map.put(sequence, 1);
for(int i=10; i<s.length(); i++) {
	sequence = (sequence&mask)<<2 | toInt(s.charAt(i));
	if(map.containsKey(sequence)) {
		if(map.get(sequence)==1))
			res.add(s.substring(i-9, i+1));
		map.put(sequence, map.get(sequence)+1);
	}
	else
		map.put(sequence, 1);
}
return res;

private int toInt(String s) {
	int res = 0;
	for(int i=0; i<s.length(); i++)
		res = (res<<2) | toInt(s.charAt(i));
	return res;
}

private int toInt(char c) {
	if(c=='A')
		return 0;
	if(c=='C')
		return 1;
	if(c=='G')
		return 2;
	else
		return 3;
}

O(n) O(n)





Power of Two
if(n==0 || n==Integer.MIN_VALUE)
	return false;
return (n&(n-1))==0;

O(1) O(1)




Number of 1 Bits
1 直观的解法 判断每一位是否为1 并累加
int count = 0;
int mask = 0x1;
for(int i=0; i<32; i++) {
	if((n & mask)==1)
		count++;
	n>>=1;
}
return count;

循环32次

2 简便的算法 利用n&(n-1)可以消去最右边1即其右边的数位 累计次数直到n等于0
int count = 0;
while(n!=0) {
	n = n&(n-1);
	count++;
}

有几个1循环几次





Majority Element
Moore voting algorithm
if we cancel each occurence of an element e with other elements that are different from e then e will exist till end if it
is majority element

int curr = 0;
int count = 0;
for(int i=0; i<num.length; i++) {
	if(count==0) {
		curr = num[i];
		count++;
	}
	else if(curr==num[i])
		count++;
	else
		count--;
}
counter = 0;
for(int i=0; i<num.length; i++) {
	if(num[i]==curr)
		counter++;
}
if(count<=num.length/2)
	return -1;
return curr;

O(n) O(1)





Bitwise AND of Numbers Range
最后的数是该数字范围内所有的数的左边共同1的部分
int mask = 0xFFFFFFFF;
while((m&mask)!=(n&mask)) {
	mask<<=1;
}
return mask&m;

O(n) O(1)



