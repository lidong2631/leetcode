import java.util.*;

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
            System.out.println(res);
            System.out.println(l1.get(right)-l1.get(left));
            System.out.println(l2.get(right)-l2.get(left));
            res=(l2.get(right)-l1.get(left));
            System.out.println("res= " +res);
            right--;
            left++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{0,0,0,1,0,1,0,0,0,1,1,0}};
        System.out.println(s.minTotalDistance(grid));
    }
}