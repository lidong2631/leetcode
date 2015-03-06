public void getkth(int k) {
	if(k<0)
		return;

	LinkedList<Integer> Queue3 = new LinkedList<Integer>();
	LinkedList<Integer> Queue5 = new LinkedList<Integer>();
	LinkedList<Integer> Queue7 = new LinkedList<Integer>();
	Queue3.add(3);
	Queue5.add(5);
	Queue7.add(7);

	int val = 1;

	for(int i=1; i<k; i++)
	{
		val = Math.min(Queue3.peek(), Math.min(Queue5.peek(), Queue7.peek()));
		if(val==Queue7.peek())
		{
			Queue7.remove()
		}
		else
		{
			if(val==Queue5.peek())
				Queue5.remove();
			else
			{
				Queue3.remove();
				Queue3.add(val*3);
			}
			Queue5.add(val*5);
		}
		Queue7.add(val*7);
	}
	return val;
}

Note: 思路很妙！！