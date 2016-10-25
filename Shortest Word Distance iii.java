public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2)) {
                    if (p1 != -1) res = Math.min(res, i - p1);
                    p1 = i;         // careful
                }
                else {
                    if (p2 != -1) res = Math.min(res, i - p2);
                    p1 = i;
                }
            }
            else if (words[i].equals(word2)) {
                if (p1 != -1) res = Math.min(res, i - p1);
                p2 = i;
            }
        }
        return res;
    }
}

O(n) O(1)

https://leetcode.com/discuss/50360/my-concise-java-solution