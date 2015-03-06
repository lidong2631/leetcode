public int numZeros(int fac) {
	if(fac<0)
		return -1;
	int count = 0;
	for(int i=5; fac/i>0; i*=5)
	{
		count+=fac/i;
	}
	return count;
}