# Definition for a undirected graph node
# class UndirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []

class Solution:
    def dfs(self, originalNode, map):
        if originalNode in map:                             #如果原始点已存在map中直接返回
            return map[originalNode]
        newNode = UndirectedGraphNode(originalNode.label)   #否则新建一个copy点 并映射进map中
        map[originalNode] = newNode
        for neighbor in originalNode.neighbors:             #循环遍历每一个原始点的邻居
            newNode.neighbors.append(self.dfs(neighbor, map))   #为每一个新建点加入原始点的邻居
        return newNode
        
    # @param node, a undirected graph node
    # @return a undirected graph node
    def cloneGraph(self, node):
        if node == None:
            return None
        return self.dfs(node, {})

Note: 要搞清python中函数声明，调用，嵌套的规则 何时写self何时不写



题意：实现对一个图的深拷贝。

解题思路：由于遍历一个图有两种方式：bfs和dfs。所以深拷贝一个图也可以采用这两种方法。

不管使用dfs还是bfs都需要一个哈希表map来存储原图中的节点和新图中的节点的一一映射。

map的作用在于替代bfs和dfs中的visit数组，一旦map中出现了映射关系，就说明已经复制完成，也就是已经访问过了。

dfs代码：

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



From cleanCode:
1 DFS
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
        if(node==null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return DFS(node, map);
    }
    
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
}

时间O(n) 空间O(n)


2 BFS
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
    }
}

时间O(n) 空间O(n)



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
        if(node==null) return null;
        
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        
        UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);     //先建立起始点node的copy点
        
        map.put(node, copyNode);                    //建立映射关系
        queue.offer(node);                          //将node入队列
        
        while(!queue.isEmpty())
        {
            UndirectedGraphNode curr = queue.poll();        //每次将队首点pop出来 逐一遍历跟它相连的点
            for(int i=0; i<curr.neighbors.size(); i++)
            {
                if(!map.containsKey(curr.neighbors.get(i)))         //如果map里没有映射关系 3步 1 new一个copy店 2 map建立映射关系 3 将这个点（原始点）入队列
                {
                    UndirectedGraphNode copyNeighbors = new UndirectedGraphNode(curr.neighbors.get(i).label);
                    map.put(curr.neighbors.get(i), copyNeighbors);
                    queue.offer(curr.neighbors.get(i));
                }
                map.get(curr).neighbors.add(map.get(curr.neighbors.get(i)));        //最后不管这个点是不是没遍历过的点 将它加到copy的点的neighbor list中  
            }
        }
        return map.get(node);
    }
}

Note：   。    以这个为例子想想就明白了 BFS遍历熟记熟记熟记！！！！！！！！！！！！！！！！
       /\
      。  。



这题dfs code ganker的写法递归能明白 非递归感觉还是bfs！！








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
