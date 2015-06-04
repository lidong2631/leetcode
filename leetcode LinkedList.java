Swap Nodes in Pairs
一对对翻转链表 建一个dummy节点 然后遵循四步 1 tmp记录当前组2号节点 2 当前组1号节点指向下一组1号节点 3 当前组2号节点指向1号节点 4 上一组2号节点指向当前组1号节点

if(head==null || head.next==null)
	return head;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode p = dummy;
while(p.next!=null && p.next.next!=null) {
	ListNode tmp = p.next.next;
	p.next.next = tmp.next;
	tmp.next = p.next;
	p.next = tmp;
	p = p.next.next;
}
时间O(n) 空间O(1)





Sort List
这题主要复习归并排序的链表实现 先用快慢指针找到链表中值 然后左右递归继续归并排序 最后return Merge(left, right) 时间O(nlogn) 空间O(n)
return MergeSort(head);

private ListNode MergeSort(ListNode head) {
	if(head==null || head.next==null)
		return head;
	ListNode slow = head, fast = head;
	while(fast.next!=null && fast.next.next!=null) {
		slow = slow.next;
		fast = fast.next.next;
	}
	ListNode newHead = slow.next;
	slow.next = null;
	ListNode p1 = MergeSort(head);
	ListNode p2 = MergeSort(newHead);
	return Merge(p1, p2); 
}

private ListNode Merge(ListNode l1, ListNode l2) {
	if(l1==null)
		return l2;
	if(l2==null)
		return l1;
	ListNode dummy = new ListNode(0);
	ListNode p = dummy;
	while(l1!=null && l2!=null) {
		if(l1.val<l2.val) {
			p.next = l1;
			l1 = l1.next;
			p = p.next;
		}
		else {
			p.next = l2;
			l2 = l2.next;
			p = p.next;
		}
	}
	while(l1!=null) {
		p.next = l1;
		l1 = l1.next;
		p = p.next;
	}
	while(l2!=null) {
		p.next = l2;
		l2 = l2.next;
		p = p.next;
	}
	return dummy.next;
}

O(nlogn) O(logn)





Rotate List
注意k有可能大于链表长度 要取下余
if(head==null)
	return head;
ListNode p = new ListNode(0);
p.next = head;
int len = 0;
while(p.next!=null) {
	p = p.next;
	len++;
}
n%=len;
p.next = head;
for(int i=0; i<n-len; i++)
	p = p.next;
head = p.next;
p.next = null;
return head;

时间O(n) 空间O(1)





Reverse Nodes in k group
这道题是swap nodes in pairs的扩展 处理一般情况 即分成k group
if(head==null || head.next==null)
	return head;
ListNode curr = head;
ListNode pre = new ListNode(0);
ListNode dummy = pre;
pre.next = curr;
int count = 0;
while(curr!=null) {
	ListNode next = curr.next;
	count++;
	if(count==k) {
		pre = reverse(pre, curr);
		count = 0;
	}
	curr = next;
}
return dummy.next;

private ListNode reverse(ListNode pre, ListNode end) {
	ListNode head = pre.next;
	ListNode curr = pre.next.next;
	while(curr!=end) {
		ListNode next = curr.next;
		curr.next = pre.next;
		pre.next = curr;
		curr = next;
	}
	head.next = end;
	return head;
}

时间O(2*n)=O(n) 空间O(1)





Reverse Linked List i
反转链表的两种方法 迭代和递归
1 迭代
if(head==null || head.next==null)
	return head;
ListNode second = head.next;
ListNode third = head.next.next;
second.next = head;
head.next = null;
if(third==null)
	return second;
ListNode curr = third, pre = second;
while(curr!=null) {
	ListNode next = curr.next;
	curr.next = pre;
	pre = curr;
	curr = next;
}
return pre;

O(n) O(1)

2 递归
if(head==null || head.next==null)
	return head;
ListNode curr = head;
List<ListNode> res = new ArrayList<ListNode>();
helper(curr, res);
return res.get(0);

