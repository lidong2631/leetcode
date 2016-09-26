public class Solution {
    public int lastRemaining(int n) {
        int head = 1, step = 1, remain = n;
        boolean left=  true;
        while (remain > 1) {
            if (left || remain % 2 == 1)
                head += step;
            remain /= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }
}

My idea is to update and record head in each turn. when the total number becomes 1, head is the only number left.

When will head be updated?

if we move from left
if we move from right and the total remaining number % 2 == 1
like 2 4 6 8 10, we move from 10, we will take out 10, 6 and 2, head is deleted and move to 4
like 2 4 6 8 10 12, we move from 12, we will take out 12, 8, 4, head is still remaining 2
then we find a rule to update our head.

https://discuss.leetcode.com/topic/59293/java-easiest-solution-o-logn-with-explanation



There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6