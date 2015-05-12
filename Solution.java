public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] dungeon = {{0,0,0}, {-1,0,0}, {2,0,-2}};
        System.out.println(s.calculateMinimumHP(dungeon));
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[] res = new int[n+1];
        res[n] = 1;
        for(int i=n-1; i>=0; i--) {
            res[i] = Math.max(res[i+1]-dungeon[m-1][i], 1);
            System.out.print(res[i]);
        }
        System.out.println();
        for(int i=m-2; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(j==n-1) {
                    res[j] = Math.max(res[j]-dungeon[i][j], 1);
                    System.out.print(res[j]);
                }
                else {
                    res[j] = Math.min(Math.max(res[j]-dungeon[i][j], 1), Math.max(res[j+1]-dungeon[i][j], 1));
                    System.out.print(res[j]);
                }
            }
            System.out.println();
        }
        return res[0];
    }
}