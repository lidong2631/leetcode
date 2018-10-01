Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.



Java:
public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        if (n <= 0) return res;
        for (int i = 1; i <= 9; i++) {      // first digit 1 - 9
            helper(n, i, res);
        }
        return res;
    }
    
    private void helper(int n, int num, List<Integer> res) {    // dfs
        if (num > n) return;
        res.add(num);
        for (int i = 0; i <= 9; i++) {      // from second digit 0 - 9
            helper(n, 10*num+i, res);
        }
    }
}

In the lexicographical order we can see that the first number is 1. The next number is 10, 11, 12 and so on up until 19. 

Then the next number is 100, 101, ... We can see that it is digit based. So, first we start with 1 in the first digit and 

keep adding digits to the right of 1 as long as it is less than n. Next, we start with 2 as the first digit and do the same


https://discuss.leetcode.com/topic/55086/java-backtracking-solution-similar-to-subsets