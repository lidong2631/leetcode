public int shortestDistance(String[] words, String word1, String word2) {
    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) 
            p1 = i;

        if (words[i].equals(word2)) 
            p2 = i;

        if (p1 != -1 && p2 != -1)
            min = Math.min(min, Math.abs(p1 - p2));
    }

    return min;
}

O(n) O(1)

https://leetcode.com/discuss/50234/ac-java-clean-solution



我的解法 思路差不多 代码多好多..

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int min = words.length+1, left = 0;
        Set<String> set = new HashSet<String>();
        for(int i=0; i<words.length; i++) {
            if(!words[i].equals(word1) && !words[i].equals(word2))
                continue;
            else if(words[i].equals(word1) && (!set.contains(word1) && !set.contains(word2))) {
                left = i;
                set.add(word1);
            }
            else if(words[i].equals(word2) && (!set.contains(word1) && !set.contains(word2))) {
                left = i;
                set.add(word2);
            }
            else if(words[i].equals(word1) && set.contains(word1))
                left = i;
            else if(words[i].equals(word2) && set.contains(word2))
                left = i;
            else {
                min = Math.min(min, i-left);
                set.clear();
                set.add(words[i]);
                left = i;
            }
        }
        return min;
    }
}

O(n) O(1)