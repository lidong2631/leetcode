public int makeChange(int n, int currCoin) {
	int next_Coin = 0;

	switch(currCoin) {
		case 25:
			next_Coin = 10;
			break;
		case 10:
			next_Coin = 5;
			break;
		case 5:
			next_Coin = 1;
			break;
		case 1:
			return 1;
	}
	int ways = 0;
	
	for(int i=0; i*currCoin<=n; i++)
		ways+=makeChange(n-i*currCoin, next_Coin);

	return ways;
}

System.out.println(makeChange(n, 25));

Note: 这题应该有dp解法 另外leetcode combination sum是返回所有结果集