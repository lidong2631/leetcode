Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?




Java:
public class Solution {
    public int findMin(int[] num) {
        int left = 0, right = num.length-1;
        while (left < right && num[left] >= num[right]) {       //careful
            int mid = (left + right) / 2;
            if (num[mid] > num[right])
                left = mid + 1;
            else if (num[mid] < num[right])
                right = mid;
            else
                left++;
        }
        return num[left];
    }
}

be careful about left < right cannot write left <= right
Runtime Error Message:
Line 13: java.lang.ArrayIndexOutOfBoundsException: 1
Last executed input:
[1]