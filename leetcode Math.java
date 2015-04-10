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





Reverse Integer
while x!=0
	if Math.abs(ret)>214743864
		return 0;
	ret = 10*ret + x%10;
	x/=10;

时间O(n) 空间O(1)





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