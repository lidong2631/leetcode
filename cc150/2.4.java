迭代法看 leetcode add two number


递归解法如下

public LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
	if(l1==null && l2==null)
		return null;
	
	int value = carry;
	
	if(l1!=null)
		value+=l1.data;
	if(l2!=null)
		value+=l2.data;
	
	LinkedListNode result = new LinkedListNode(value);

	LinkedListNode newNode = addLists(l1==null? null:l1.next, l2==null? null:l2.next, value>10? 1:0);

	result.next = newNode;
	return result;
}