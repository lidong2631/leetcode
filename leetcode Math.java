Valid Number
顺序： whitespace->'+/-'->isNumeric->'.'->digit->'e'->'+/-'->digit->whitespace-> isNumeric&&i==n

while i<n && isWhitespace
	i++;
if i<n && '+'/'-'
	i++;
boolean isNumeric = false;
while i<n && isDigit {
	isNumeric = true;
	i++;
}
if i<n &&　'.'
	i++;
	while i<n && isDigit
		isNumeric = true;
		i++;
if i<n && isNumeric && 'e'
	i++
	if i<n && '+'/'-'
		i++
	isNumeric = false;
	while i<n && isDigit
		isNumeric = true;
		i++；
while i<n && isWhitespace
	i++;
return isNumeric && i==n;

时间O(n) 空间O(1)





String to Integer(atoi)
顺序: whitespace->'+/-'->digit->>maxDiv10->sign*num

maxDiv10 = Integer.MAX_VALUE/10;

while i<n && isWhitespace
	i++;
if i<n &&　'+'
	i++;
else if i<n && '-'
	i++;
	sign = -1;
while i<n && isDigit
	digit = Character.getNumericValue(s.charAt(i));
	if(num>maxDiv10 || (num==maxDiv10 && digit>=8))
		return sign==1? Integer.MAX_VALUE:Integer.MIN_VALUE;
	num = 10*num+digit;
	i++;
return sign*num；

时间O(n) 空间O(1)





Sqrt(x)
二分法

left = 1, right = x/2+1;
while left<=right
	mid = (left+right)/2;
	if(x/mid>=mid && x/(mid+1)<mid+1)
		return mid;
	else if(x/mid<mid)
		right = mid - 1;
	else if(x/(mid+1)>mid+1)
		left = mid + 1;
return right;

时间O(logn) 空间O(1)





Roman to Integer
for i=0; i<s.length(); i++
	switch(s.charAt(i))
		case 'I':
			if(i<s.length()-1 && (s.charAt(i+1)=='V' || s.charAt(i+1)=='X'))
				res-=1;
			else
				res+=1;
			break;
		case 'V':
			res+=5;
		case 'X:':
		case 'L':
		case 'C':
		case 'D':
		case 'M':
return res;
时间O(n) 空间O(1)





Integer to Roman
int digit = 1000;
List<Integer> tmp = new ArrayList<Integer>();
while(digit>0)
	tmp.add(num/digit);
	num%=digit;
	digit/=10;
StringBuilder res = new StringBuilder();
res.add(convert(tmp.get(0), '', '', 'M'));
...
...
...
return res.toString();

convert(int digit, int ten, int five, int one) {
	StringBuilder sb = new StringBuilder();
	switch(digit) {
		case '9':
		case '8':case'7':case'6':case'5':
		case '4':
		case '3':case'2':case'1':
		case '0':
	}
	return sb.toString();
}

时间O(整数位数) 空间O(1)





Reverse Integer
while x!=0
	if Math.abs(ret)>214743864
		return 0;
	ret = 10*ret + x%10;
	x/=10;

时间O(n) 空间O(1)





Plus One
for i=digits.length-1; i>=0; i--
    if(digits[i]<9) {
        digits[i]+=1;
        return digits;
    }
    else
        digits[i] = 0;
int[] res = new int[digits.length+1];
res[0] = 1;

时间O(n) 空间一般是O(1) 最坏为O(n)





Permutation Sequence
k--;
StringBuilder res = new StringBuilder();
List<Integer> num = new ArrayList<Integer>();
int fac = 1;
for i=2; i<n; i++
	fac*=n;
for i=1; i<n; i++
	num.add(i);
round = n-1;
while(round>=0)
	int index = k/fac;
	k%=fac;
	res.append(index);
	num.remove(index);
	if(round>0)
		fac/=round;
	round--;
时间O(n^2) 空间O(n)





Palindrome Number
if x<0
	return false;
div = 1;
while x/div>=10
	div*=10;
while x!=0
	if(x%10!=x/div)
		return false;
	x = (x%div)/10；
	div/=100;

时间O(n) 空间O(1)





Multiply Strings
for i=num1.length()+num2.length(); i>0; i--
	for j=Math.min(i-1, num1.length(); j>0; j--)
		if(i-j<=num2.length())
			num+=(int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-j-1)-'0');
	if(num!=0 || i!=1)
		res.append(num%10);
	num/=10;
return res.reverse().toString();

时间O(n) 空间O(1)





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




Divide Two Integers
1 被除数减除数 直到小于等于0 时间O(n)

2 以2的幂为底一组基德线性组合
if divisor==0 
	return Integer.MAX_VALUE
res = 0;
if dividend==Integer.MIN_VALUE
	res = 1;
	dividend+=Math.abs(divisor);
if divisor==Integer.MIN_VALUE
	return res;
boolean sign = ((dividend&divisor)>>31)==1;
int digit = 0;
Math.abs(dividend); Math.abs(divisor);
while (dividend>>1)>=divisor
	divisor<<=1;
	digit++;
while(digit>=0)
	if(dividend>=divisor)
		dividend-=divisor;
		res+=(1<<digit);
	divisor>>=1;
	digit--;
return sign?-res:res;

时间O(logn) 空间O(1)





Pow(x, n)
同样是Divide Two Integers的解法 以2的幂为指数n的底
if n==1 return 1.0;
res = 1.0;
if n<0
	if x>=1/Double.MAX_VALUE ||　x<=-1/Double.MAX_VALUE
		x = 1.0/x;
	else return Double.MAX_VALUE;
	if n==Integer.MIN_VALUE
		res*=x;
		n++;
n = Math.abs(n);
boolean sign = (n%2==1 &&　x<0)
x = Math.abs(x);
while n>0
	if n&1==1
		if(res>Double.MAX_VALUE/x)
			return Double.MAX_VALUE;
		res*=x;
	x+=x;
	n>>=1;
return sign?-res:res;

时间O(logn) 空间O(1)





Add Two Numbers
while(l1!=null && l2!=null)
	val = (l1.val+l2.val+carry)%10;
	carry = (l1.val+l2.val+carry)/10;
	p.next = new ListNode(val);
	p = p.next; l1 = l1.next; l2 = l2.next;
while(l1!=null)

while(l2!=null)

if(carry!=0)
	p.next = new ListNode(carry);

时间O(n) 空间O(1)





Add Binary
while(indexA>=0 && indexB>=0)
	int num = carry + (int)(a.charAt(indexA)-'0') + (int)(b.charAt(indexB)-'0');
    carry = num/2;
    num%=2;
    res.append(num);
    indexA--; indexB--; 
while indexA>=0
while indexB>=0
if carry==1
	res.append(1);
return res.reverse().toString();