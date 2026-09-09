package io.neetcode.list;

class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode nextTemplate = current.next;
            current.next = prev;
            prev = current;
            current = nextTemplate;
        }
        return prev;
    }
}
