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