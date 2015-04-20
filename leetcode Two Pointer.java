Valid Palindrome
while(left<right) {
	if(!isValid(s.charAt(left))) {
		left++;
		continue;
	}
	if(!isValid(s.charAt(right))) {
		right--;
		continue;
	}
	if(!isSame(s.charAt(left), s.charAt(right)))
		return false;
	left++;
	right--;
}

isValid(char c)
	if('a-z') || ('A-Z') || ('0-9')
		return true;
	return false;

isSame(char c1, char c2)
	Character.toLowerCase(c1)==Character.toLowerCase(c2)

时间O(n) 空间O(1)





Two Sum ii - input array is sorted
while l<r
	if(numbers[l]+numbers[r]==target)
		res[0] = l+1;
		res[0] = r+1;
		return res;
	else if ...
		r--;
	else
		l++;

时间O(n) 空间O(1)





