public class Solution {
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0)
            return true;
        int minX = points[0][0];
        int maxX = points[0][0];
        Set<String> set = new HashSet<String>();
        
        for (int[] point : points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            set.add(Arrays.toString(point));
        }
        
        int sum = maxX + minX;
        for (int[] point : points) {
            if (!set.contains(Arrays.toString(new int[]{sum-point[0], point[1]})))
                return false;
        }
        return true;
    }
}

1 Find the smallest and largest x-value for all points.
2 If there is a line then it should be at y = (minX + maxX) / 2.
3 For each point, make sure that it has a reflected point in the opposite side

O(n) O(n)


https://leetcode.com/discuss/107675/11ms-two-pass-hashset-based-java-solution