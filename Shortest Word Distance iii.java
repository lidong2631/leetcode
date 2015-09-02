public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = words.length;
        for(int i=0; i<words.length; i++) {
            if(words[i].equals(word1)) {
                if(words[i].equals(word2)) {    //word1 word2 are same
                    if(p1!=-1 && i-p1<min) {
                        min = i - p1;
                    }
                    p1 = i;
                }
                else {
                    if(p2!=-1 && i-p2<min) {    //find word1 and word1, word2 are not same
                        min = i - p2;
                    }
                    p1 = i;
                }
            }
            else if(words[i].equals(word2)) {   //find word2
                if(p1!=-1 && i-p1<min) {
                    min = i - p1;
                }
                p2 = i;
            }
        }
        return min;
    }
}

O(n) O(1)

https://leetcode.com/discuss/50360/my-concise-java-solution