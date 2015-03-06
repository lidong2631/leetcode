public int updateBits(int n, int m, int i, int j) {
	int max = ~0;

	int left = max-((1<<j+1)-1);	//这里答案有问题 应是j+1

	int right = ((1<<i)-1);

	int mask = left | right;

	return (n&mask) | (m<<i);
}