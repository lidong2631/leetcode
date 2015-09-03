public class Solution {
    public String numberToWords(int num) {
        String[] bigString = new String[]{"", "Thousand ", "Million ", "Billion "};     //note space there
        String res = "";
        int i = 0;
        while(num>0) {      //check thousand->million->billion
        	if(num%1000!=0)
        		res = helper(num%1000) + bigString[i] + res;
        	i++;
        	num/=1000;
        }
        return res.length()!=0?res.trim():"Zero";
    }
    
    private String helper(int num) {
    	String[] digit = new String[]{"Zero", "One", "Two", "Three", "Four",
    								  "Five", "Six", "Seven", "Eight", "Nine"};
    	String[] teen = new String[]{"Ten", "Eleven", "Twelve", "Thirteen",
    								"Fourteen", "Fifteen", "Sixteen", "Seventeen",
    								"Eighteen", "Nineteen"};
    	String[] ten = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty",
    								"Sixty", "Seventy", "Eighty", "Ninety"};
    	String res = "";
    	if(num>99) {       //hundred
    		res+=digit[num/100]+" Hundred ";
    		num%=100;
    	}
    	if(num>=10 && num<=19) {   //teen
    		res+=teen[num-10] + " ";
    	}
    	else {         //ten and less than ten
    		if(num>=20) {
    			res+=ten[num/10] + " ";
    		}
    		num%=10;
    		if(num>0) {
    			res+=digit[num] + " ";
    		}
    	}
    	return res;
    }
}



https://leetcode.com/discuss/55273/my-java-solution
https://leetcode.com/discuss/55462/my-clean-java-solution-very-easy-to-understand