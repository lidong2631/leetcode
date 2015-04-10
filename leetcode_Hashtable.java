 Longest Substring Without Repeating Characters
窗口思路 如果是ASCII
1
用一个256 boolean数组标记是否重复
for i=0； i<S.length(); i++
	while exists[S.charAt(i)] 只要重复字符
		exists[j] = false
		j++;
	exists[i] = true 不是重复 设为true
	maxLen = Math.max(maxLen, i-j+1);
return maxLen

2
上一种解法要扫两遍 可以只扫一遍 数组不标记是否重复 而是存字符的索引
int charMap[] = new int[256];
Arrays.fill(charMap, -1);
int j=0;
for i=0; i<S.length(); i++
	if charMap[S.charAt(i)]>=j 重复字符出现
		j = charMap[S.charAt(i)]+1; j跳到这个字符的下一位
	charMap[S.charAt(i)] = j;	非重复就更新charMap
	更新maxLen
return maxLen

如果不是ASCII 就要用set替代256数组 

时间O(n) 空间O(1) 如果用set就要O(n)





Longest Substring with At Most Two Distinct Characters
for i=0; i<s.length(); i++
	if(count[s.charAt(j)]==0)  
        numDistinct++;
    count[s.charAt(j)]++;
    while(numDistinct>2) {  
        count[s.charAt(i)]--;
        if(count[s.charAt(i)]==0)
            numDistinct--;
        i++;
    }
    maxLen = Math.max(maxLen, j-i+1);
}
return maxLen;

时间O(2*n) 空间O(1)





Minimum Window Substring
窗口思路
HashMap<Character, Integer> map 记录T中每个字符出现的次数
for T
	map初始化赋值
for i=0; i<S.length(); i++
	if map中有这个字符
		map中对应值-1
		if 减完后map中对应值>=0 则这个不是重复多余字符
			count要加1
		while count等于T的长度 可以左移窗口
			if map包含这个字符
				更新map中的值
				if 更新map值后>0 则压缩到头
					if 得到这个结果 i-leftP+1<minLen
						更新minLen和结果
					count--因为有效字符被移除一个
			leftP++
return res

时间O(n) 空间O(T)





Substring with Concatenation of All Words
窗口思路
HashMap<String, Integer> map 记录L中对应单词出现次数
for T
	map初始化赋值
for i=0; i<L[0].length(); i++ 外循环
	HashMap<String, Integer> currMap 记录当前得到的字典中的单词的出现次数
	int count = 0, left = i; count记录当前循环已得到的字典中词的个数
	for j=i; j<=S.length()-L[0].length(); j+=L[0].length()		内循环
		拿到当前字符串currStr = S.substring(j, j+L[0].length());
		if map中有currStr	
			更新currMap中的值 if currMap.get(currStr)+1
							  else	currMap.put(currStr, 1)
			if currMap中的值<=Map中的值
				count++
			else
				while currMap中的值>Map中的值 就要一直左移窗口直到把这个多余字符去掉
					拿到left指的单词tmpstr = S.substring(left, left+L[0].length()) 将tmpStr在currMap中的值-1 更新count left右移一个单词
			if count等于L长度
				找到一个解 加入结果 更新currMap count 右移left
		else map中没有
			count=0 清空currMap left右移到j+L[0].length()

时间O(2*n/L) 每一次循环最多左右窗口各扫一次 一共L次所以总时间为O(2*n/L*L)=O(n) 空间O(m*L)





Single Number i ii
位运算
对于i可以用异或解 更通用的解法利用位运算
int[32] digits
for(i=0; i<32; i++)
	for(j=0; j<A.length; j++)
		digits[i] += (A[j]>>i)&1;
