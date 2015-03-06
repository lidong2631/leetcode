public class Solution {
    public void rotate(int[] nums, int k) {
        if(k<=0)
            return;
        k = k % nums.length;    //这里要注意 test case ([-1], 2)这种 不然会报ArrayIndexOutOfBoundException
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    
    private void reverse(int[] num, int start, int end) {
        while(start<end) {
            int tmp = num[start];
            num[start] = num[end];
            num[end] = tmp;
            start++;
            end--;
        }
    }
}

from meetqun http://www.meetqun.com/thread-6977-1-1.html

其实这题目是Easy不应该写题解。
不过我记得自己在《编程之美》上第一次看到这题居然第一想法还是O(n^2)的解法 ORZ
其实这题思路很简洁，说白了就是找规律。就从给的例子下手：
k = 3, 1 2 3 4 5 6 7 ----> 5 6 7 1 2 3 4

左边的k个数字是原来右边的，右边的n-k个数字是原来左边的。
其实就是先把整个array reverse了。然后把左边k个reverse 再把右边n-k个reverse。

类似这种reverse再局部reverse的还有很多题目比如这一道：https://oj.leetcode.com/problems/reverse-words-in-a-string/% t4 j3 r1 x# Y  E
都巧妙地通过reverse技巧避免了空间浪费。