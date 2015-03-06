//iterative

public void reverse(Node head) {
	Node second = head.next;
	Node third = head.next.next;

	second.next = head;
	head.next = null;

	if(third==null)
		return;

	Node curr = third;
	Node prev = second;

	while(curr!=null)
	{
		Node tmp = curr.next;
		curr.next = prev;
		prev = curr;
		curr = tmp;
	}

	head = prev;
}



//recursive

public void reverse(Node curr) {
	if(curr==null)
		return;

	if(curr.next==null)
	{
		head = curr;
		return;
	}

	reverse(curr.next);
	curr.next.next = curr;
	curr.next = null;
}