public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 1; j < prerequisites[i].length; j++) {
                adjList.get(prerequisites[i][0]).add(prerequisites[i][j]);  // careful
            }
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (helper(visited, recStack, i, adjList)) {
                return new int[0];
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(visited, false);
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i])
                tps(visited, queue, i, adjList);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            res[i++] = queue.poll();
        }
        return res;
    }
    
    // check cycle
    private boolean helper(boolean[] visited, boolean[] recStack, int v, List<List<Integer>> adjList) {
        if (!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            List<Integer> list = adjList.get(v);
            for (int i = 0; i < list.size(); i++) {
                if (!visited[list.get(i)] && helper(visited, recStack, list.get(i), adjList))
                    return true;
                else if(recStack[list.get(i)])
                    return true;
            }
            recStack[v] = false;        // this can be here or outside if
        }
        return false;
    }
    
    // topological sort for outputing courses
    private void tps(boolean[] visited, Queue<Integer> queue, int v, List<List<Integer>> adjList) {
        visited[v] = true;
        List<Integer> list = adjList.get(v);
        for (int i = 0; i < list.size(); i++) {
            if (!visited[list.get(i)])
                tps(visited, queue, list.get(i), adjList);
        }
        queue.add(v);
    }
}




public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] resArr = new int[numCourses];
        for(int i=0; i<numCourses; i++)     //这里是为了应对没有先修课的情况 如2，[] 这时应该返回[0,1] 而不是[0,0]
            resArr[i] = i;
        if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
            return resArr;
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        List<Integer> check = new ArrayList<Integer>(); //check判断是否有cycle 0为无环 1有环
        check.add(0);
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0; i<numCourses; i++)
            res.add(new ArrayList<Integer>());
        for(int i=0; i<prerequisites.length; i++) {
            for(int j=1; j<prerequisites[i].length; j++)
                res.get(prerequisites[i][0]).add(prerequisites[i][j]);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<numCourses; i++) {
            if(!visited[i] && check.get(0)==0)
                tps(i, visited, recStack, check, queue, res);
        }
        if(check.get(0)==1) {  //根据check的值输出结果
            int[] tmp = {};     //有环
            return tmp;
        }
        int i=0;            //正常无环情况
        while(queue.size()>0) {
            resArr[i] = queue.poll();
            i++;
        }
        return resArr;
    }
    
    private void tps(int v, boolean[] visited, boolean[] recStack, List<Integer> check, Queue<Integer> queue, List<List<Integer>> res) {
        visited[v] = true;      //结合了tps和detect cycle
        recStack[v] = true;
        List<Integer> item = res.get(v);
        for(int i=0; i<item.size(); i++) {
            if(!visited[item.get(i)])       //如果没访问过就dfs递归
                tps(item.get(i), visited, recStack, check, queue, res);
            else if(recStack[item.get(i)]) {    //如果有指回之前节点的路径 设check为1
                check.set(0, 1);
                return；
            }
        }
        recStack[v] = false;    //如果当前没有环 将recStack设回为false 并将当前点加入队列
        queue.add(v);
    }
}

合并了detect cycle和tps 代码更加简洁

也可以参考下http://www.cnblogs.com/grandyang/p/4504793.html
我们从queue中每取出一个数组就将其存在结果中，最终若有向图中有环，则结果中元素的个数不等于总课程数，那我们将结果清空即可



