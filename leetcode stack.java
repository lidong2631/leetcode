Valid Parentheses
用stack维护当前的括号 左括号push 右括号pop 看pop时左右是否匹配 这里用map封装了括号
Map<Character, Character> map = new HashMap<Character, Character>();
map.put('(', ')');
map.put('[', ']');
map.put('{', '}');
Stack<Character> stack = new Stack<Character>();
for(char c:s.toCharArray()) {
	if(map.containsKey(c))
		stack.push(c);
	else {
		if(stack.empty() || map.get(stack.pop())!=c)
			return false;
	}
}
return stack.empty();

O(n) O(n)





Trapping Rain Water
1 左扫一遍 右扫一遍
if(A==null || A.length<2)
	return 0;
int water = 0, leftMax = 0;
int[] leftMost = new int[A.length];
for(int i=0; i<A.length; i++) {
	leftMax = Math.max(leftMax, A[i]);
	leftMost[i] = leftMax;
}
int rightMax = 0;
for(int i=A.length-1; i>=0; i--) {
	rightMax = Math.max(rightMax, A[i]);
	water+=Math.max(leftMost[i], rightMax)-A[i];
}
return water;

O(2n) O(n)

2 贪心 夹逼
int water = 0, left = 0, right = A.length-1;
while(left<right) {
	int min = Math.min(A[left], A[right]);
	if(A[left]==min) {
		left++;
		while(left<right && A[left]<=min) {
			water+=min-A[left];
			left++;
		}
	}
	else {
		right--;
		while(left<right && A[right]<=min) {
			water+=min-A[right];
			right--;
		}
	}
}
return water;

O(n) O(1)





Simplify Path
根据栈中的内容转换成路径即可
int i=0;
Stack<String> stack = new Stack<String>();
while(i<path.length()) {
	StringBuilder sb = new StringBuilder();
	int index = i;
	while(i<path.length() &&　path.charAt(i)!='/') {
		sb.append(path.charAt(i));
		i++;
	}
	if(index!=i) {
		String tmp = sb.toString();
		if(tmp.equals("..")) {
			if(!stack.empty())
				stack.pop();
		}
		else if(!tmp.equals("."))
			stack.push(tmp);
	}
	i++;
}
if(stack.empty())
	return "/";
String res = "";
while(!stack.empty())
	res = "/"+stack.pop()+res;
return res;

O(n) O(n)





Min Stack
双栈 正常stack和minStack
Stack<Integer> stack = new Stack<Integer>();
Stack<Integer> minStack = new Stack<Integer>();

public void push(int x) {
    stack.push(x);
    if(minStack.empty() || x<=minStack.peek())
        minStack.push(x);
}

public void pop() {
    if(stack.empty())
        return;
    int elem = stack.pop();
    if(!minStack.empty() && elem==minStack.peek())
        minStack.pop();
}

public int top() {
    return stack.peek();
}

public int getMin() {
    return minStack.peek();
}

O(1) O(n)





Largest Rectangle in Histogram
if(height==null || height.length==0)
    return 0;
int maxArea = 0;
LinkedList<Integer> stack = new LinkedList<Integer>();
int i = 0;
for(; i<height.length; i++) {
    if(stack.isEmpty() || height[i]>height[stack.peek()])   //这里写>或>=都可以
        stack.push(i);
    else {
        int peekIndex = stack.pop();
        int width = stack.isEmpty() ? i : i-stack.peek()-1;
        maxArea = Math.max(maxArea, width*height[peekIndex]);
        i--;
    }
}
while(!stack.isEmpty()) {
    int peekIndex = stack.pop();
    int width = stack.isEmpty() ? i : i-stack.peek()-1;
    maxArea = Math.max(maxArea, width*height[peekIndex]);
}
return maxArea;





Maximal Rectangle




Evaluate Reverse Polish Notation
