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