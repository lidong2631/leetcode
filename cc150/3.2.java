public class Node {
	public int val;
	public int min;

	public Node(int v, int min) {
		val = v;
		min = min;
	}
}

public class StackWithMin extends Stack<Node> {
	public void push(int val) {
		int min = Math.min(val, min());
		super.push(new Node(val, min));
	}

	public int min() {
		if(this.isEmpty())
			return Integer.MAX_VALUE;
		else
			return peek().min;
	}
}

Note: 这种实现对于每个元素都keep一个min变量 如果很多元素的min都一样 会浪费大量空间存储重复值




public class StackWithMin2 extends Stack<Integer> {
	Stack<Integer> minStack;

	public StackWithMin2() {
		minStack = new Stack<Integer>();
	}

	public void push(int val) {
		if(val<=min())
			minStack.push(val);
	}

	public Integer pop() {
		int val = super.pop();
		if(val==min())
			minStack.pop();
		return val;
	}

	public int min() {
		if(minStack.isEmpty())
			return Integer.MAX_VALUE;
		else
			return minStack.peek();
	}
}

Note: 这个解法用一个单独stack存储min 可以省掉一部分重复值得浪费 但如果stack所有数都一样或是递减存储则每个min值还是都会存 还要有stack的开销