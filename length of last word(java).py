class Solution:
    # @param s, a string
    # @return an integer
    def lengthOfLastWord(self, s):
        if s.split() == []:
            return 0
        else:
            return len(s.split()[len(s.split())-1])








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