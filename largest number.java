public class Solution {
    public String largestNumber(int[] num) {
        if(num==null)
            return null;
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<num.length; i++)
            list.add(num[i]);
        Collections.sort(list, new Comparator<Integer>(){   //排序
           public int compare(Integer i1, Integer i2) {
               String str1 = ""+i1+i2;
               String str2 = ""+i2+i1;
               return str2.compareTo(str1);
           }
        });
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++)
            sb.append(list.get(i));
        if(sb.charAt(0)=='0')   //处理特殊情况 [0,0] output should be "0" instead of "00"
            return "0";
        return sb.toString();
    }
}

这题最简单的方法其实是，比较两个字符串s1和s2时，比较s1+s2和s2+s1即可。注意这里有一个特殊情况，就是组合后的字符串以0开始，需要把开始的0都去掉

时间O(nlogn) 空间O(n)

易错样例：
Input:     [0,0]
Output:    "00"
Expected:  "0"