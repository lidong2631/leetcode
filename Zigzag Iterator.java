public class ZigzagIterator {

    private List<Integer> list;
    private int p;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        p = 0;
        list = new ArrayList<Integer>();
        int minLen = Math.min(v1.size(), v2.size());
        int i=0;
        for(; i<minLen; i++) {
            list.add(v1.get(i));
            list.add(v2.get(i));
        }
        if(v1.size()==minLen) {
            for(; i<v2.size(); i++)
                list.add(v2.get(i));
        }
        else {
            for(; i<v1.size(); i++)
                list.add(v1.get(i));
        }
    }

    public int next() {
        return list.get(p++);
    }

    public boolean hasNext() {
        return p<list.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

思路是将两个list中的元素依次按zigzag存入一个list 然后next() hasNext()方法就可以直接便利list就好

如果扩展为k个list 仍是此思路 只是构造函数里要双层循环 并且每次要判断有没有超过list长度