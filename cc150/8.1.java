int fibonacci(int n) {
	if(n==0)
		return 0;
	else if(n==1)
		return 1;
	else if(n>1)
		return fibonacci(n-1) + fibonacci(n-2);
	else
		return -1;
}


int fibonacci(int n) {
	if(n < 0)
		return -1;
	if(n==0)
		return 0;
	int a = 1, b = 1;
	for(int i=3; i<=n; i++)
	{
		int c = a + b;
		a = b;
		b = c;
	}
	return b;
}