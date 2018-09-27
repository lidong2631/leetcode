Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?



Java:
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
        if (!hasPeeked) {
            peekElement = i.next();
            hasPeeked = true;
        }
        return peekElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (hasPeeked) {
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


