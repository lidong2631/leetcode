from hawstein 这种解法更好：

public int swap_bits(int x) {
	return ((x & 0x55555555) << 1) | ((x >> 1) & 0x55555555);
}



public int swapOddEvenBits(int x) {
	return ((x&0x55555555)<<1) | ((x&0xAAAAAAAA)>>1);
}

1 mask all odd bits with 10101010 in binary

2 shift them left to put them in even bits

3 mask all even bits with 01010101 in binary

4 shift them right to put them in odd bits

5 add the two together

totally 5 steps

Note: 要注意符号位 和数字越界的问题



(x & 0xAAAAAAAA)得出的结果是unsigned，所以向右移动一位，最高位填充的是0，不是1。

只有对有符号数进行右移，才会根据最高位是0是1来进行填充，对于无符号数右移，只会填充0。你可以run一下上述程序自己验证