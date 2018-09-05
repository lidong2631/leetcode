Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0





Java:
public class Solution {
    public int findMin(int[] num) {
        int left = 0, right = num.length - 1;
        while (left < right && num[left] >= num[right]) {   // careful
            int mid = (left + right) / 2;
            if (num[mid] > num[right]) 
            	left = mid + 1;
            else 
            	right = mid;
        }
        return num[left];
    }
}

看cleanCode 说的很清楚 当A[mid]<A[right] 最小值在A[left]到A[mid]之间 当A[mid]>A[right] 最小值在A[mid+1]到A[right]之间
