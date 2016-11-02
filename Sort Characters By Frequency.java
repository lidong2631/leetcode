public class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0)
            return "";
        Map<Character, Integer> map = new HashMap<>();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
                k++;
            }
            else
                map.put(c, map.get(c)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> heap = 
            new PriorityQueue<>(k, new Comparator<Map.Entry<Character, Integer>>() {
                public int compare(Map.Entry<Character, Integer> m1, Map.Entry<Character, Integer> m2) {
                    return m2.getValue() - m1.getValue();
                }
            });
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            heap.offer(entry);
        }
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            char key = entry.getKey();
            int val = entry.getValue();
            for (int i = 0; i < val; i++)
                sb.append(key);
        }
        return sb.toString();
    }
}


same as Top K Frequent Element



Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.