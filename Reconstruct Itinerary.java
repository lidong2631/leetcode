public class Solution {
    Map<String, PriorityQueue<String>> map;
    LinkedList<String> res;                    // must declared as LinkedList because addFirst method only declared in LinedList
    
    public List<String> findItinerary(String[][] tickets) {
        map = new HashMap<String, PriorityQueue<String>>();
        res = new LinkedList<String>();
        
        for(String[] ticket : tickets) {        // create map
            map.putIfAbsent(ticket[0], new PriorityQueue<String>());
            map.get(ticket[0]).add(ticket[1]);
        }
        
        dfs("JFK");         //start from JFK doing DFS
        return res;
    }
    
    public void dfs(String depart) {
        PriorityQueue<String> arrivals = map.get(depart);
        while(arrivals!=null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        res.addFirst(depart);
    }
}


tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

      1    3
JFK: ATL, SFO
      2    5
ATL: JFK, SFO
      4
SFO: ATL


https://leetcode.com/discuss/84702/share-my-solution

Eulerian path
https://en.wikipedia.org/wiki/Eulerian_path
Seven Bridges of Königsberg
https://en.wikipedia.org/wiki/Seven_Bridges_of_K%C3%B6nigsberg