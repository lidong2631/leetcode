public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null) return res;
            
        Set<String> set = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(s);
        
        boolean found = false;
        
        while(!queue.isEmpty()) {   //bfs
            String tmp = queue.poll();
            
            if(isValid(tmp)) {
                res.add(tmp);
                found = true;
            }
            
            if(found) continue;                                 // if found is true then pop all the string in queue and we are done
            
            for(int i=0; i<tmp.length(); i++) {                 // every time remove one '(' or ')'
                if(tmp.charAt(i)!='(' && tmp.charAt(i)!=')')
                    continue;
                String item = tmp.substring(0,i) + tmp.substring(i+1);
                if(!set.contains(item)) {                       // add it to the set and queue
                    set.add(item);
                    queue.offer(item);
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s) {                         // we do not need stack to check valid parentheses because we only have '(' and ')'
        int count = 0;
        
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='(') count++;
            if(s.charAt(i)==')' && count--==0) return false;
        }
        return count==0;
    }
}


The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), 
check if they are valid, if found valid ones on the current level, put them to the final result list and we are done, 
otherwise, add them to a queue and carry on to the next level.

The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, 
also no recursion call is needed in BFS.

Thanks to @peisi, we dont need stack to check valid parentheses.

Time complexity:

In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the time 
complexity level by level and add them up to get the final complexity.

On the first level, there is only one string which is the input string s, lets say the length of it is n, to check whether 
its valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) 
new strings, each of them has n-1 characters, and for each string, we need to check whether it's valid or not, 
thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time complexity 
is (n-2) x C(n, n-2), so on and so forth...

Finally we have this formula:

T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).

https://leetcode.com/discuss/67842/share-my-java-bfs-solution