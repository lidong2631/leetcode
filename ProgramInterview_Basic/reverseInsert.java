class reverseInsert {
	
	private static class Node {
		public int val;
		public Node next;

		public Node(int v) {
			val = v;
		}
	}

	Node start;

	public static void main(String[] args) {
		Node root = new Node(1);
		Node curr = root;
		for(int i=2; i<8; i++)
		{
			Node tmp = new Node(i);
			curr.next = tmp;
			curr = curr.next;
		}

		curr = root;
		while(curr!=null)
		{	
			System.out.print(curr.val+ " ");
			curr = curr.next;
		}
		System.out.println();


		reverseInsert ri = new reverseInsert();
		curr = root;
		ri.start = root;
		ri.rev(curr);

		curr = root;
		while(curr!=null)
		{	
			System.out.print(curr.val+ " ");
			curr = curr.next;
		}
	}

	public void rev(Node end) {
		if(end.next!=null)
			rev(end.next);
		if(start!=null)
		{
			if(start==end||start.next==end)
			{
				end.next = null;
				start = null;
			}
			else
			{
				end.next = start.next;
				start.next = end;
				start = end.next;
			}
		}
	}

}