public int negative(int n) {
	int sign = n>0 ? -1:1;
	int oppposite = 0;
	while(n!=0)
	{
		n+=sign;
		oppposite+=sign;
	}
	return opposite;
}

public int minus(int a, int b) {
	b = negative(b);
	return a + b;
}

public boolean diffSign(int a, int b) {
	if(a>0&&b<0)||(a<0&&b>0)
		return true;
	return false;
}

public int multiply(int a, int b) {
	if(b>a)
		return multipy(b, a);
	
	int res = 0;		//res初始为0 可以处理a=0或b=0的情况

	for(int i=0; i<b; i++)
		res+=a;
	
	if(diffSign(a, b))
		return negative(res);
	return res;
}

public int divide(int a, int b) throws java.lang.ArithmeticException {

		if(b==0)
			throw new ArithmeticException("divisor is 0");
		int quotient = 0;
		int divisor = negative(Math.abs(b));
		int dividend;

		for(dividend = Math.abs(a); dividend>=Math.abs(divisor); dividend+=divisor)
			quotient++;

		if(diffSign(a, b))
			return negative(quotient);
		return quotient;
	}
}


Note: 这题的算法都是简单版的 see leetcode divide two integer, multiply string 这两个算法更复杂些