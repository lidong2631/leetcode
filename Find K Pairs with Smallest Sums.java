public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        
        PriorityQueue<int[]> heap = new PriorityQueue<>(nums1.length, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });
        
        for (int i = 0; i < nums1.length; i++) {    // add first column (every element in nums1 only first element in nums2)
            heap.offer(new int[]{nums1[i]+nums2[0], i, 0});
        }
        
        while (k-- > 0 && !heap.isEmpty()) {
            int[] curr = heap.poll();
            res.add(new int[]{nums1[curr[1]], nums2[curr[2]]});
            if (curr[2] + 1 < nums2.length)     // every time only all element right and ignore below to avoid duplicate
                heap.offer(new int[]{nums1[curr[1]]+nums2[curr[2]+1], curr[1], curr[2]+1});
        }
        return res;
    }
}

same as Kth Smallest Element in a Sorted Matrix

        nums2
nums1   2  4  6
    1   3  5  7
    7   9  11 13
    11  13 15 17

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]



Imagine a matrix:

a1 a2 a3
a4 a5 a6
a7 a8 a9

remove duplicate is similar to Kth Smallest Element in a Sorted Matrix

K * lgM M = nums1.length

https://discuss.leetcode.com/topic/50647/9ms-java-solution-with-explanation