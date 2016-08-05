public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<int[]>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k == 0)
            return res;
            
        PriorityQueue<Tuple> queue = new PriorityQueue<Tuple>(nums1.length, new Comparator<Tuple>() {
            public int compare(Tuple t1, Tuple t2) {
                return t1.val - t2.val;
            }
        });
        for (int i = 0; i < nums1.length; i++) {                // add first column (every element in nums1 only first element in nums2)
            queue.offer(new Tuple(i, 0, nums1[i]+nums2[0]));
        }
        while (k-->0 && !queue.isEmpty()) {
            Tuple curr = queue.poll();
            res.add(new int[]{nums1[curr.index1], nums2[curr.index2]});
            if (curr.index2 + 1 < nums2.length)                 // every time only all element right and ignore below to avoid duplicate
                queue.offer(new Tuple(curr.index1, curr.index2+1, nums1[curr.index1]+nums2[curr.index2+1]));
        }
        return res;
    }
}

class Tuple {
    int index1;
    int index2;
    int val;
    
    public Tuple(int id1, int id2, int val) {
        this.index1 = id1;
        this.index2 = id2;
        this.val = val;
    }
}


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