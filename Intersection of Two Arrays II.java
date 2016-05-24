public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums1) {
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
        }
        for (int n : nums2) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) - 1);
                res.add(n);
                if (map.get(n) == 0)
                    map.remove(n);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            result[i] = res.get(i);
        return result;
    }
}

O(n) O(n)