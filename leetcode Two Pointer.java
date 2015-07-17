Valid Palindrome
夹逼比较
int left = 0, right = s.length()-1;
while(left<right) {
    while(left<right && !Character.isLetterOrDigit(s.charAt(left))) //注意这里只判断字符和数字
        left++;
    while(left<right && !Character.isLetterOrDigit(s.charAt(right)))
        right--;
    if(Character.toLowerCase(s.charAt(left))!=Character.toLowerCase(s.charAt(right)))
        return false;
    left++;
    right--;
}
return true;

时间O(n) 空间O(1)





Two Sum ii - input array is sorted
夹逼比较
while(l<r) {
	if(numbers[l]+numbers[r]==target) {
		res[0] = l+1;
		res[0] = r+1;
		return res;
    }
	else if(numbers[l]+numbers[r]>target)
		r--;
	else
		l++;
}
时间O(n) 空间O(1)





3Sum
Arrays.sort(num)
for(int i=num.length-1; i>=2; i--) {
    if(i<num.length-1 && num[i]==num[i+1])
        continue;
    List<list<Integer> TwoSum = TwoSum(num, i-1, -num[i]);
    for(int j=0; j<TwoSum.size(); j++)
        TwoSum.get(j).add(num[i]);
    res.addAll(TwoSum);
}

while(left<right) {
    if(num[left]+num[right]==target) {
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(num[left]);
        tmp.add(num[right]);
        res.add(tmp);
        left++; right--;
        while(left<right && num[left]==num[left-1])
            left++;
        while(left<right && num[right]==num[right+1])
            right--;
    }
    else if(num[left]+num[right]<target)
        left++;
    else
        right--;
    return res;
}

时间O(n^2) 空间O(n)





3Sum Closest
int closest = nums[0]+nums[1]+nums[2];
int minDiff = closest - target;
Arrays.sort(nums);
for(int i=0; i<nums.length-2; i++) {
    int localDiff = helper(nums, i+1, target-num[i]);
    minDiff = Math.abs(minDiff)<Math.abs(localDiff)? minDiff:localDiff;
}
return minDiff+target;

private helper(int[] nums, int start, int target) {
    int left = start, right =nums.length-1;
    int minDiff = nums[start]+nums[start+1]-target;
    while(left<right) {
        if(nums[left]+nums[right]==target)
            return 0;
        int localDiff = nums[left]+nums[right]-target;
        if(Math.abs(localDiff)<Math.abs(minDiff))
            minDiff = localDiff;
        if(localDiff>0)
            right--;
        else
            left++;
    }
    return minDiff;
}

时间O(n^2) 空间O(n)





Container With Most Water
夹逼
求两条垂直x轴的线和x轴围成的container里能乘的最大水量
while(left<right) {
    int diff = height[right]-height[left];
    if(diff>0) {
        int localWater = (right-left)*height[left];
        maxWater = maxWater<localWater ? localWater:maxWater;
        left++;
    }
    else {
        int localWater = (right-left)*height[right];
        maxWater = maxWater<localWater ? localWater:maxWater;
        right--;
    }
}
return maxWater;

时间O(n) 空间O(1)





Trapping Rain Water
夹逼
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
        right--;
        while(left<right && A[right]<=minHeight) {
            water+=minHeight-A[right];
            right--;
        }
    }
}
return water;

时间O(n) 空间O(1) 优于上一解法





Sort Colors

1 计数排序
int[] c = new int[3];
int[] res = new int[A.length];
for(int i=0; i<A.length; i++)
    c[A[i]]+=1;
for(int i=1; i<3; i++)
    c[i]+=c[i-1];
for(int i=A.length-1; i>=0; i--) {
    res[c[A[i]]-1] = A[i];
    c[A[i]]--;
}
for(int i=0; i<A.length; i++)
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
先计算长度 再走到length－n的位置
if(head==null)
    return null;
ListNode p = new ListNode(0);
p.next = head;
int len = 0;
while(p.next!=null) {
    p = p.next;
    len++;
}
n%=len;
p.next = head;
for(int i=0; i<len-n; i++)
    p = p.next;
head = p.next;
p.next = null;
return head;
时间O(n) 空间O(1)





Remove Nth Node From End Of List
p1 p2相隔n 然后一起走到p1.next==null 即是要移除点
int i=0;
while(p1!=null &&　i<n) {
	p1 = p1.next;
	i++;
}
if(i<n)
	return head;
if(p1==null)
	return head.next;
ListNode p2 = head;
while(p1.next!=null) {
	p1 = p1.next;
	p2 = p2.next;
}
p2.next = p2.next.next;

