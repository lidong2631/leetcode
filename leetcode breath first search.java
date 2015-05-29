Leetcode Breath-First Breath

Word Ladder
将每个字符串看成图的一个节点 边连接两个相差一个字符的字符串 等价于求图一个顶点到另一个顶点的最短路径 用bfs
if(start==null || start.length()==0 || end==null || end.length==0 || start.length()==end.length())
    return 0;
Queue<String> queue = new LinkedList<String>();
Set<String> set = new HashSet<String>();
queue.add(start);
set.add(start);
int curr = 1, next = 0, level = 1;
while(!queue.isEmpty()) {
    String curr = queue.poll();
    curr--;
    for(int i=0; i<curr.length(); i++) {
        char[] charStr = curr.toCharArray();
        for(char c='a'; c<='z'; c++) {
            charStr[i] = c;
            String tmp = new String(charStr);
            if(tmp.equals(end))
                return level+1;
            if(dict.contains(tmp) && !set.contains(tmp)) {
                set.add(tmp);
                queue.add(tmp);
                next++;
            }
        }
    }
    if(curr==0) {
        curr = next;
        next = 0;
        level++;
    }
}
return 0;

O()




Surrounded Region
bfs 这题用dfs会栈溢出
1 扫一遍所有的元素 将所有'O'替换成一个特殊字符'-'
2 扫四条边 将碰到的'-'替换成'O' 并bfs flood fill所有碰到的'-'
3 在扫一遍矩阵 剩下的'-'即为surrounded region 将所有剩下的'-'替换成'X'

if(board==null || board.length==0 || board[0].length==0)
    return;
for(int i=0; i<board.length; i++) {
    for(int j=0; j<board[0].length; j++) {
        if(board[i][j]=='O')
            board[i][j] = '-';
    }
}
for(int i=0; i<board.length; i++) {
    if(board[i][0]=='-')
        floodFill(board, i, 0);
}
for(int i=0; i<board.length; i++) {
    if(board[i][board[0].length-1]=='-')
        floodFill(board, i, board[0].length-1);
}
for(int i=0; i<board[0].length; i++) {
    if(board[0][i]=='-')
        floodFill(board, 0, i);
}
for(int i=0; i<board[0].length; i++) {
    if(board[board.length-1][i]=='-')
        floodFill(board, board.length-1, i);
}

for(int i=0; i<board.length; i++) {
    for(int j=0; j<board[0].length; j++) {
        if(board[i][j]=='-')
            board[i][j] = 'X';
    }
}

private void floodFill(char[][] board, int i, int j) {
    board[i][j] = 'O';
    Queue<Integer> queue = new LinkedList<Integer>();
    int code = i*board[0].length+j;
    queue.add(code);
    while(!queue.isEmpty()) {
        code = queue.poll();
        int row = code/board[0].length;
        int col = code%board[0].length;
        if(row>0 && board[row-1][col]=='-') {   //up
            queue.add((row-1)*board[0].length+col);
            board[row-1][col] = 'O';
        }
        if(row<board.length-1 && board[row+1][col]=='-') {  //down
            queue.offer((row+1)*board[0].length+col);
            board[row+1][col] = 'O';
        }
        if(col>0 && board[row][col-1]=='-') {   //left
            queue.offer(row*board[0].length+col-1);
            board[row][col-1] = 'O';
        }
        if(col<board[0].length-1 && board[row][col+1]=='-') {   //right
            queue.offer(row*board[0].length+col+1);
            board[row][col+1] = 'O';
        }
    }
}

O(m*n) O(m*n)





Number of Islands
问题的实质是 count number of connected components in a undirected graph
1 dfs
if(grid==null || grid.length==0 || grid[0].length==0)
    return 0;
boolean[][] visited = new boolean[grid.length][grid[0].length];
int count = 0;
for(int i=0; i<grid.length; i++) {
    for(int j=0; j<grid[0].length; j++) {
        if(isValid(grid, i, j, visited)) {
            dfs(grid, i, j, visited);
            count++;
        }
    }
}
return count;
}

private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
    int[] rowNum = {-1, 0, 0, 1};
    int[] colNum = {0, 1, -1, 0};
    
    visited[i][j] = true;
    for(int k=0; k<4; k++) {
        if(isValid(grid, i+rowNum[k], j+colNum[k], visited))
            dfs(grid, i+rowNum[k], j+colNum[k], visited);
    }
}

private boolean isValid(char[][] grid, int i, int j, boolean[][] visited) {
    if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j]=='1' && !visited[i][j])
        return true;
    return false;
}

O(m*n) 空间应该是递归栈空间

2 bfs
class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public int numIslands(char[][] grid) {
    if(grid==null || grid.length==0 || grid[0].length==0)
        return 0;
    int row = grid.length;
    int col = grid[0].length;
    int count = 0;
    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            if(grid[i][j]=='1') {
                bfs(grid, i, j);
                count++;
            }
        }
    }
    return count;
}

private void bfs(char[][] grid, int i, int j) {
    Queue<Pair> queue = new LinkedList<Pair>();
    queue.offer(new Pair(i, j));
    while(!queue.isEmpty()) {
        Pair p = queue.poll();
        grid[p.x][p.y] = '#';
        if(isValid(grid, p.x-1, p.y)) {
            grid[p.x-1][p.y] = '#';
            queue.offer(new Pair(p.x-1, p.y));
        }
        if(isValid(grid, p.x+1, p.y)) {
            grid[p.x+1][p.y] = '#';
            queue.offer(new Pair(p.x+1, p.y));    
        }
        if(isValid(grid, p.x, p.y-1)) {
            grid[p.x][p.y-1] = '#';
            queue.offer(new Pair(p.x, p.y-1));
        }
        if(isValid(grid, p.x, p.y+1)) {
            grid[p.x][p.y+1] = '#';
            queue.offer(new Pair(p.x, p.y+1));
        }
    }
}

