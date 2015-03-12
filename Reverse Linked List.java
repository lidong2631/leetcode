Iterative

public void reverse(ListNode head) {
    if(head==null || head.next==null)
        return;
    ListNode second = head.next;
    ListNode third = second.next;

    second.next = head; //reverse
    head.next = null;

    if(third==null)
        return;

    ListNode curr = third;
    ListNode pre = second;
    while(curr!=null) { //iterative reverse
        ListNode next = curr.next;
        curr.next = pre;
        pre = curr;
        curr = next;
    }
    head = pre;
}



Recursive

public void reverse(ListNode curr) {
    if(curr==null)  //check for empty list
        return;

    if(curr.next==null) {   //base case
        head = curr;
        return;
    }

    reverse(curr.next);
    curr.next.next = curr;
    curr.next = null;
}