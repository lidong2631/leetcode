class MyQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        stack1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        peek();
        stack2.pop();
    }

    // Get the front element.
    public int peek() {
        if (stack2.isEmpty()) {     // careful here
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

简单题 跟两个queue实现一个stack思路差不多 O(1)操作

https://leetcode.com/discuss/44106/short-o-1-amortized

Note stack has both isEmpty(), empty() methods