class MyStack {
    Queue<Integer> queue = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);    
    }

    // Removes the element on top of the stack.
    public void pop() {
        int size = queue.size();
        for(int i=1; i<size; i++) {
            queue.add(queue.remove());
        }
        queue.remove();
    }

    // Get the top element.
    public int top() {
        int size = queue.size();
        for(int i=1; i<size; i++) {
            queue.add(queue.remove());
        }
        int tmp = queue.remove();   //不能用peek() 用peek会改变原始的顺序
        queue.add(tmp);
        return tmp;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}

pop时将队列元素重新一个个排到队尾 剩原来最后一个元素在队首