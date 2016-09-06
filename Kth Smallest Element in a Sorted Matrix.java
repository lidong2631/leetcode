public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(matrix.length, new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });
        int[] res = new int[3];
        for (int i = 0; i < matrix.length; i++) {
            heap.offer(new int[]{matrix[i][0], i, 0});
        }
        while (k-- > 0) {
            res = heap.poll();
            if (res[2] + 1 < matrix[0].length) heap.offer(new int[]{matrix[res[1]][res[2]+1], res[1], res[2]+1});
        }
        return res[0];
    }
}

same as Find Kth Pairs with Smallest Sums

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

You may assume k is always valid, 1 ≤ k ≤ n2.



In heap, element is an array
int[0] represents matrix[i][j] value
int[1] represents i
int[2] represents j
Whenever an element is poll() from heap, push the element below it to heap, only push the element right to it to heap when it is in first row. 
So we can avoid duplicates.

O(klogk)

https://discuss.leetcode.com/topic/52859/java-heap-solution-time-complexity-klog-k