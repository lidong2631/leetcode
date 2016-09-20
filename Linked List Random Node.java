import java.util.Random;

public class Solution {

    private ListNode head;
    private Random randGenerator;

    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        this.randGenerator = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode curr = head;
        ListNode res = null;
        for (int i = 1; curr != null; i++) {
            if (randGenerator.nextInt(i) == 0)
                res = curr;
            curr = curr.next;
        }
        return res.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

O(n)

https://discuss.leetcode.com/topic/53738/o-n-time-o-1-space-java-solution/2
http://blog.jobbole.com/42550/
https://en.wikipedia.org/wiki/Reservoir_sampling
https://discuss.leetcode.com/topic/55049/java-solution-with-cases-explain
https://discuss.leetcode.com/topic/53753/brief-explanation-for-reservoir-sampling/2


Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();