1 divide array in three equal parts. Individual stack grows in that limited space


public class 3stacks {
	int stackSize = 100;
	int[] buffer = new int[3*stackSize];
	int[] stackPtr = {0, 0, 0};

	public void push(int stackNum, int val) {
		int index = stackNum*stackSize + stackPtr[stackNum];
		stackPtr[stackNum]+;
		buffer[index] = val;
	}

	public int pop(int stackNum) {
		int index = stackNum*stackSize + stackPtr[stackNum];
		stackPtr[stackNum]--;
		int val = buffer[index];
		buffer[index] = 0;
		return val;
	}

	public int peek(int stackNum) {
		int index = stackNum*stackSize + stackPtr[stackNum];
		return buffer[index];
	}

	public boolean isEmpty(int stackNum) {
		return stackPtr[stackNum]==stackNum*stackSize;
	}
}



2 stack can grow as long as there is any free space in the array.

this approach we would be able to have flexibility in terms of variable space utilization but

we would need to increase space complexity.

public class FreeListNode {
	public int prevIndex;
	public int val;
	public FreeListNode(int p, int v) {
		val = v;
		prevIndex = p;
	}
}

public class 3stacks {
	int stackSize = 300;
	int FreeListIndex = 0;	//一共用了多少index
	int[] stackPtr = {};
	FreeListNode[] buffer = new FreeListNode[stackSize*3];

	public void push(int stackNum, int val) {	//push操作还应该判断下stack是否已经满了
		int prevIndex = stackPtr[stackNum];
		stackPtr[stackNum] = FreeListIndex;
		FreeListIndex++;
		buffer[stackPtr[stackNum]] = new FreeListNode(prevIndex, val);	//push时存储上一元素的位置prevIndx
	}

	public int pop(int stackNum) {	//pop前还应该判断下stack是否已经空
		int nextIndex = stackPtr[stackNum];
		int val = buff[index].val;
		stackPtr[stackNum] = buffer[nextIndex].prevIndex;	//pop时直接回到prevIndex
		buffer[nextIndex] = null;
		FreeListIndex--;
		return val;
	}

	public int peek(int stackNum) {
		return buffer[stackPtr[stackNum]].val;
	}

	public boolean isEmpty(int stackNum) {
		return stackPtr[stackNum]==-1;
	}
}

Note： 这种解法有一个问题 就是我们pop某一个stack时它的元素可能不在栈顶 如122212 我们pop stack2 则删掉的空间无法再被使用

解决办法是buffer里存的不是数值 而是FreeListNode的对象 它有一个prevIndex变量指向前一个元素的位置 这样stack的使用更加灵活 但空间开销会变大 