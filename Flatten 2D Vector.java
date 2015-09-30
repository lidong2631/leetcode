http://segmentfault.com/a/1190000003791233
 
public class Vector2D {
 
    private List<Iterator<Integer>> l;
    private int curr;
 
    public Vector2D(List<List<Integer>> vec2d) {
        this.l = new ArrayList<Iterator<Integer>>();
        for(List<Integer> tmpList : vec2d) {
            if(tmpList.size()>0)
                this.l.add(tmpList.iterator());
        }
    }
 
    public int next() {
        Integer i = l.get(curr).next();
        if(!l.get(curr).hasNext())
            curr++;
        return i;
    }
 
    public boolean hasNext() {
        return curr<l.size() && l.get(curr).hasNext();
    }
}
 
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