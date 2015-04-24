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





Container With Most Water
求两条垂直x轴的线和x轴围成的container里能乘的最大水量
while left<right
    int diff
    if diff>0
        int localWater = (right-left)*height[left];
        maxWater = maxWater<localWater ? localWater:maxWater;
        left++;
    else
        同上 right

时间O(n) 空间O(1)





Trapping Rain Water
上一题的进阶版 不是和x轴围 而是和中间其他高度围

1 第一种扫两遍 先左扫一遍 再右扫一遍
for(int i=1; i<A.length-1; i++) {
    leftMax = Math.max(leftMax, A[i-1]);
    maxL[i] = leftMax;
}
int rightMax = 0;
for(int i=A.length-2; i>0; i--) {
    rightMax = Math.max(rightMax, A[i+1]);
    water+=Math.min(maxL[i], rightMax)>A[i]?Math.min(maxL[i], rightMax)-A[i]:0;
}

时间O(2*n)=O(n) 空间O(n)

2 只需要扫一次数组 比较左右指针 取小的那边开始走 如果下一个元素更小就把差值加到结果中 否则重新比较左右指针大小 
while(left<right) {
    int minHeight = Math.min(A[left], A[right]);
    if(A[left]==minHeight) {
        left++;
        while(left<right && A[left]<=minHeight) {
            water+=minHeight-A[left];
            left++;
        }
    }
    else {
        right 同上
    }

时间O(n) 空间O(1) 优于上一解法





Sort Colors

1 计数排序
int[] c = new int[3];
int[] res = new int[A.length];
for int i=0; i<A.length; i++
    c[A[i]]+=1;
for int i=1; i<3; i++
    c[i]+=c[i-1];
for int i=A.length-1; i>=0; i--
    res[c[A[i]]-1] = A[i];
    c[A[i]]--;
for int i=0; i<A.length; i++
    A[i] = res[i];

2 
int index1 = 0, index2 = 0;
for(int i=0; i<A.length; i++)
    if(A[i]==0)
        A[i]=2;
        A[index1++] = 1;
        A[index0++] = 0;
    else if(A[i]==1)
        A[i]=2;
        A[index1++] = 1;

时间O(n) 空间O(1)





Rotate List
ListNode dummy = new ListNode(0);
ListNode p = dummy;
while(p.next!=null)
	p = p.next;
	len++;
n%=len;
p.next = head //////
for(int i=0; i<len-n; i++)
	dummy = dummy.next;
ListNode newHead = dummy.next;
dummy.next = null;
return newHead;

corner case n大于链表长度
时间O(n) 空间O(1)





Remove Nth Node From End Of List
int i=0;
while(p1!=null &&　i<n) {
	p1 = p1.next;
	i++;
}
if(i<n)
	n%=i;
if(p1==null)
	return head.next;
ListNode p2 = head;
while(p1.next!=null) {
	p1 = p1.next;
	p2 = p2.next;
}
p2.next = p2.next.next;