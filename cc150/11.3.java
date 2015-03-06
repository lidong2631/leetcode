byte[] bitVector = new byte[0xFFFFFFFF/8];		//this means one integer has 4 bytes and one byte has 8bits so one integer has 32bits totally.
												//same as new byte[0xFFFFFFF/8] cause int takes 4 bytes in java

public void findNum() throws FileNotFoundException {
	Scanner in = new Scanner(new FileReader("input_file.txt"));

	while(in.hasNextInt())
	{
		int n = in.nextInt();

		bitVector[n/8] |= 1<<(n%8);		//find corresponding number in bitVector by using OR operator to set nth bit of a byte
	}									//10 would correspond to 2nd bit of index 2 in the byte array

	for(int i=0; i<bitVector.length; i++)
	{
		for(int j=0; j<8; j++)
		{
			if((bitVector[i] & (1<<j))==0)	//retrieves the individual bits of each byte. when 0 bit is found finds the value
			{
				System.out.println(i*8+j);	//print the number value
				return;
			}
		}
	}
}




follow up:

int size = 1048576;		//2^20 bits which can comfortably fit in 10MB 10*2^20 bytes
int blockNum = 4096;	//2^12
byte[] bitVector = new byte[size/8];
int[] blocks = new int[blockNum];

public void findNumber() throws FileNotFoundException {
	
	int start = -1;
	Scanner in = new Scanner(new FileReader("input_file.txt"));

	//下面的while和for定位出哪个block
	while(in.hasNextInt)
	{
		int n = in.nextInt();
		blocks[n/(bitVector.length*8)]++;	//对应第几号block++
	}

	for(int i=0; i<blocks.length; i++)
	{
		if(blocks[i]<bitVector.length*8)	//if value < 2^20 then at least 1 number is missing this block
			start = i*bitVector.length*8;	//start定位到这个block
		break;
	}

	in = new Scanner(new FileReader("input_file.txt"));

	//下面的while和for定位出这个block 的哪一位是没有的数字
	while(in.hasNextInt())
	{
		int n = in.nextInt();
		if(n>=start && n<start+bitVector.length*8)		//if this number inside the missing number block we record it
			bitVector[(n-start)/8] |= 1 <<((n-start)%8);	//record the corresponding bit to 1
	}

	for(int i=0; i<bitVector.length; i++)
	{
		for(int j=0; j<8; j++)
		{
			if((bitVector[i] & (1<<j))==0)		//最后bitVector中为0的位即是这个数
			{
				System.out.println(i*8+j+start);
				return;
			}
		}
	}
}

Note:看 hawstein blog

由于Bit Map只能处理非负数， (没有说在第-1位上置1的)，因此对于有符号整数，可以将所有的数加上0x7FFFFFFF，将数据移动到正半轴，

找到一个满足条件的数再减去0x7FFFFFFF即可。因此本文只考虑无符号整数，对于有负数的情况，按照上面的方法处理即可。

