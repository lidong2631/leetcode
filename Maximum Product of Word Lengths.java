public class Solution {
    public int maxProduct(String[] words) {
        int max = 0;
        int[] bytes = new int[words.length];
        for(int i=0; i<words.length; i++) {
            int val = 0;
            for(int j=0; j<words[i].length(); j++) {
                val |= 1<<(words[i].charAt(j)-'a');     //每个字符对应一位 abceefghijklmnopqrstuvwxyz
            }
            bytes[i] = val;
        }
        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                if((bytes[i]&bytes[j])==0)      //判断是否有相同字符
                    max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }
}

O(n^2)

https://leetcode.com/discuss/74528/bit-manipulation-java-o-n-2