时间O(n) 空间O(1)





Remove Element
双指针从后往前扫 i碰到elem元素就赋j指向的值给它
int j = A.length-1;
for(int i=A.length-1; i>=0; i--) {
    if(A[i]==elem)
        A[i] = A[j--];
}
return j+1;

时间O(n) 空间O(1)





Remove Duplicates From Sorted Array i ii
双指针解法 维护指针index之前的元素均是合法 若A[i]也是合法更新index 否则index不动 可以直接写扩展成n的写法
if(A.length<n)
    return A.length;
int index = n;
for(int i=n; i<A.length; i++) {
    if(A[i]!=A[index-n])
        A[index++] = A[i];
}
return index;

时间O(n) 空间O(1)





Partition List
双指针 一个指小于x的值 一个指大于等于x的值
ListNode p1 = new ListNode(0), head1 = p1;
ListNode p2 = new ListNode(0), head2 = p2;
ListNode p = head;
while(p!=null) {
    if(p.val<x) {
        p1.next = p;
        p = p.next;
        p1 = p1.next;
        p1.next = null; //
    }
    else {

    }
}
p1.next = head2.next;
return head1.next;

时间O(n) 空间O(1)




Palindrome Linked List
先将链表前半reverse 再逐个比较
if(head==null || head.next==null)
    return true;
int len = 0;
ListNode curr = head;
while(curr!=null) {
    curr = curr.next;
    len++;
}
curr = head.next;
ListNode prev = head;
head.next = null;
for(int i=0; i<len/2-1; i++) {
    ListNode tmp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = tmp;
}
if(len%2==1)
    curr = curr.next;
while(curr!=null) {
    if(prev.val!=curr.val)
        return false;
    prev = prev.next;
    curr = curr.next;
}
return true;

O(n) O(1)




Minimum Window Substring
双指针 右指针先走 当左右指针之间包含了所有T中的字符 左指针开始压缩 压缩到底后如果小于minLen则更新 如此下去直到右指针到S的末尾
for(int i=0; i<T.length(); i++) {
    if(map.containsKey(T.charAt(i)))
        map.put(T.charAt(i), map.get(T.charAt(i))+1);
    else
       map.put(T.charAt(i), 1);
}
for(int i=0; i<S.length(); i++) {
    if(map.containsKey(S.charAt(i)))
        map.put(S.charAt(i), map.get(S.charAt(i))-1);
        if(map.get(S.charAt(i))>=0)
            count++;
    while(count==T.length()) {
        if(map.containsKey(S.charAt(leftP))) {
            map.put(S.charAt(leftP), map.get(S.charAt(leftP))+1)
            if(map.get(S.charAt(leftP))>0) {
                if(minLen>i-leftP+1) {
                    minLen = i-leftP+1;
                    res = S.substring(leftP, i+1);
                }
                count--;
            }
        }
        leftP++;
    }
}

时间O(n) 空间O(T)





Longest Substring Without Repeating Characters
双指针 一前一后
boolean[] charMap = new boolean[256];
int left = 0, maxLen = 0;
for(int right=0; right<s.length(); right++) {
    while(charMap[s.charAt(right)]) {
        charMap[s.charAt(left)] = false;
        left++;
    }
    charMap[s.charAt(right)] = true;
    maxLen = Math.max(maxLen, right-left+1);
}

时间O(n) 空间O(1)





Longest Substring With At Most Two Distinct Characters
双指针+count
int i = 0, maxLen = 0;
int[] count = new int[256]; 
int numDistinct = 0;
for(int j=0; j<s.length(); j++) {
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

时间O(n) 空间O(n)





Substring With Concatenation of All Words






Linked List Cycle
快慢指针
ListNode slow = head, fast = head;
while(fast!=null && fast.next!=null) {
    slow = slow.next;
    fast = fast.next.next;
    if(slow==fast)
        return true;
}
return false;

时间O(n) 空间O(1)





Linked List Cycle ii
两次快慢指针
ListNode slow = head, fast = head;
while(fast!=null && fast.next!=null) {
    slow = slow.next;
    fast = fast.next.next;
    if(slow==fast)
        break;
}
if(slow==fast) {
    slow = head;
    while(slow!=fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
}
return null;

时间O(n) 空间O(1)





Implement strStr()
暴力法 双指针
for(int i=0; ; i++) {
    for(int j=0; ; j++) {
        if(j==needle.length())
            return i;
        if(i+j==haystack.length())
            return -1;
        if(needle.charAt(j)!=haystack.charAt(i+j))
            break;
    }
}
时间O(n) 空间O(1)





































