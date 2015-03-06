public int maxSum(int[] arr) {
	int currSum = 0;
	int maxSum = 0;

	for(int i=0; i<arr.length; i++)
	{
		sum+=arr[i];
		if(maxSum<currSum)
			maxSum = currSum;
		if(sum<0)
			sum = 0;
	}
	return sum;
}

Note: 当序列中所有数都为负数时 此程序返回最大的那个负数 也可以返回0 这里要和面试官讨论

扩展可以看看leetcode best time to sell stacks ii 求非连续最大和