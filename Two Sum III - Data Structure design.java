From cleanCode

public class TwoSum {

    private Map<Integer, Integer> table = new HashMap<>();
    
	public void add(int number) { 
	    int count = table.containsKey(number)? table.get(number) : 0;
	    table.put(number, count+1);
	}

	public boolean find(int value) {
	    for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
	        int num = entry.getKey();
	        int y = value - num;
	        if (y == num) {
	            if (entry.getValue()>=2)
	                return true;
	        }
	        else if (table.containsKey(y))
	            return true;
	    }
	    return false;
	}
}

用一个哈希表存储数据 键为数字 值为该数字出现的次数 add操作时间为O(1) 因为只是往哈希表里加值操作 find操作要从哈希表中取出每一个数字看有没有

匹配的two sum 注意当有重复值时要判断其值是否大于等于2 如果只有一个数字则无法构成two sum 这样每次找数是常量时间一共n个数所以是O(n) 空间需要

一个O(n)的哈希表

这题还有其他的设计也要看一看 这种题主要是开放思路 不同设计会有不同的优缺点 要能够比较出他们好处坏处



1 add O(n)  find O(1)   Space: O(n^2)
Store all possible sum pairs in a hashtable. We also need a list of all added numbers. Each add go through the list and form
new sum pairs that put into the hashtable. For find, it is just a look up in the hashtable. This is useful if the find operation
is far exceed the add operation


public class TwoSum {

    private Set<Integer> set = new HashSet<Integer>();
    private List<Integer> list = new ArrayList<Integer>();
    
	public void add(int number) { 
	    for(Integer num : list) {
	        if(!set.contains(num+number))
	            set.add(num+number);
	    }
	    list.add(number);
	    if(list.size()==1)
	        set.add(number);
	}

	public boolean find(int value) {
	    if(set.contains(value) && list.size()>1)
	        return true;
	    else
	        return false;
	}
}