private void helper(ListNode curr, List<ListNode>) {
	if(curr.next==null) {
		res.add(curr);
		return;
	}
	helper(curr.next, res);
	curr.next.next = curr;
	curr.next = null;
}

O(n) O(n)





Reverse Linked List ii
给定范围反转而不是整个链表反转 两种做法 1 原方法 2 reverse nodes in k group套路
原方法
if(head==null || head.next==null)
	return head;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode prev = dummy;
for(int i=0; i<m-1; i++)
	prev = prev.next;
ListNode curr = prev.next;
for(int i=0; i<n-m; i++) {
	ListNode tmp = prev.next;
	prev.next = curr.next;
	curr.next = curr.next.next;
	prev.next.next = tmp;
}
return dummy.next;

时间O(n) 空间O(1)





Reorder List
三步 1 分半 用快慢指针 2 reverse 还是老套路 3 merge 时间O(n) 空間O(1)
if(head==null || head.next==null)
    return;
ListNode slow = head, fast = head;
while(fast.next!=null && fast.next.next!=null) {
    slow = slow.next;
    fast = fast.next.next;
}
ListNode prev = slow.next;
slow.next = null;

ListNode curr = prev.next;
prev.next = null;
while(curr!=null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}

ListNode h1 = head;
ListNode h2 = prev;
while(h2!=null) {
    ListNode tmp1 = h1.next;
    ListNode tmp2 = h2.next;
    h1.next = h2;
    h2.next = tmp1;
    h1 = tmp1;
    h2 = tmp2;
}

O(n) O(1)





Remove Nth Node From End of List
先让第一个指针移动n 判断是否是合法的n 如果是则第二个指针开始移动 当p1.next=null时 将p2.next = p2.next.next即可
if(head==null)
	return head;
