public class Solution {
    public String largestNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (Integer n : nums) list.add(n);
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                String s1 = "" + i1 + i2;
                String s2 = "" + i2 + i1;
                return s2.compareTo(s1);
            }
        };
        Collections.sort(list, cmp);
        StringBuffer res = new StringBuffer();
        for (Integer i : list) res.append(i);
        if (res.charAt(0) == '0') return "0";   // careful
        return res.toString();
    }
}

这题最简单的方法其实是，比较两个字符串s1和s2时，比较s1+s2和s2+s1即可。注意这里有一个特殊情况，就是组合后的字符串以0开始，需要把开始的0都去掉

时间O(nlogn) 空间O(n)

易错样例：
Input:     [0,0]
Output:    "00"
Expected:  "0"