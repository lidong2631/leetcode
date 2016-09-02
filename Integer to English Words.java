public class Solution {
    public String numberToWords(int num) {
        String[] big = {"", "Thousand ", "Million ", "Billion "};   // careful need to have tail space
        String res = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) res = helper(num%1000) + big[i] + res; // careful num%1000 before go into helper
            i++;
            num /= 1000;
        }
        return (res.length() == 0) ? "Zero" : res.trim();
    }
    
    private String helper(int num) {
        String str = "";
        String[] digit = {"Zero", "One", "Two", "Three", "Four",
                        "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teen = {"Ten", "Eleven", "Twelve", "Thirteen",
                        "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] ten = {"", "", "Twenty", "Thirty", "Forty", "Fifty",
                        "Sixty", "Seventy", "Eighty", "Ninety"};    // careful two empty string at beginning
        if (num > 99) {
            str = digit[num/100] + " Hundred ";
            num %= 100;
        }
        if (num >= 10 && num <= 19) {
            str += teen[num-10] + " ";
            return str;
        }
        else {
            if (num >= 20) str += ten[num/10] + " ";
            num %= 10;  // careful
            if (num > 0) str += digit[num] + " ";
        }
        return str;
    }
}



https://leetcode.com/discuss/55273/my-java-solution
https://leetcode.com/discuss/55462/my-clean-java-solution-very-easy-to-understand


Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert 
just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? 
(middle chunk is zero and should not be printed out)



