public static void main(String[] args) {
	int n = 5;
	Tower[] towers = new Tower[n];

	for(int i=0; i<3; i++)
		towers[i] = new Tower(i);	//初始化三个tower

	for(int i=n-1; i>=0; i--)	//将盘子都加到第一个tower 数字由上到下递增
		towers[0].add(i);

	towers[0].moveDisks(n, towers[2], towers[1]);	//开始move
}

public class Tower {
	private Stack<Integer> disks;	//每个tower有一个stack存放所有在这个tower上的盘子
	private int index;				//tower的索引

	public Tower(int i) {
		disks = new Stack<Integer>();
		index = i;
	}

	public void moveDisks(int n, Tower destination, Tower intermedia) {
		if(n > 0)
		{
			moveDisks(n-1, intermedia, destination);	//将上面的n-1个盘子 以destination为中转 从当前tower 放到当前tower的中转tower
			moveTopTo(destination);					//当递归到只剩下一个盘子时 将这个盘子直接从当前tower移动到destination
			intermedia.moveDisks(n-1, destination, this);	//将上面n-1盘子 从当前tower的中转tower 移动到destination 以当前tower为中转 这里用this是因为以当前tower为对象调用的moveDisks
		}
	}

	public void moveTopTo(Tower t) {
		int top = disks.pop();		//pop出当前tower最上面的盘子 将它push到tower t的stack中 打印结果
		t.add(top);
		System.out.println("Move disk " + top + " from " + this.index()/*top所在tower的index*/ + " to " + t.index());
	}

	public int index() {
		return index;
	}

	public void add(int disk) {
		if(!disks.isEmpty() && disks.peek()<=disk)
			System.out.println("Error placing disk " + disk);
		else
			disks.push(d);
	}
}

Note: hanoi tower 栈实现 好好理解 很经典的问题