int res
for(int i=0; i<32; i++)
	res+=(digits[i]%n(<<i;





Fraction to Recurring Decimal
位运算
if numerator==0, denominator==0 判断0

StringBuffer res

if((numerator<0)^(denominator<0)) 判断是否异号
	res.append("-")

long num, long den 转long 有可能是最小负数-2147483648

integral = num/den 将整数位加入结果
res.append(integral)

res.append(".") 加小数点

HashMap<Long, Integer> map map记录每一个余数和它对应的位置 当出现重复余数即得到循环小数

while(remainder!=0)
	if map.containsKey(remainder)
		return res.substring(0, map.get(remainder)) + "(" + res.substring(map.get(remainder), res.length()) + ")";
	map.put(remainder, res.length());
	res.append(remainder/den);
	remainder = (remainder%den)*10;
return res.toString();

时间O(n) 空间O(n)





Repeated DNA Sequences
位运算 用2位二进制数表示A C G T这四个字符
int mask = 0x3FFFF, sequence=toInt(s.substring(0, 10));
HashMap<Integer, Integer> map 保存对应二进制序列出现的次数
map.put(sequence, 1)
for i=10; i<s.length(); i++
	sequence = (sequence&mask)<<2|toInt(s.charAt(i));
	if(map.containsKey(sequence)) {
		if map.get(sequence)==1
			add to result
		map.put(sequence, map.get(sequence)+1);
	}
	else
		map.put(sequence, 1);

时间O(n) 空间O(n)





Max Points on a Line
几何运算题 
for i=0; i<points.length-1; i++
	localMax=1; numSame=0; double ratio=0.0; HashMap<Double, Integer> map
	for(j=i+1; j<points.length; j++)
		if 相同点
			numSame++;
			continue;
		else if y相等 ratio=
		else if x相等 ratio=
		else ratio=
		if map.containsKey(ratio)
		else map.put(ratio, 2)
	更新localMax, globalMax
return globalMax

时间O(n^2) 空间O(n)哈希表长度





Valid Sudoku
for i=0; i<9; i++
	Boolean[] map = new Boolean[9];
	for j=0; j<9; j++
		if not board[i][j] '.'
			if map[(int)(board[i][j]-'1')]
				return false;
			else map set true

验证列部分同上

for block=0; block<9; block++
	Boolean[] map = new Boolean[9];
	for i=block/3*3; i<block/3*3+3; i++
		for j=block%3*3; j<block%3*3+3; j++
			if not board[i][j] '.'
				if map[(int)(board[i][j]-'1')]
					return false;
				else map set true
注意判断小格子对于列是用i=block/3*3 而对于行是用j=block%3*3 
时间O(3*9^2)对于81个格子判断了3次





Sudoku Solver
NP问题
boolean helper(board[][], int i, int j) {
	if(j==9)
		return helper(board, i+1, 0);
	if(i==9)
		return true;
	if(board[i][j]=='.') {
		for k 1-9
			board[i][j] = (char)('0'+k);
			if(isValid(board, i, j)) {
				if(helper(board, i, j+1))
					return true;
			}
		board[i][j]='.';
	}
	else
		return helper(board, i, j+1);
	return false;
}

isValid(board[][], int row, int col) {
	char tmp =board[row][col];
	board[row][col] = '#';
	判断行列
	for(int i=row/3*3; i<row/3*3+3; i++) {
		for(int j=col/3*3; j<col/3*3+3; j++) {
			if(board[i][j]==tmp)
				return false;
		}
	}
	board[row][col] = tmp;
	return true;
}




Anagrams
HashMap<String, List<String>> map 键值是排序后的string value是属于这个gourp的所有string
for i=0; i<strs.length; i++
	String转charArray sort 再转回String
	if map.containsKey()
		加入map
	else
		新建entrySet在map
输出size()>1的value

时间O(nklogk) 空间O(nk)





Binary Tree Inorder Traversal
1 递归
public void helper(root, res) {
	if(root!=null) {
		helper(root.left, res);
		res.add(root);
		helper(root.right, res);
	}
}

2 迭代
while(!stack.empty() || root!=null) {
	if(root!=null) {
		stack.push(root);
		root = root.left;
	}
	else {
		root = stack.pop();
		res.add(root.val);
		root = root.right;
	}
}

3 Morris
while(curr!=null) {
	if(curr.left!=null) {	//当前节点左节点不为空
		pre = curr.left;
		while(pre.right!=null && pre.right!=curr)
			pre = pre.right;	//得到前继节点
		if(pre.right==null) {	//前继节点右孩子为空
			pre.right = curr;
			curr = curr.left;
		}
		else {				//前继节点右孩子为当前节点
			pre.right = null;
			res.add(curr.val);
			curr = curr.right;
		}
	}
	else {				//当前节点左节点为空
		res.add(curr.val);
		curr = curr.right;
	}
}







Copy List with Random Pointer
1 哈希表解法
HashMap<RandomListNode, RandomListNode> map 用一个哈希表映射原节点和copy节点
newHead p1 = head.next
p2 = newHead;
while(p1!=null)
	newNode = new RandomListNode(p1.label)	copy节点 并建立对应关系
	p2.next = newNode;
	map.put(p1, newNode);
	p1 = p1.next;
	p2 = p2.next;
while(p1!=null)		利用map拷贝random指针
	p2.random = map.get(p1.random);
	p1 = p1.next; p2 = p2.next;

时间O(n) 空间O(n)

2 不用哈希表
将每个节点copy一份接在后面
while(p1!=null)
	RandomListNode newNode = new RandomListNode(node.label);
	newNode.next = p1.next;
	p1.next = newNode;
	p1 = newNode.next;

copy random指针
while(p1!=null)
	if(p1.random!=null)
		p1.next.random = p1.random.next;
	p1 = p1.next.next;

拆分指针
while(p1!=null)
	p1.next = p2.next;
	while(p2.next!=null)
		p2.next = p2.next.next;
		p2 = p2.next;
	p1 = p1.next;

时间O(n) 空间O(1)




















