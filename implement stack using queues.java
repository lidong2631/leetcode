Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Example:

MyStack stack = new MyStack();

stack.push(1);
stack.push(2);  
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false
Notes:

You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).




Java:
class MyStack {
    private Queue<Integer> queue;
    
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (empty()) return;
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        queue.poll();
    }

    // Get the top element.
    public int top() {
        if (empty()) return -1;
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        int top = queue.poll();
        queue.add(top);
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}

pop时将队列元素重新一个个排到队尾 剩原来最后一个元素在队首