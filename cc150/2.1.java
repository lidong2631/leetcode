public void deleteDup(LinkNode head) {
	HashSet<Integer> set = new HashSet<Integer>();

	LinkNode n = head;
	LinkNode prev = null;

	while(n!=null)
	{
		if(set.contains(n.data))
			prev.next = n.next;
		else
		{
			set.add(n.data);
			prev = n;
		}
		n = n.next;
	}
}

O(n), O(n)


public void deleteDup(LinkNode head) {
	if(head==null)
		return;
	LinkNode prev = head;
	LinkNode curr = prev.next;

	while(curr!=null)
	{
		LinkNode runner = head;
		while(runner!=curr)
		{
			if(runner.data==curr.data)
			{
				LinkNode tmp = curr.next;
				prev.next = tmp;
				curr = tmp;
				break;
			}
			runner = runner.next;
		}
		if(runner==curr)
		{
			prev = curr;
			curr = curr.next;
		}
	}
}

O(n^2), O(1)