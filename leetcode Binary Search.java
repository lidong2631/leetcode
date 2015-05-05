Two Sum ii - Input array is sorted
这题假设数组已排好序 可以直接用两边夹逼的方法做出 
while(left<right)
    if(numbers[left]+numbers[right]==target)
        保持结果 返回
    else if >target
        right--;
    else
        left++;
return res;

时间O(n) 空间O(1)




Sqrt(x)
if(x<0)
	return -1;
if(x==0)
	return 0;
int left = 1, right = x/2+1;
while(left<=right) {
	int mid = (left+right)/2;
	if(x/mid>=mid && x/(mid+1)<mid+1)
		return mid;
	else if(x/mid<mid)
		right = mid - 1;
	else
		left = mid + 1;
}
return right;

O(logn) O(1)





Search Insert Position
code ganker版比cleanCode版好理解
while(left<=right) {
    int mid = (left+right)/2;
    if(A[mid]==target)
        return mid;
    else if(A[mid]>target)
        right = mid - 1;
    else if(A[mid]<target)
        left = mid + 1;
}
return left;

O(logn) O(1)





Find Minimum in Rotated Array i
二分法 看cleanCode图 下面四个题都是基于同一种思路
while(left<right && num[left]>=num[right]) {
	int mid = (left+right)/2;
	if(num[mid]>num[right])
		left = mid + 1;
	else
		right = mid;
}
return num[left];

O(logn) O(1)





Find Minimum in Rotated Array ii
对i稍微修改 但是会影响复杂度
while(left<right && num[left]>=num[right]) {
	int mid = (left+right)/2;
	if(num[mid]>num[right])
		left = mid + 1;
	else if(num[mid]<num[right])
		right = mid;
	else
		right--;
}
return num[left];

O(logn)最差O(n) O(1)





Search in Rotated sorted array
while(left<=right) {
	int mid = (left+right)/2;
	if(A[mid]==target)
		return mid;
	if(A[mid]>A[right]) {
		if(target>=A[left] && target<A[mid])
			right = mid - 1;
		else
			left = mid + 1;
	}
	else {
		if(target>A[mid] && target<=A[right])
			left = mid +　1;
		else
			right = mid - 1;
	}
}
return -1;

O(logn) O(1)





Search in Rotated sorted array ii
while(left<=right) {
	int mid = (left+right)/2;
	if(A[mid]==target)
		return true;
	if(A[mid]>A[right]) {
		if(target>=A[left] && target<A[mid])
			right = mid - 1;
		else
			left = mid + 1;
	}
	else if(A[mid]<A[right]) {
		if(target>A[mid] && target<=A[right])
			left = mid +　1;
		else
			right = mid - 1;
	}
	else
		right--;
}
return false;

O(logn)最差O(n) O(1)





Search for a range
int[] res = new int[2];
res[0] = -1; res[1] = -1;
int mid = 0;
int left = 0, right = A.length-1;
while(left<=right) {
    mid = (left+right)/2;
    if(A[mid]==target)
        break;
    else if(A[mid]>target)
        right = mid - 1;
    else
        left = mid + 1;
}
if(left>right)
    return res;
left = 0; right = mid-1;
while(left<=right) {
    mid = (left+right)/2;
    if(A[mid]==target)
        right = mid - 1;
    else
        left = mid + 1;
}
res[0] = left;
left = mid + 1; right = A.length-1;
while(left<=right) {
    mid = (left+right)/2;
    if(A[mid]==target)
        left = mid + 1;
    else
        right = mid - 1;
}
res[1] = right;
return res;

O(logn) O(1)





Search in a 2D matrix
int left=0, right = matrix.length-1;
while(left<=right) {
    int mid = (left+right)/2;
    if(matrix[mid][0]==target)
        return true;
    else if(matrix[mid][0]>target)
        right = mid - 1;
    else
        left = mid + 1;
}
if(right<0)
    return false;
int row = right;
left = 0; right = matrix[0].length-1;
while(left<=right) {
    int mid = (left+right)/2;
    if(matrix[row][mid]==target)
        return true;
    else if(matrix[row][mid]>target)
        right = mid - 1;
    else
        left = mid + 1;
}
return false;

O(logn) O(1)





Pow(x, n)
二分递归 此解法没考虑越界
if(n==0)
	return 1.0;
double half = myPow(x, n/2);
if(n%2==0)
	return half*half;
else if(n>0)
	return half*half*x;
else
	return half*half/x;

O(logn) O(1)





Median of Two Sorted Array
private double helper(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k) {
    int m = r1-l1+1;
    int n = r2-l2+1;
    if(m>n)
        return helper(nums2, l2, r2, nums1, l1, r1, k);
    if(m==0)
        return nums2[l2+k-1];
    if(k==1)
        return Math.min(nums1[l1], nums2[l2]);
    int pos1 = Math.min(k/2, m);
    int pos2 = k-pos1;
    if(nums1[l1+pos1-1]==nums2[l2+pos2-1])
        return nums1[l1+pos1-1];
    else if(nums1[l1+pos1-1]<nums2[l2+pos2-1])
        return helper(nums1, l1+pos1, r1, nums2, l2, l2+pos2-1, k-pos1);
    else
        return helper(nums1, l1, l1+pos1-1, nums2, l2+pos2, r2, k-pos2);

O(logk) O(logk)





Find Peak Element
int left = 0, right = num.length-1;
while(left<right) {
	int mid = (left+right)/2;
	if(num[mid]<num[mid+1])
		left = mid + 1;
	else
		right = mid;
}
return num[left];

O(logn) O(1)





Divide Two Integers
以2的幂为底的一组基
if(dividend==Integer.MIN_VALUE && divisor==-1)
    return Integer.MAX_VALUE;
if(divisor==0)
    return Integer.MAX_VALUE;
int res = 0;
if(dividend==Integer.MIN_VALUE) {
    res = 1;
    dividend+=Math.abs(divisor);
}
if(divisor==Integer.MIN_VALUE)
    return res;
boolean sign = ((dividend^divisor)>>>31)==1;
dividend = Math.abs(dividend);
divisor = Math.abs(divisor);
int digit = 0;
while((dividend>>1)>=divisor) {
    divisor<<=1;
    digit++;
}
while(digit>=0) {
    if(dividend>=divisor) {
        dividend-=divisor;
        res+=(1<<digit);
    }
    divisor>>=1;
    digit--;
}
return sign?-res:res;

O(logn) O(1)