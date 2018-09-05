Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/





# Definition for a undirected graph node
# class UndirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []

class Solution:
    # @param node, a undirected graph node
    # @return a undirected graph node
    # @BFS
    def cloneGraph(self, node):
        def dfs(input, map):
            if input in map:
                return map[input]
            output = UndirectedGraphNode(input.label)
            map[input] = output
            for neighbor in input.neighbors:
                output.neighbors.append(dfs(neighbor, map))
            return output
        if node == None: return None
        return dfs(node, {})




bfs代码：

复制代码
# Definition for a undirected graph node
# class UndirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []

class Solution:
    # @param node, a undirected graph node
    # @return a undirected graph node
    # @BFS
    def cloneGraph(self, node):
        if node == None: return None
        queue = []; map = {}
        newhead = UndirectedGraphNode(node.label)
        queue.append(node)
        map[node] = newhead
        while queue:
            curr = queue.pop()
            for neighbor in curr.neighbors:
                if neighbor not in map:
                    copy = UndirectedGraphNode(neighbor.label)
                    map[curr].neighbors.append(copy)
                    map[neighbor] = copy
                    queue.append(neighbor)
                else:
                    # turn directed graph to undirected graph
                    map[curr].neighbors.append(map[neighbor])
        return newhead




Java:
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }
    
    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (map.containsKey(node)) return map.get(node);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        for (UndirectedGraphNode n : node.neighbors) {
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }
}

时间O(n) 空间O(n)


    o
  // \\
 o ===o



Java:
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
        queue.add(node); map.put(node, nodeCopy);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();        // all the nodes polled from queue has already been visited
            for (UndirectedGraphNode n : curr.neighbors) {
                if (!map.containsKey(n)) {
                    UndirectedGraphNode nCopy = new UndirectedGraphNode(n.label);
                    map.put(n, nCopy);
                    queue.add(n);
                    map.get(curr).neighbors.add(nCopy);
                }
                else map.get(curr).neighbors.add(map.get(n));
            }
        }
        return nodeCopy;
    }
}


时间O(n) 空间O(n)






from code ganker:

这道题是LeetCode中为数不多的关于图的题目，不过这道题还是比较基础，就是考察图非常经典的方法：深度优先搜索和广度优先搜索。这道题用两种方法都可以解决，

因为只是一个图的复制，用哪种遍历方式都可以。具体细节就不多说了，因为两种方法太常见了。这里恰好可以用旧结点和新结点的HashMap来做visited的记录。

下面是广度优先搜索的代码： 

public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node==null)
        return null;
    
    LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    
    map.put(node,copy);
    queue.offer(node);
    
    while(!queue.isEmpty())
    {
        UndirectedGraphNode cur = queue.poll();
        for(int i=0;i<cur.neighbors.size();i++)
        {
            if(!map.containsKey(cur.neighbors.get(i)))
            {
                copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
                map.put(cur.neighbors.get(i),copy);
                queue.offer(cur.neighbors.get(i));
            }
            map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
        }
    }
    return map.get(node);
}

深度优先搜索的代码如下： （这个没看懂 感觉还是bfs）

public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null)
        return null;
    
    LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
   
    stack.push(node);
    
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    map.put(node,copy);
    
    while(!stack.isEmpty())
    {
        UndirectedGraphNode cur = stack.pop();
        for(int i=0;i<cur.neighbors.size();i++)
        {
            if(!map.containsKey(cur.neighbors.get(i)))
            {
                copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
                map.put(cur.neighbors.get(i),copy);
                stack.push(cur.neighbors.get(i));
            }
            map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
        }
    }
    return map.get(node);
}



当然深度优先搜索也可以用递归来实现，代码如下：

public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null)
        return null;
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    
    map.put(node,copy);
    helper(node,map);
    
    return copy;
}
private void helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map)
{
    for(int i=0;i<node.neighbors.size();i++)
    { 
        UndirectedGraphNode cur = node.neighbors.get(i);
        if(!map.containsKey(cur))
        {
            UndirectedGraphNode copy = new UndirectedGraphNode(cur.label);
            map.put(cur,copy);
            helper(cur,map);
        }
        map.get(node).neighbors.add(map.get(cur));
    }
}

这几种方法的时间复杂度都是O(n)（每个结点访问一次），而空间复杂度则是栈或者队列的大小加上HashMap的大小，也不会超过O(n)。

图的两种遍历方式是比较经典的问题了，虽然在面试中出现的不多，但是还是有可能出现的，而且如果出现了就必须做好，所以大家还是得好好掌握哈。
