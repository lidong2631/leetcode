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