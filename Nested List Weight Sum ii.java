Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1 at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)



Java:
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        return helper(nestedList, 0);
    }
    
    private int helper(List<NestedInteger> nestedList, int prev) {
        int intSum = prev, listSum = 0;
        List<NestedInteger> list = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) intSum += ni.getInteger();
            else list.addAll(ni.getList());
        }
        if (list.size() > 0) listSum = helper(list, intSum);
        return intSum + listSum;
    }
}


[1, [4, [6]]]
prev = 0 intSum = 1 (0 + 1) listSum = 1 + 16
    prev = 1 intSum = 5 (1 + 4) listSum = 11
        prev = 5 intSum = 11 (6 + 5) listSum = 0

O(n*maxDepth)

https://leetcode.com/discuss/110042/share-my-2ms-intuitive-one-pass-no-multiplication-solution