private boolean isValid(char[][] grid, int i, int j) {
    if(i<0 || i>=grid.length || j<0 || j>=grid[0].length)
        return false;
    if(grid[i][j]=='1')
        return true;
    return false;
}

O(m*n)




Course Schedule
DFS for a connected graph produces a tree
There is a cycle in a graph only if there is a back edge(from a node to itself or one of its ancestors in the tree)
To detect a back edge, we keep track of vertices in current rec stack of function for DFS traversal
there is a cycle if a vertex is already in rec stack
The edge that connects current vertex to the vertex in rec stack is a back edge 

if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
    return true;
boolean[] visited = new boolean[numCourses];    //dfs中标记节点是否访问过 一旦设为true就不会再变回来
boolean[] recStack = new boolean[numCourses];   //标记当前递归栈中该节点是否已访问过 
                                            //如果当前一轮递归访问完没有cycle 会重新设为false
List<List<Integer>> res = new ArrayList<List<Integer>>();
for(int i=0; i<numCourses; i++)       //先构建一个adjacency list
    res.add(new ArrayList<Integer>());
for(int i=0; i<prerequisites.length; i++) {
    for(int j=1; j<prerequisites[i].length; j++)
        res.get(prerequisites[i][0]).add(prerequisites[i][j]);
}

for(int i=0; i<numCourses; i++) {       //dfs
    if(helper(i, visited, recStack, res))   //helper用来detect cycle
        return false;
}
return true;

private boolean helper(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> res) {
    if(!visited[v]) {
        visited[v] = true;
        recStack[v] = true;
        
        List<Integer> item = res.get(v);    //拿到当前点的邻居list
        for(int i=0; i<item.size(); i++) {
            if(!visited[item.get(i)] && helper(item.get(i), visited, recStack, res))    //dfs将遇到的节点visited和recstack都设为true
                return true;
            else if(recStack[item.get(i)])  //如果已经访问过这个邻居节点并且其rec记录为true 说明有cycle
                return true;
        }
    }
    recStack[v] = false;    //如果遍历完当前点所有邻居都没有cycle 要将rec重新设为false
    return false;
}

O(E+V)




Course Schedule ii
Topological Sort + Detect Cycle

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





Clone Graph
1 DFS
if(node==null)
    return null;
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
return DFS(node, map);

private UndirectedGraphNode DFS(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
    if(map.containsKey(node))
        return map.get(node);
    UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
    map.put(node, nodeCopy);
    for(UndirectedGraphNode neighbor : node.neighbors) {
        nodeCopy.neighbors.add(DFS(neighbor, map));
    }
    return nodeCopy;
}

O(n) O(n)

2 BFS
if(node==null)
    return null;
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
map.put(node, nodeCopy);
queue.add(node);
while(!queue.isEmpty()) {
    UndirectedGraphNode curr = queue.poll();
    for(UndirectedGraphNode p : curr.neighbors) {    
        if(map.containsKey(p)) {            //如果map中有这个节点记录 只要将它连入当前点的neighbor即可
            map.get(curr).neighbors.add(map.get(p));
        }
        else {                              //否则要新建点 map更新记录 连入neighbor 加入队列
            UndirectedGraphNode pCopy = new UndirectedGraphNode(p.label);
            map.put(p, pCopy);
            map.get(curr).neighbors.add(pCopy);
            queue.add(p);
        }
    }
}
return nodeCopy;

O(n) O(n)





Binary Tree ZigZag Level Order Traversal




Binary Tree Right Side View
层序遍历套路 只是当遍历完一层要将最后一个点加到结果中
List<Integer> res = new ArrayList<Integer>();
if(root==null)
    return res;
LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
queue.offer(root);
int currLevel = 1;
int nextLevel = 0;
while(!queue.isEmpty()) {
    currLevel--;
    TreeNode node = queue.poll();
    if(node.left!=null) {
        nextLevel++;
        queue.offer(node.left);
    }
    if(node.right!=null) {
        nextLevel++;
        queue.offer(node.right);
    }
    if(currLevel==0) {
        currLevel = nextLevel;
        nextLevel = 0;
        res.add(node.val);
    }
}
return res;

O(n) O(n)





Binary Tree Level Order Traversal
1 递归
List<List<Integer>> res = new ArrayList<List<Integer>>();
    helper(root, 0, res);
    return res;
}

private void helper(TreeNode root, int level, List<List<Integer>> res) {
    if(root!=null) {
        if(res.size()<level+1) {	//如果是第一次到这一层 要新加一个list进res
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        helper(root.left, level+1, res);
        helper(root.right, level+1, res);
    }
}

O(n) O(logn)

2 非递归
本质就是把树看成一个有向图 然后进行一次广度优先搜索
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(root==null)
    return res;
Queue<TreeNode> queue = new LinkedList<TreeNode>();
List<Integer> item = new ArrayList<Integer>();
queue.offer(root);
int currLevel = 1, nextLevel = 0;
while(!queue.isEmpty()) {
    TreeNode curr = queue.poll();
    currLevel--;
    item.add(curr.val);
    if(curr.left!=null) {
        queue.offer(curr.left);
        nextLevel++;
    }
    if(curr.right!=null) {
        queue.offer(curr.right);
        nextLevel++;
    }
    if(currLevel==0) {
        currLevel = nextLevel;
        nextLevel = 0;
        res.add(item);
        item = new ArrayList<Integer>();
    }
}
return res;

O(n) O(n)





Binary Tree Level Order Traversal ii
只是在上一题的基础上加一句Collections.reverse(res);










