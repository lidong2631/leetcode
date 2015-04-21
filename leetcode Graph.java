Clone Graph
1 DFS
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
return DFS(node, map);

UndirectedGraphNode DFS(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map)
    if(map.containsKey(node))
        return map.get(node);
    UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
    for(UndirectedGraphNode neighbor : node.neighbors)
        nodeCopy.neighbors.add(DFS(neighbor, map));

时间O(n) 空间O(n)

2 BFS
Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
map.put(node, nodeCopy);
queue.add(node);
while(!queue.isEmpty()) {
    UndirectedGraphNode curr = queue.poll();
    for(UndirectedGraphNode p : curr.neighbors) {
        if(map.containskey(p))
            map.get(curr).add(map.get(p));
        else {
            UndirectedGraphNode pCopy = new UndirectedGraphNode(p.label);
            map.put(p, pCopy);
            map.get(curr).add(pCopy);
            queue.add(p);
        }
    }
}
return nodeCopy;

时间O(n) 空间O(n)