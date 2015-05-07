public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
            return true;
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0; i<numCourses; i++)
            res.add(new ArrayList<Integer>());
        for(int i=0; i<prerequisites.length; i++) {
            for(int j=1; j<prerequisites[i].length; j++)
                res.get(prerequisites[i][0]).add(prerequisites[i][j]);
        }
        
        for(int i=0; i<numCourses; i++) {
            if(helper(i, visited, recStack, res))
                return false;
        }
        return true;
    }
    
    private boolean helper(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> res) {
        if(!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            
            List<Integer> item = res.get(v);
            for(int i=0; i<item.size(); i++) {
                if(!visited[item.get(i)] && helper(item.get(i), visited, recStack, res))
                    return true;
                else if(recStack[item.get(i)])
                    return true;
            }
        }
        recStack[v] = false;
        return false;
    }
}