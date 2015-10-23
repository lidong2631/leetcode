public class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1) {
                    l1.add(i);
                    l2.add(j);
                }
            }
        }
        Collections.sort(l1);
        Collections.sort(l2);
        int left = 0, right = l1.size()-1, res = 0;
        while(left<right) {
            res+=l1.get(right)-l1.get(left)+l2.get(right)-l2.get(left);
            right--;
            left++;
        }
        return res;
    }
}

将x y轴坐标排序 两两一对将差值相加

https://leetcode.com/discuss/65336/14ms-java-solution