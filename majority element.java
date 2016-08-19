public class Solution {
    public int majorityElement(int[] num) {
        int candidate = 0, count = 0;
        for (Integer n : num) {
            if (count == 0) {
                candidate = n;
                count = 1;
            }
            else if (n == candidate) count++;
            else count--;
        }
        return candidate;
    }
}




public class Solution {
    public int majorityElement(int[] num) {
        int counter = 0, curr = 0;
        for(int i=0; i<num.length; i++) {
            if(counter==0) {
                curr = num[i];
                counter++;
            }
            else if(curr==num[i]) {
                counter++;
            }
            else
                counter--;
        }
        counter = 0;        //后面只是判断curr是不是majority leetcode假设一定有解 实际应该有这步
        for(int i=0; i<num.length; i++) {
            if(num[i]==curr)
                counter++;
        }
        if(counter<=num.length/2)
            return -1;
        return curr;
    }
}

Moore voting algorithm O(n) O(1)





See GeeksforGeeks 扩展题http://www.geeksforgeeks.org/majority-element/
 
Now give a try to below question
Given an array of 2n elements of which n elements are same and the remaining n elements are all different. 
Write a C program to find out the value which is present n times in the array. T
here is no restriction on the elements in the array. They are random (In particular they not sequential).
 
注意原题的moore’s voting algorithm只有当majority element > n/2时才有效 而扩展题说的是majority element exactly half
 
另外原题也可以用hashmap做 扫一遍数组 将num[i]记为key 出现次数记为value 最后扫一遍hashmap看哪个value大于n/2即可 
顺便复习下遍历hashmap的几种方式 见http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap


Solution for Exercise question...
Since there are 2n element in the array out of which half are same then it is sure that 1. 
either at least two same element are consecutive 2. they are potentially at most distance two.

For example. 
1. n.n.1,2,3,4,n,n,n,.....
2. n,1,n,2,n....... 
So just one scan take the element out
that either A[i] == A[i+1] or A[i] == A[i+2] for i = 0 ... 2n


Apart from the case of consecutive occurences, there can also be a case where the number 
does not appear consecutively but rather at the first or the last (or both) position(s). 
If there are no consecutive numbers found in the array, then check whether the first element occurs more than once or not. 
If it occurs then it is the required element. Otherwise check whether the last element occurs more than once in the array. 
If not, then conclude that no element occurs half the time.

Your method wont work for 2,3,4,2. Where the duplicate element is at a distance of 3 and yet satisfies all the given conditions 
of the problem. So I guess we need to check for potential gaps of 3 to take care of this minor corner case










public class Solution {
    public int majorityElement(int[] num) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<num.length; i++) {
            if(!map.containsKey(num[i]))
                map.put(num[i], 1);
            else
                map.put(num[i], map.get(num[i])+1);
        }
        for(int k : map.keySet()) {
            if(map.get(k)>num.length/2)
                return k;
        }
        return -1;
    }
}

Time complexity O(n)  space complexity O(n)
注意如何遍历一个map

If you are only interested in the keys, you can iterate through the keySet() of the map:

Map<String, Object> map = ...;

for (String key : map.keySet()) {
    // ...
}
If you only need the values, use values():

for (Object value : map.values()) {
    // ...
}
Finally, if you want both the key and value, use entrySet():

for (Map.Entry<String, Object> entry : map.entrySet()) {
    String key = entry.getKey();
    Object value = entry.getValue();
    // ...
}

http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap

http://stackoverflow.com/questions/46898/iterate-over-each-entry-in-a-map