// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

    private boolean hasPeeked;
    private Iterator<Integer> i;
    private Integer peekElement;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    hasPeeked = false;
	    i = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(!hasPeeked) {
            peekElement = i.next();
            hasPeeked = true;
        }
        return peekElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(hasPeeked) {
	        Integer tmp = peekElement;
	        hasPeeked = false;
	        peekElement = null;
	        return tmp;
	    }
	    return i.next();
	}

	@Override
	public boolean hasNext() {
	    return hasPeeked || i.hasNext();
	}
}

用三个变量hasPeeked, peekElement, Iterator<Integer> i
hasPeeked记录是否peek过
peekElement存储peek的值
i是普通迭代器
当没peek时 用普通i的迭代器方法next() 否则直接返回peekElement

https://leetcode.com/discuss/59368/concise-java-solution

For a clean implementation, check out Google's guava library source code.