ListNode p1 = head;
int i=0;
while(p1!=null && i<n) {
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
return head;

O(n) O(1)





Remove Linked List Elements
if(head==null)
	return null;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode p = dummy;
while(p!=null && p.next!=null) {
	if(p.next.val==val) {
		ListNode tmp = p.next;
		while(tmp!=null && tmp.val==val)
			tmp = tmp.next;
		p.next = tmp;
	}
	p = p.next;
}
return dummy.next;

O(n) O(1)





Remove Duplicates from Sorted List
将有重复的值只保留一个 扫一遍链表 如果p.val==p.next.val 则p.next = p.next.next
if(head==null)
	return head;
ListNode p = head;
while(p.next!=null) {
	if(p.val==p.next.val)
		p.next = p.next.next;
	else
		p = p.next;
}
return head;

O(n) O(1)





Remove Duplicates from Sorted List ii
重复的值全部删掉 利用dummy节点删除重复的节点 上一题不需要dummy直接跳过重复即可
if(head==null || head.next==null)
	return head;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode p = dummy;
ListNode curr = head;
while(p.next!=null) {
	while(curr.next!=null && p.next.val==curr.next.val)
		curr = curr.next;
	if(p.next==curr) {
		p = p.next;
		curr = curr.next;
	}
	else {
		p.next = curr.next;
	}
}
return dummy.next;

O(n) O(1)





Partition List
两个节点 一个指小于x的节点一个指大于x的节点 扫一遍链表 再把两个链表接一起
ListNode h1 = new ListNode(0), h2 = new ListNode(0);
ListNode p = head, p1 = h1, p2 = h2;
while(p!=null) {
	if(p.val<x) {
		p1.next = p;
		p1 = p1.next;
		p = p.next;
		p1.next = null;
	}
	else {
		p2.next = p;
		p2 = p2.next;
		p = p.next;
		p2.next = null;
	}
}
p1.next = h2.next;
return h1.next;

O(n) O(1)





Merge Two Sorted Lists
if(l1 == null) return l2;
if(l2 == null) return l1;
ListNode dummy = new ListNode(0);
ListNode tmp = dummy;
while(l1!=null && l2!=null)
{
    if(l1.val<l2.val)
    {
        tmp.next = l1;
        l1 = l1.next;
        tmp = tmp.next;
    }
    else
    {
        tmp.next = l2;
        l2 = l2.next;
        tmp = tmp.next;
    }
}
while(l1!=null)
{
    tmp.next = l1;
    l1 = l1.next;
    tmp = tmp.next;
}
while(l2!=null)
{
    tmp.next = l2;
    l2 = l2.next;
    tmp = tmp.next;
}
return dummy.next;

O(n) O(1)





Merge k Sorted Lists
1 Merge Sort
if(lists==null || lists.size()==0)
	return null;
return helper(lists, 0, lists.size()-1);

private ListNode helper(List<ListNode> lists, int left, int right) {
	if(left==right) {
		return lists.get(left);
	}
	int mid = (left+right)/2;
	return Merge(helper(lists, left, mid), helper(lists, mid+1, right));
}

private ListNode Merge(ListNode p1, ListNode p2) {
	//上一题
}

O(nlogn) O(logn)

2 Heapsort
if(lists==null || lists.size()==0)
	return null;
PriorityQueue<Integer> heap = new PriorityQueue<Integer>(10, new Comparator() {
	public int compare(ListNode l1, ListNode l2) {
		return l1.val-l2.val;
	}
});
for(int i=0; i<lists.size(); i++) {
	if(lists.get(i)!=null)
		heap.add(lists.get(i));
}
ListNode pre = heap.poll();
ListNode curr = pre;
while(heap.size()>0) {
	if(curr.next!=null)
		heap.add(curr.next);
	curr = heap.poll();
	pre.next = curr;
	pre = curr;
}
return head;

O(nlogn) O(n)





Linked List Cycle
if(head==null || head.next==null)
	return false;
ListNode slow = head, fast = head;
while(fast.next!=null && fast.next.next!=null) {
	slow = slow.next;
	fast = fast.next.next;
	if(fast==slow)
		return true;
}
return false;

时间O(n) 空间O(1)





Linked List Cycle ii
if(head==null || head.next==null)
	return null;
ListNode slow = head, fast = head;
while(fast.next!=null && fast.next.next!=null) {
	slow = slow.next;
	fast = fast.next.next;
	if(slow==fast)
		break;
}
if(slow!=fast)
	return null;
slow = head;
while(slow!=fast) {
	slow = slow.next;
	fast = fast.next;
}
return slow;

O(n) O(1)





Intersection of Two Linked Lists
这题有很多做法 我的做法是先计算两个链表的长度让长的那个先走l2-l1步 然后两指针一起走 相遇时即为Intersection
if(headA==null || headB==null)
	return null;
int lenA = 1, lenB = 1;
ListNode pA = headA, pB = headB;
while(pA.next!=null) {
	lenA++;
	pA = pA.next;
}
while(pB.next!=null) {
	lenB++;
	pB = pB.next;
}
pA = headA; pB = headB;
if(lenA>lenB) {
	for(int i=0; i<lenA-lenB; i++)
		pA = pA.next;
}
else {
	for(int i=0; i<lenB-lenA; i++)
		pB = pB.next;
}
while(pA!=pB) {
	pA = pA.next;
	pB = pB.next;
}
return pA;

O(n) O(1)





Insertion Sort List
插入排序
if(head==null || head.next==null)
	return head;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode p = head;
while(p.next!=null) {
	if(p.val>p.next.val) {
		ListNode prev = dummy;
		while(prev.next.val<p.next.val)
			prev = prev.next;
		ListNode tmp = p.next;
		p.next = p.next.next;
		tmp.next = prev.next;
		prev.next = tmp;
	}
	else
		p = p.next;
}

时间O(n^2) 空间O(1)





Copy List with Random Pointer



Convert Sorted List to Binary Search Tree
先扫一遍得到节点数量 之后递归建立左子树 然后建立root 将ListNode设为下一个节点 递归右子树 return root 时间O(logn) 空间O(logn)+O(n)



Add Two Numbers
每次新建一个节点 将l1.val l2.val carry加起来存在节点里 然后如果l1 l2还有剩余值将他们都加起来 最后如果carry还有一位要再新建节点






