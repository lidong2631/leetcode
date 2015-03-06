public class MyQueue<Integer> {
	Stack<Integer> s1, s2;
	public MyQueue() {
		s1 = new Stack<Integer>();
		s2 = new Stack<Integer>();
	}

	public void push(Integer val) {
		s1.push(val);
	}

	public Integer pop() {
		if(!s2.isEmpty())
			return s2.pop();
		while(!s1.isEmpty())
			s2.push(s1.pop());
		return s2.pop();
	}

	public Integer peek() {
		if(!s2.isEmpty())
			return s2.peek();
		while(!s1.isEmpty())
			s2.push(s1.pop());
		return s2.peek();
	}

	public int size() {
		return s1.size() + s2.size();
	}
}

Note: push是到stack1 pop从stack2出 如果stack2为空 就将stack1种所有元素push到stack2