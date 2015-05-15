import java.util.*;

public class CourseScheduleii {
    public static void main(String[] args) {
        CourseScheduleii cs = new CourseScheduleii();
        int[][] p = {{0, 1}, {1, 0}};
        int[] res = new int[2];
        res = cs.findOrder(2, p);
        for(int i=0; i<res.length; i++)
            System.out.print(res[i]+" ");
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] resArr = new int[numCourses];
        for(int i=0; i<numCourses; i++)
            resArr[i] = i;
        if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
            return resArr;
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0; i<numCourses; i++)
            res.add(new ArrayList<Integer>());
        for(int i=0; i<prerequisites.length; i++) {
            for(int j=1; j<prerequisites[i].length; j++)
                res.get(prerequisites[i][0]).add(prerequisites[i][j]);
        }
        
        for(int i=0; i<res.size(); i++) {
            for(int j=0; j<res.get(i).size(); j++)
                System.out.print(res.get(i).get(j));
            System.out.println();
        }

        for(int i=0; i<numCourses; i++) {
            if(helper(i, visited, recStack, res))
                return resArr;
        }
        System.out.println("finish detect");
        Arrays.fill(visited, false);

        // Queue<Integer> queue1 = new PriorityQueue<Integer>();
        // queue1.offer(1);queue1.offer(2);queue1.offer(10); queue1.offer(5);
        // Iterator<Integer> iter = queue1.iterator();
        // while(iter.hasNext())
        //     System.out.print(iter.next());
        // System.out.println();

        Queue<Integer> queue = new LinkedList<Integer>();
        //Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i<numCourses; i++) {
            if(!visited[i])
                tps(i, visited, queue, res);
        }
        int i=0;
        System.out.println("poll from queue");
        while(queue.size()>0) {
            int tmp = queue.poll();
            System.out.print(tmp);
            resArr[i] = tmp;
            i++;
        }
        System.out.println();
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
        System.out.println("enter tps");
		for(int i=0; i<item.size(); i++) {
			if(!visited[item.get(i)]) {
				System.out.println("print " + item.get(i));
                tps(item.get(i), visited, queue, res);
            }
		}
        System.out.println("add v= " + v);

        // queue.add(1);
        // queue.add(2);
        // queue.add(3);
        queue.add(v);

        Iterator<Integer> i = queue.iterator();
        while(i.hasNext()) {
            System.out.print(i.next());
        }
        System.out.println();
    }
}