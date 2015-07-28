public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i)=='+' || input.charAt(i)=='-' || input.charAt(i)=='*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> res1 = diffWaysToCompute(part1);
                List<Integer> res2 = diffWaysToCompute(part2);
                for(Integer k : res1) {
                    for(Integer j : res2) {
                        int c = 0;
                        switch(input.charAt(i)) {
                            case '+':
                                c = k+j;
                                break;
                            case '-':
                                c = k-j;
                                break;
                            case '*':
                                c = k*j;
                        }
                        res.add(c);
                    }
                }
            }
        }
        if(res.size()==0)
            res.add(Integer.valueOf(input));
        return res;
    }
}

NP

https://leetcode.com/discuss/48477/a-recursive-java-solution-284-ms