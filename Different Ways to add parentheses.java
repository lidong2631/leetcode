public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String s1 = input.substring(0, i), s2 = input.substring(i+1);
                List<Integer> l1 = diffWaysToCompute(s1);
                List<Integer> l2 = diffWaysToCompute(s2);
                for (Integer i1 : l1) {
                    for (Integer i2 : l2) {
                        switch (input.charAt(i)) {
                            case '+':
                                res.add(i1+i2);
                                break;
                            case '-':
                                res.add(i1-i2);
                                break;
                            case '*':
                                res.add(i1*i2);
                                break;
                        }
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.parseInt(input));
        return res;
    }
}

NP

https://leetcode.com/discuss/48477/a-recursive-java-solution-284-ms


Given a string of numbers and operators, return all possible results from computing all the different possible ways 
to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]