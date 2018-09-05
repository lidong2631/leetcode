Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?




Java:
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    
    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++; r--;
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