Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.



Java:
public class NumArray {

    int[] arr;
    int[] BITree;

    public NumArray(int[] nums) {
        int len = nums.length;
        arr = new int[len];
        BITree = new int[len+1];
        
        for (int i = 0; i < len; i++) {
            update(i, nums[i]);
            arr[i] = nums[i];
        }
    }

    void update(int i, int val) {
        int diff = val - arr[i];
        arr[i] = val;
        i++;
        
        while (i <= arr.length) {
            BITree[i] += diff;
            i += i & (-i);
        }
    }

    int getSum(int i) {
        int sum = 0;
        i++;
        
        while (i > 0) {
            sum += BITree[i];
            i -= i & (-i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);


Binary Indexed Tree

O(logn) for update and getSum, O(nlogn) for initialization
https://leetcode.com/discuss/72551/share-my-java-binary-indexed-tree-solution
http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/


Another solution could use Segment Tree