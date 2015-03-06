public class reverseWord {
    public static void main(String[] args) {
    	reverseWord rw = new reverseWord();
    	rw.reverseWords("a   b");
    }

    public String reverseWords(String s) {
        s = s.trim();
        String[] strArr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        System.out.println(strArr.length);
        for(int i=strArr.length-1; i>=0; i--)
        {
            //System.out.println(strArr[i] + " " + i);
            if(!strArr[i].equals(""))
            {
            	System.out.println("strArrp[i] is not empty, strArr[i]= " + strArr[i]);
            	sb.append(strArr[i]+" ");
            }
            //System.out.println(sb);
        }
        System.out.println(sb);
        return sb.length()==0? "":sb.substring(0, sb.length()-1);
    }
}

/*
输出为

4
b 3
 2
 1
a 0

*/