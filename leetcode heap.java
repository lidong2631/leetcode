Leetcode Heap

The Skyline Problem




Merge k Sorted Lists
两种方法 merge sort heapsort
1 merge sort
if(lists==null || lists.size()==0)
	return null;
return helper(lists, 0, lists.size()-1);

private ListNode helper(List<ListNode> lists, int left, int right) {
	if(left<right) {
		int mid = (left+right)/2;
		return Merge(helper(lists, left, mid), helper(lists, mid+1, right));
	}
	return lists.get(left);
}

private ListNode Merge(ListNode l1, ListNode l2) {
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

O(nklogk) O(logk)

2 heapsort
if(lists==null || lists.size()==0)
	return null;
PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator() {
	public int compare(ListNode l1, ListNode l2) {
		return l1.val-l2.val;
	}
});
for(int i=0; i<lists.size(); i++) {
	if(lists.get(i!)!=null)
		heap.add(lists.get(i));
}
ListNode curr = heap.poll();
ListNode pre = curr;
ListNode head = curr;
while(heap.size()!=0) {
	if(curr.next!=null)
		heap.add(curr.next);
	curr = heap.poll();
	pre.next = curr;
	pre = pre.next;
}
return head;

O(nklogk) O(k)





Kth Largest Element in an Array
两种解法 quickselect heapsort



