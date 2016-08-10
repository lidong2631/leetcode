import java.util.*;
public class Solution {

    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    ListNode head = null;
    Random randomGenerator = null;
    public Solution(ListNode head) {
        this.head = head;
        this.randomGenerator = new Random();

    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode result = null;
        ListNode current = head;
        
        for(int n = 1; current!=null; n++) {
            if (randomGenerator.nextInt(n) == 0) {
                result = current;
            }
            current = current.next;
        }
        
        return result.val;
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
*/


Given a singly linked list, return a random node value from the linked list. Each node must have the same probability of being chosen.

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



see reservoir sampling
https://discuss.leetcode.com/topic/53753/brief-explanation-for-reservoir-sampling
https://en.wikipedia.org/wiki/Reservoir_sampling

also see knuth shuffle
https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle

https://discuss.leetcode.com/topic/53738/o-n-time-o-1-space-java-solution/2