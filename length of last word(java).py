class Solution:
    # @param s, a string
    # @return an integer
    def lengthOfLastWord(self, s):
        if s.split() == []:
            return 0
        else:
            return len(s.split()[len(s.split())-1])




Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.



public class Solution {
    public int lengthOfLastWord(String s) {
        int j = s.length(), count = 0;
        for(int i=s.length()-1; i>=0; i--) {
            if(s.charAt(i)==' ')
                j = i;
            else if(i==0 || s.charAt(i-1)==' ') {
                count = j - i;
                break;
            }
        }
        return count;
    }
}

根据cleanCode reverse words in a string 改编 仍是确定最后单词的起始和结束位置即可

O(n) O(1)



public class Solution {
    public int lengthOfLastWord(String s) {
        if(s==null || s.length()==0)
            return 0;
        int lastwordIndex = s.length();				//这里是s.length()
        while(lastwordIndex>0 && s.charAt(lastwordIndex-1)==' ')		//注意是lastwordIndex>0
            lastwordIndex--;
        int index = lastwordIndex;
        while(index>0 && s.charAt(index-1)!=' ')				//注意是index>0
            index--;
        return lastwordIndex-index;
    }
}

Note: 简单题 但是要小心下标