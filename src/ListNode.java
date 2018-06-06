public class ListNode<anyType> {
    private ListNode<anyType> previous;
    private anyType value;
    private ListNode<anyType> next;

    public ListNode(ListNode<anyType> previous, anyType value, ListNode<anyType> next) {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }

    public anyType getValue() {
        return value;
    }

    public ListNode<anyType> getPrevious() {
        return previous;
    }

    public ListNode<anyType> getNext() {
        return next;
    }

    public void setValue(anyType value) {
        this.value = value;
    }

    public void setPrevious(ListNode<anyType> previous) {
        this.previous = previous;
    }

    public void setNext(ListNode<anyType> next) {
        this.next = next;
    }
}