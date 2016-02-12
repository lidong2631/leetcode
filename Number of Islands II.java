public class Solution {
    private int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        if(m<=0 || n<=0)
            return res;
        int[] root = new int[m*n];
        Arrays.fill(root, -1);				// all the roots are initialized to -1
        int count = 0;						// count how many islands
        
        for(int[] pos : positions) {
            int index = pos[0]*n+pos[1];	// every time put a new island calculate index because we are in a 2-dimension
            root[index] = index;			// set its root to itself and add count initialiy
            count++;
            
            for(int[] dir : dirs) {						// in 4 directions
                int row = pos[0] + dir[0];
                int col = pos[1] + dir[1];
                int newIndex = row*n+col;				// calculate new index
                if(row<0 || row>=m || col<0 || col>=n || root[newIndex]==-1)	// if its surounding is ocean skip
                    continue;
                int p = getRoot(root, newIndex);		// otherwise there is island surround it get the surround island's root
                if(p!=index) {						// if current island's root not equal to p
                    root[index] = p;				// change its root to p
                    index = p;						// set index to p
                    count--;						// count-- now we finish merging two islands
                }
            }
            res.add(count);
        }
        return res;
    }

    private int getRoot(int[] root, int id) {
        while(id!=root[id])
            id = root[id];
        return id;
    }
}



we cannot change while loop in getRoot()

Input:
3
3
[[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]
Output:
[1,2,3,4,3,2,0]
Expected:
[1,2,3,4,3,2,1]


https://leetcode.com/discuss/69572/easiest-java-solution-with-explanations
http://www.geeksforgeeks.org/union-find/

https://leetcode.com/discuss/69392/python-clear-solution-unionfind-class-weighting-compression