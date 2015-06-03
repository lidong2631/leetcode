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
1 quickselect
return helper(nums, 0, nums.length-1, k);

private int helper(int[] nums, int left, int right, int k) {
	if(left==right)
		return nums[left];
	while(true) {
		int pivotIndex = right;
		pivotIndex = partition(nums, left, right, pivotIndex);
		int rank = right-pivotIndex+1;
		if(rank==k)
			return nums[pivotIndex];
		else if(k>rank)
           return helper(nums, left, pivotIndex-1, k-rank);
       else
           return helper(nums, pivotIndex+1, right, k);
	}
}

private int partition(int[] nums, int left, int right, int pivotIndex) {
	int index = left;
	for(int i=left; i<right; i++) {
		if(nums[i]<nums[pivotIndex]) {
			int tmp = nums[i];
			nums[i] = nums[index];
			nums[index] = tmp;
			index++;
		}
	}
	int tmp = nums[index];
	nums[index] = nums[pivotIndex];
	nums[pivotIndex] = tmp;
	return index;
}

average O(n) worst O(n^2) O(1)

2 heap 很好的想法 只要是找第几大的数这种不需要严格顺序的题都可以联想到heap
PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
int i=0;
while(i<k) {
	heap.add(nums[i]);
	i++;
}
while(i<nums.length) {
	if(nums[i]>heap.peak()) {
		heap.poll();
		heap.add(nums[i])
	}
	i++;
}
return heap.peak();

O(k+(n-k)logk) O(k)









