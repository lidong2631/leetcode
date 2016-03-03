http://segmentfault.com/a/1190000003791233
 
public class Vector2D {

    private List<Iterator<Integer>> list;
    private int curr;

    public Vector2D(List<List<Integer>> vec2d) {
        list = new ArrayList<Iterator<Integer>>();
        curr = 0;
        for(List<Integer> l : vec2d) {
            if(l.size()>0)
                list.add(l.iterator());
        }
    }

    public int next() {
        Integer i = list.get(curr).next();
        if(!list.get(curr).hasNext())
            curr++;
        return i;
    }

    public boolean hasNext() {
        return curr<list.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
O(n) O(1)



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