public class Solution {
    public String getHint(String secret, String guess) {
        int[] digits = new int[10];
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            int d1 = secret.charAt(i) - '0', d2 = guess.charAt(i) - '0';
            if (d1 == d2) {
                bulls++;
                continue;
            }
            if (digits[d1] < 0) cows++;
                digits[d1]++;
            if (digits[d2] > 0) cows++;
                digits[d2]--;
        }
        return bulls + "A" + cows + "B";
    }
}




public String getHint(String secret, String guess) {
    int bulls = 0;
    int cows = 0;
    int[] numbers = new int[10];
    for (int i = 0; i<secret.length(); i++) {
        int s = Character.getNumericValue(secret.charAt(i));
        int g = Character.getNumericValue(guess.charAt(i));
        if (s == g) bulls++;
        else {
            if (numbers[s] < 0) cows++;     // if numbers[s] is negative then previous guess has chosen this number cows++
            if (numbers[g] > 0) cows++;     // if numbers[g] is positive then previous secret has this number cows++
            numbers[s] ++;
            numbers[g] --;
        }
    }
    return bulls + "A" + cows + "B";
}

O(n) O(1)

secret: "1807"
guess:  "7810"

numbers[1] = 1, numbers[7] = -1
bulls = 1
cows = 1, numbers[0] = 1, numbers[1] = 0
cows = 2, cows = 3, numbers[7] = 0, numbers[0] = 0


https://leetcode.com/discuss/67031/one-pass-java-solution

