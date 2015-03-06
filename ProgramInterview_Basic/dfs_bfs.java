For undirected graph

dfs:

Input: A graph G and a vertex v of G

Output: All vertices reachable from v labeled as discovered

A recursive implementation of DFS:[4]

1  procedure DFS(G,v):
2      label v as discovered
3      for all edges from v to w in G.adjacentEdges(v) do
4          if vertex w is not labeled as discovered then
5              recursively call DFS(G,w)


A non-recursive implementation of DFS:[5]

1  procedure DFS-iterative(G,v):
2      let S be a stack
3      S.push(v)
4      while S is not empty
5            v ← S.pop() 
6            if v is not labeled as discovered:
7                label v as discovered
8                for all edges from v to w in G.adjacentEdges(v) do
9                    S.push(w)

Recursively:

public DepthFirst(Graph G, int start) {
	boolean[] visited = new boolean[G.V()];
	dfs(G, start);
}

public void DFS(Graph G, int s) {
	marked[s] = true;
	for(int w : G.adj(s))
	{
		if(!marked[w])
			dfs(G, w);
	}
}

Iteratively:

public void DFS(Graph G, int start) {
	LinkedList<Integer> stack = new LinkedList<Integer>();
	stack.push(start);

	while(!stack.isEmpty())
	{
		int s = stack.pop();
		if(!marked[s])
		{
			marked[s] = true;
			for(int w : G.adj(s))
				stack.push(w);
		}
	}
}









bfs:

Pseudocode[edit]
Input: A graph G and a root v of G

1  procedure BFS(G,v) is
2      create a queue Q
3      create a set V
4      add v to V
5      enqueue v onto Q
6      while Q is not empty loop
7         t ← Q.dequeue()
8         if t is what we are looking for then
9            return t
10        end if
11        for all edges e in G.adjacentEdges(t) loop
12           u ← G.adjacentVertex(t,e)
13           if u is not in V then
14               add u to V
15               enqueue u onto Q
16           end if
17        end loop
18     end loop
19     return none
20 end BFS


public void BFS(Graph G, int start) {
	LinkedList<Integer> queue = new LinkedList<Integer>();
	HashSet<Integer> set = new HashSet<Integer>();

	queue.offer(start);
	set.add(start);

	while(!queue.isEmpty())
	{
		int s = queue.poll();
		for(int w : G.adj(s))
			if(!set.contains(w))
			{
				set.add(w);
				queue.offer(w);
			}
	}
}











Time and space complexity[edit]
The time complexity can be expressed as O(|V|+|E|) [1] since every vertex and every edge will be explored in the worst case. 

Note: O(|E|) may vary between O(1) and  O(|V|^2), depending on how sparse the input graph is.

When the number of vertices in the graph is known ahead of time, 

and additional data structures are used to determine which vertices have already been added to the queue, 

the space complexity can be expressed as O(|V|) where |V| is the cardinality of the set of vertices. 

If the graph is represented by an Adjacency list it occupies \Theta(|V|+|E|)[2] space in memory, 

while an Adjacency matrix representation occupies \Theta(|V|^2).[