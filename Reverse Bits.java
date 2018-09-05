Reverse bits of a given 32 bits unsigned integer.

Example:

Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
             return 964176192 represented in binary as 00111001011110000010100101000000.
Follow up:
If this function is called many times, how would you optimize it?




Java:
public class Solution {
    public int reverseBits(int n) {
        int res = 0, i = 0;
        while (i < 32) {
            res += ((n >> i) & 1) << (31 - i);  // careful cannot write (n & (1 << i))
            i++;
        }
        return res;
    }
}


Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 

return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer



public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i=0; int res = 0;
        while(i<32) {
            res = (res<<1) + ((n>>i)&1);
            i++;
        }
        return res;
    }
}


我们只需要把要翻转的数从右向左一位位的取出来，然后加到新生成的数的最低位即可

java中运算符优先级 http://blog.csdn.net/xiaoli_feng/article/details/4567184




优化 按字节转 将不同字节map到hashmap 这样就可以直接取reverse的结果
If this function is called many times, how would you optimize it?

https://leetcode.com/discuss/27328/java-solution-and-optimization
We can divide an int into 4 bytes, and reverse each byte then combine into an int. For each byte, we can use cache to improve performance.



https://leetcode.com/discuss/27338/8ms-c-code-some-ideas-about-optimization-spoiler
The key idea of the optimization is to look up a 4 bit chuck and find out what the reverse is. For example, 

reverse of 0001 is 1000 (in decimal reverse of 1 is 8). Another example, reverse of 1010 is 0101, meaning reverse of 10 is 5.

Based on this idea we could create a look up table:

value -> reverse

0 ------> 0

1 ------> 8

... ------> ...

15 ------> 15

This can be further optimized by using bytes lookup table of size 256 but I am too lazy to generate the table : ). 

Note, place the table initialization outside the reverseBits() routine is necessary for performance.

In theory, using look up table may improve the performance as we are dealing with 4 bits each time. 

Comparing to the method that iteratively swaps two bits each time, the method below should be faster. 

Given the 600 test cases, the performance difference is not dramatic though.

During each iteration, shift the output 4 bits to the left, and discard the lowest 4 bits from the input. 

Make sure the reverse of current lowest 4 bits is saved to the current highest 4 bits in the output.
// cache
private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
public int reverseBits(int n) {
    byte[] bytes = new byte[4];
    for (int i = 0; i < 4; i++) // convert int into 4 bytes
        bytes[i] = (byte)((n >>> 8*i) & 0xFF);
    int result = 0;
    for (int i = 0; i < 4; i++) {
        result += reverseByte(bytes[i]); // reverse per byte
        if (i < 3)
            result <<= 8;
    }
    return result;
}

private int reverseByte(byte b) {
    Integer value = cache.get(b); // first look up from cache
    if (value != null)
        return value;
    value = 0;
    // reverse by bit
    for (int i = 0; i < 8; i++) {
        value += ((b >>> i) & 1);
        if (i < 7)
            value <<= 1;
    }
    cache.put(b, value);
    return value;
}





public class Solution {
   
    private final Map<Byte, Integer> map = new HashMap<Byte, Integer>();
    
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        byte[] bytes = new byte[4];
        for(int i=0; i<4; i++) {
            bytes[i] = (byte)((n>>>8*i)&0xFF);
        }
        int res = 0;
        for(int i=0; i<4; i++) {
            res+=reverseByte(bytes[i]);
            if(i<3)
                res<<=8;
        }
        return res;
    }
    
    private int reverseByte(byte b) {
        if(map.containsKey(b))
            return map.get(b);
        int res = 0;
        for(int i=0; i<8; i++) {
            res+=((b>>>i)&1);
            if(i<7)
                res<<=1;
        }
        map.put(b, res);
        return res;
    }
}