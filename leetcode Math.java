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





