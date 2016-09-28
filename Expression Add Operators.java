public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        helper(num, res, new StringBuffer(), target, 0, 0, 0);
        return res;
    }
    
    private void helper(String num, List<String> res, StringBuffer sb, int target, int index, long prev, long multi) {
        if (index == num.length()) {
            if (prev == target)
                res.add(sb.toString());
            return;
        }
        for (int i = index; i < num.length(); i++) {
            if (num.charAt(index) == '0' && i != index)             // case like "043"
                break;
            long curr = Long.parseLong(num.substring(index, i+1));  // get curr number
            int len = sb.length();
            if (index == 0) {
                helper(num, res, sb.append(curr), target, i+1, curr, curr);
                sb.setLength(len);                                  // restore for next loop
            }
            else {
                helper(num, res, sb.append('+').append(curr), target, i+1, prev+curr, curr);    // 3 operators
                sb.setLength(len);
                helper(num, res, sb.append('-').append(curr), target, i+1, prev-curr, -curr);
                sb.setLength(len);
                helper(num, res, sb.append('*').append(curr), target, i+1, prev-multi+curr*multi, curr*multi);
                sb.setLength(len);
            }
        }
    }
}

edge cases we need to be aware:
1 overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
2 0 sequence: because we cant have numbers with multiple digits started with zero, we have to deal with it too.
3 a little trick is that we should save the value that is to be multiplied in the next recursion

prev-multi+curr*multi:
for example, if you have a sequence of 12345 and you have proceeded to 1 + 2 + 3, now your eval is 6 right? 
If you want to add a * between 3 and 4, you would take 3 as the digit to be multiplied, so you want to take it 
out from the existing eval. You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4)

https://leetcode.com/discuss/58614/java-standard-backtrace-ac-solutoin-short-and-clear


Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators 
(not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []