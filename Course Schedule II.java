public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] resArr = new int[numCourses];
        for(int i=0; i<numCourses; i++)
            resArr[i] = i;

        //如果prerequisites数组为空 应该输出所有课程
        if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
            return resArr;
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        //构造图
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0; i<numCourses; i++)
            res.add(new ArrayList<Integer>());
        for(int i=0; i<prerequisites.length; i++) {
            for(int j=1; j<prerequisites[i].length; j++)
                res.get(prerequisites[i][0]).add(prerequisites[i][j]);
        }
        
        //detect cycle
        for(int i=0; i<numCourses; i++) {
            if(helper(i, visited, recStack, res)) {
                int[] tmp = {};
                return tmp;
            }
        }
        
        //重置visited 用topological sort将结果输入数组
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<Integer>();   //这里很奇怪 用PriorityQueue好像不对
        for(int i=0; i<numCourses; i++) {
            if(!visited[i])
                tps(i, visited, queue, res);
        }
        int i=0;
        while(queue.size()>0) {
            resArr[i] = queue.poll();
            i++;
        }
        return resArr;
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
    
    private void tps(int v, boolean[] visited, Queue<Integer> queue, List<List<Integer>> res) {
        visited[v] = true;
		List<Integer> item = res.get(v);
		for(int i=0; i<item.size(); i++) {
			if(!visited[item.get(i)])
				tps(item.get(i), visited, queue, res);
		}
		queue.add(v);
    }
}

折腾了半天 参考CourseScheduleii.java

几个点 
1 是实现Queue interface用PriorityQueue和LinkedList好像不一样

2 如何返回一个空数组 这里用的是int[] tmp = {}; 还有没有别的更好方法

3 代码应该可以更简练 detect cycle和topological sort可以合并写

看下data structure java那本书 里面有讲topological sort