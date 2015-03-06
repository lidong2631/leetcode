public class BitSet {
	int[] bitset;

	public BitSet(int size) {
		bitset = new int[size>>5];	//java中int型变量有32位
	}

	public boolean get(int pos) {
		int wordNum = pos>>5;			//divide 32 先把pos的bit数除以32 转成在整型数组bitset中对应的位置
		int bitNum = (pos & 0x1F);				//mod 32 bitNum表示找到bitset的对应整数位后 在这个整数位它对应bit第几位
		return (bitset[wordNum] & (1<<bitNum))!=0;	//检查bitset[wordNum]里的整数的bitNum位看是否为0 不为0则表示重复
	}

	public void set(int pos) {		//同理 将第一次出现的数set到对应bitset的位
		int wordNum = (pos>>5);
		int bitNum = (pos & 0x1F);
		bitset[wordNum] |= 1<<bitNum;
	}
}

public static void checkDup(int[] arr) {
	BitSet bs = new BitSet(32000);
	for(int i=0; i<arr.length; i++)
	{
		if(bs.get(arr[i]-1))			//bitset starts at 0 but number start from 1
			System.out.println(arr[i]);
		else
			bs.set(arr[i]-1);
	}
}