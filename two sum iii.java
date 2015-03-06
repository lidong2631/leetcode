public class TwoSum {

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    
	public void add(int number) {
	    if(map.containsKey(number))
	        map.put(number, map.get(number)+1);
	    else
	        map.put(number, 1);
	}

	public boolean find(int value) {
	    for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
	        int num1 = entry.getKey();
	        int num2 = value - num1;
	        if(num1==num2) {
	            if(entry.getValue()>1)
	                return true;
	        }
	        else if(map.containsKey(num2))
	            return true;
	    }
	    return false;
	}
}

see leetcode handbook Q3

这题顺便看下map 相关的entrySet, Entry<k,v> 如果遍历map键值对 map键 map值等