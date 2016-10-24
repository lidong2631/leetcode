public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                if (a1[0] == a2[0])
                    return a2[1] - a1[1];
                else
                    return a1[0] - a2[0];
            }  
        });
        int[] arr = new int[envelopes.length];
        int size = 0;
        for (int[] envelope : envelopes) {
            int left = 0, right = size;
            while (left != right) {
                int mid = (left + right) / 2;
                if (arr[mid] < envelope[1])
                    left = mid + 1;
                else
                    right = mid;
            }
            arr[left] = envelope[1];
            if (left == size) size++;
        }
        return size;
    }
}

You have a number of envelopes with widths and heights given as a pair of integers (w, h). 

One envelope can fit into another if and only if both the width and height of one envelope is greater than the width 

and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


same as cc150 circus problem


O(nlogn) O(n)

1 Sort the array. Ascend on width and descend on height if width are same.
2 Find the longest increasing subsequence based on height. (use leetcode longest increasing subsequence)

[[5,4],[6,4],[6,7],[2,3]]

sort: [[2,3],[5,4],[6,7],[6,4]]
find LIS: [[2,3],[5,4],[6,7]]


https://leetcode.com/discuss/106946/java-nlogn-solution-with-explanation