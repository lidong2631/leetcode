Valid Number
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
