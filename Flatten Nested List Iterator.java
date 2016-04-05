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
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> res;

    public NestedIterator(List<NestedInteger> nestedList) {
        res = new LinkedList<Integer>();
        if (nestedList == null || nestedList.size() == 0)
            return;
        flatten(nestedList, res);
    }

    @Override
    public Integer next() {
        if (hasNext())
            return res.remove(0);
        return null;
    }

    @Override
    public boolean hasNext() {
        return res.size() > 0;
    }
    
    public void flatten(List<NestedInteger> nestedList, List<Integer> res) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger())
                res.add(ni.getInteger());
            else
                flatten(ni.getList(), res);
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */



https://leetcode.com/discuss/95846/easy-to-understand-java-solution