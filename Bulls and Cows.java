public String getHint(String secret, String guess) {
    int bulls = 0;
    int cows = 0;
    int[] numbers = new int[10];
    for (int i = 0; i<secret.length(); i++) {
        int s = Character.getNumericValue(secret.charAt(i));
        int g = Character.getNumericValue(guess.charAt(i));
        if (s == g) bulls++;
        else {
            if (numbers[s] < 0) cows++;
            if (numbers[g] > 0) cows++;
            numbers[s] ++;
            numbers[g] --;
        }
    }
    return bulls + "A" + cows + "B";
}

O(n) O(1)

https://leetcode.com/discuss/67031/one-pass-java-solution


My Solution

public class Solution {
    public String getHint(String secret, String guess) {
        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapG = new HashMap<Character, Integer>();
        int numA = 0, numB = 0;
        for(int i=0; i<secret.length(); i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if(s==g) {
                numA++;
                continue;
            }
            if(mapG.containsKey(s) && mapG.get(s)>0) {
                mapG.put(s, mapG.get(s)-1);
                numB++;
            }
            else {
                if(mapS.containsKey(s))
                    mapS.put(s, mapS.get(s)+1);
                else 
                    mapS.put(s, 1);
            }
            if(mapS.containsKey(g) && mapS.get(g)>0) {
                mapS.put(g, mapS.get(g)-1);
                numB++;
            }
            else {
                if(mapG.containsKey(g))
                    mapG.put(g, mapG.get(g)+1);
                else
                    mapG.put(g, 1);
            }
        }
        return String.valueOf(numA)+"A"+String.valueOf(numB)+"B";
    }
}

O(n) O(n)