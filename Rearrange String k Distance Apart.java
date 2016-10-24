public class Solution {
    public String rearrangeString(String str, int k) {
        if (str == null || str.length() == 0)
            return "";
        if (k == 0)
            return str;
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(str.length(), 
            new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> m1, Map.Entry<Character, Integer> m2) {
                return m2.getValue() - m1.getValue();
            }   
        });
        for (char c : str.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            heap.offer(entry);
        StringBuffer sb = new StringBuffer(str);
        
        int len = str.length();
        while (!heap.isEmpty()) {
            int count = Math.min(k, len);
            Map<Character, Integer> cache = new HashMap<>();
            
            for (int i = 0; i < count; i++) {
                if (heap.isEmpty()) return "";      // careful
                Map.Entry<Character, Integer> entry = heap.poll();
                sb.append(entry.getKey());
                int val = entry.getValue();
                if (--val > 0) cache.put(entry.getKey(), val);
                len--;
            }
            for (Map.Entry<Character, Integer> entry : cache.entrySet())
                heap.offer(entry);
        }
        return sb.substring(str.length()).toString();
    }
}

aaadbbcc k = 2

Map:
a: 3 b: 2 c: 2 d: 1

first for
abc
a: 2 d: 1 b: 1 c: 1

second for
abcadb
a: 1 c: 1

third for
abcadbac


Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string ""

str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.

str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.

str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"



https://leetcode.com/discuss/108174/c-unordered_map-priority_queue-solution-using-cache

http://www.cnblogs.com/grandyang/p/5586009.html






Rearrange a string so that all same characters become d distance (exactly) away

public class Solution {
    public String rearrangeString(String str, int k) {
        if (str == null || str.length() == 0)
            return "";
        if (k == 0)
            return str;
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(str.length(), 
            new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> m1, Map.Entry<Character, Integer> m2) {
                return m2.getValue() - m1.getValue();
            }   
        });
        for (char c : str.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            heap.offer(entry);
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++)
            charArr[i] = '#';
            
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            char key = entry.getKey();
            int freq = entry.getValue();
            int i = 0;
            while (charArr[i] != '#')
                i++;
            for (int j = 0; j < freq; j++) {
                if (i + j*k >= charArr.length)
                    return "";
                charArr[i+j*k] = key;
            }
        }
        return String.valueOf(charArr);
    }
}

Time Complexity: Time complexity of above implementation is O(n + mLog(MAX)). Here n is the length of str, m is count of distinct 

characters in str[] and MAX is maximum possible different characters. MAX is typically 256 (a constant) and m is smaller than MAX. 

So the time complexity can be considered as O(n).

http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/