From cleanCode

public class TwoSum {

    private Map<Integer, Integer> table = new HashMap<Integer, Integer>();
    
	public void add(int number) { 
	    int count = table.containsKey(number)? table.get(number) : 0;
	    table.put(number, count+1);
	}

	public boolean find(int value) {
	    for(Map.Entry<Integer, Integer> entry : table.entrySet()) {
	        int num = entry.getKey();
	        int y = value - num;
	        if(y==num) {
	            if(entry.getValue()>=2)
	                return true;
	        }
	        else if(table.containsKey(y))
	            return true;
	    }
	    return false;
	}
}

用一个哈希表存储数据 键为数字 值为该数字出现的次数 add操作时间为O(1) 因为只是往哈希表里加值操作 find操作要从哈希表中取出每一个数字看有没有

匹配的two sum 注意当有重复值时要判断其值是否大于等于2 如果只有一个数字则无法构成two sum 这样每次找数是常量时间一共n个数所以是O(n) 空间需要

一个O(n)的哈希表

这题还有其他的设计也要看一看 这种题主要是开放思路 不同设计会有不同的优缺点 要能够比较出他们好处坏处