1 if a>b return a else return b

2 if a-b>0 return a else return b

3 if a-b>0 let k=0 else k=1 return a-k*(a-b)	//这一步是转折

4 let c = a - b let k = the most significant bit of c return a - k*c



public int max(int a, int b) {
	int c = a - b;
	int k = (c>>31) & 1;
	int max = a - k*c;
	return max;
}