Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.



Java:
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<>();
        for (int n : nums1) {
            if (!set.contains(n))
                set.add(n);
        }
        for (int n : nums2) {
            if (set.contains(n)) {
                res.add(n);
                set.remove(n);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            result[i] = res.get(i);
        return result;
    }
}

O(n) O(n)

http://www.geeksforgeeks.org/find-union-and-intersection-of-two-unsorted-arrays/