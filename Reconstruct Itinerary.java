Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 

All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK

tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

      1    3
JFK: ATL, SFO
      2    5
ATL: JFK, SFO
      4
SFO: ATL



Java:
public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> res = new LinkedList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();   // use Heap as val to sort the string
        
        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) 
                map.put(ticket[0], new PriorityQueue<String>());
            map.get(ticket[0]).add(ticket[1]);
        }
        
        dfs("JFK", res, map);
        return res;
    }
    
    private void dfs(String depart, LinkedList<String> res, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> arrivals = map.get(depart);
        while (arrivals != null && !arrivals.isEmpty()) {   // careful arrivals != null
            dfs(arrivals.poll(), res, map);
        }
        res.addFirst(depart);
    }
}

https://leetcode.com/discuss/84702/share-my-solution

Eulerian path
https://en.wikipedia.org/wiki/Eulerian_path
Seven Bridges of Königsberg
https://en.wikipedia.org/wiki/Seven_Bridges_of_K%C3%B6nigsberg