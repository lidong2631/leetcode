Implement an iterator to flatten a 2d vector.

Example:

Input: 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
Output: [1,2,3,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,2,3,4,5,6].
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.






http://segmentfault.com/a/1190000003791233
Java: 
public class Vector2D {

    private List<Iterator<Integer>> list;
    private int curr;

    public Vector2D(List<List<Integer>> vec2d) {
        list = new ArrayList<Iterator<Integer>>();
        curr = 0;
        for (List<Integer> l : vec2d) {
            if (l.size() > 0)
                list.add(l.iterator());
        }
    }

    public int next() {
        Integer i = list.get(curr).next();
        if (!list.get(curr).hasNext())
            curr++;
        return i;
    }

    public boolean hasNext() {
        return curr < list.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
O(n) O(1)


mplement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.



public class Vector2D {
 
    private Iterator<List<Integer>> it;
    private Iterator<Integer> curr;
 
    public Vector2D(List<List<Integer>> vec2d) {
        it = vec2d.iterator();
    }
 
    public int next() {
        if(hasNext())
            return curr.next();
        return -1;
    }
 
    public boolean hasNext() {
        while((curr==null || !curr.hasNext()) && it.hasNext())
            curr = it.next().iterator();
        return curr!=null && curr.hasNext();
    }
}