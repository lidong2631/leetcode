public Stack<Integer> sort(Stack<Integer> s) {
	Stack<Integer> stack = new Stack<Integer>();
	while(!s.isEmpty())
	{
		int tmp = s.pop();
		while(!stack.isEmpty() && stack.peek()>tmp)
		{
			s.push(stack.pop());
		}
		stack.push(tmp);
	}
	return stack;
}

Note: 每次pop一个s的元素 跟比较栈stack的栈顶元素比较 符合递增顺序就直接push 

否则pop stack元素到s中直到stack栈顶元素小于s的元素 然后push s中元素到stack