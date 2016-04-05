/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int[] res = new int[1];
        int level = 1;
        dfs(nestedList, 1, res);
        return res[0];
    }
    
    private void dfs(List<NestedInteger> nestedList, int level, int[] res) {
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger())
                res[0] += (int)ni.getInteger() * level;
            else
                dfs(ni.getList(), level+1, res);
        }
    }
}