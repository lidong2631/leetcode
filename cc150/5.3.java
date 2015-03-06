public boolean isOne(int n, int index) {	//check if this bit is 1
	return ((n & (1<<index)) > 0);
}

public int setBit(int n, int index, boolean b) {
	if(b)
	{
		return n | (1<<index);		//set bit index to 1
	}
	else
	{
		int mask = ~(1<<index);		//set bit index to 0
		return n & mask;
	}
}

public int getNext(int n) {
	if(n<=0)
		return -1;

	int bit = 0;
	int index = 0;
	int countOne = 0;

	for(; !isOne(n, index)&&bit<32; bit++)		//find first 1
		index++;
	for(; isOne(n, index)&&bit<32; bit++) {	//find next 0
		index++;
		countOne++;
	}
	if(bit==31)		//special case 0111...00 	no answer
		return -1;
	if(bit==32)		//special case 11100..00 	answer:00...00111
		n = 0;
	else
	{
		n = setBit(n, index, true);

		index--;
		n = setBit(n, index, false);	//turn off previous one
		countOne--;

		for(int i=index-1; i>=countOne; i--)	//shift all ones as far to the right as possible
			n = setBit(n, i, false);
	}
	for(int i=countOne-1; i>=0; i--)
		n = setBit(n, i, true);
	return n;
}


public int getPrev(int n) {
	if(n<=0)
		return -1;

	int bit = 0;
	int index = 0;
	int countZero = 0;

	for(; isOne(n, index)&&bit<32; bit++)		//find first 0
		index++;
	for(; !isOne(n, index)&&bit<32; bit++)		//find next 1
	{
		index++;
		countZero++;
	}
	if(bit==31)		//special case 100...11 	no answer
		return -1;
	if(bit==32)		//special case 000...11 	answer is 11000...000
		n = ~0;
	else
	{
		n = setBit(n, index, false);

		index--;
		n = setBit(n, index, true);		//turn on previous 0
		countZero--;

		for(int i=index-1; i>=countZero; i--)	//shift all ones as far to left as possible
			n = setBit(n, i, true);
	}
	for(int i=countZero-1; i>=0; i++)
		n = setBit(n, i, false);
	return n
}

Note: 这题有意思 三步

这题还要注意边界条件 看hawstein blog

假设一个32位的整数， 它的第31位为1，即：0100..00，那么按照上面的操作，我们会得到1000..00， 很不幸，这是错误的。因为int是有符号的，意味着我们得到了一个负数。 

我们想要得到的是一个比0100..00大的数，结果得到一个负数，自然是不对的。 事实上比0100..00大的且1的个数和它一样的整数是不存在的，扩展可知， 

对于所有的0111..，都没有比它们大且1的个数和它们一样的整数。对于这种情况， 直接返回-1。-1的所有二进制位全为1，不存在一个数说1的个数和它一样还比它大或小的， 

因此适合作为找不到答案时的返回值。

另一个边界情况是什么呢？就是对于形如11100..00的整数，它是一个负数， 比它大且1的个数相同的整数有好多个，最小的当然是把1都放在最低位了：00..0111。









