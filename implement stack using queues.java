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