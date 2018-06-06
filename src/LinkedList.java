public class LinkedList<anyType> {
    private ListNode<anyType> head = null;
    private int               size = 0;

    public LinkedList() {
    }

    public boolean add(anyType x) {
        addLast(x);

        return true;
    }

    public boolean add(int index, anyType x) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + " is out of bounds!");

        ListNode<anyType> current = head;

        if (index == size - 1) {
            addLast(x);
            return true;
        }

        for (int i = 0; i < index - 1; i++)
            current = current.getNext();

        current.setNext(new ListNode<>(current, x, current.getNext()));
        current.getNext()
               .getNext()
               .setPrevious(current.getNext());
        size++;

        return true;
    }

    public boolean addFirst(anyType x) {
        if (head == null) {
            head = new ListNode<anyType>(null, x, null);
            head.setPrevious(head);
            head.setNext(head);
            size++;

            return true;
        }

        head = new ListNode<anyType>(head.getPrevious(), x, head.getNext());
        size++;

        return true;
    }

    public boolean addLast(anyType x) {
        ListNode<anyType> current = head;

        if (head == null) {
            head = new ListNode<>(null, x, null);
            head.setPrevious(head);
            head.setNext(head);
            size++;

            return true;
        }

        current = head.getPrevious();
        current.setNext(new ListNode<anyType>(current, x, current.getNext()));
        head.setPrevious(current.getNext());
        size++;

        return true;
    }

    public anyType remove(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(index + " is out of bounds!");

        if (index == size - 1)
            return removeLast();

        if (index == 0)
            return removeFirst();

        ListNode<anyType> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        anyType removed = current.getNext()
                                 .getValue();

        current.setNext(current.getNext().getNext());
        current.getNext().setPrevious(current);
        size--;

        return removed;
    }

    public int size() {
        return size;
    }

    public anyType removeFirst() {
        anyType removed = head.getValue();

        head = head.getNext();

        head.getPrevious()
            .getPrevious()
            .setNext(head);
        size--;

        return removed;
    }

    public anyType removeLast() {
        ListNode<anyType> current = head;

        current = current.getPrevious()
                         .getPrevious();

        anyType removed = current.getNext()
                                 .getValue();

        current.setNext(current.getNext()
                               .getNext());
        size--;

        return removed;
    }

    public anyType get(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + " is out of bounds!");

        ListNode<anyType> current = head;

        if (index == size - 1)
            return getLast();

        if (index == 0)
            return getFirst();

        for (int i = 0; i < index; i++)
            current = current.getNext();


        return current.getValue();
    }

    public anyType getFirst() {
        return head == null ? null : head.getValue();
    }

    public anyType getLast() {
        return head == null ? null : head.getPrevious()
                                         .getValue();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public anyType set(int index, anyType x) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + " is out of bounds!");

        ListNode<anyType> current = head;

        if (index == size - 1) {
            current = current.getPrevious();

            anyType removed = current.getValue();

            current.setValue(x);

            return removed;
        }

        for (int i = 0; i < index; i++)
            current = current.getNext();

        anyType removed = current.getValue();

        current.setValue(x);

        return removed;
    }

    public void showList() {
        System.out.println(toString());
    }

    public String toString() {
        StringBuilder     stringBuilder = new StringBuilder("[");
        ListNode<anyType> current       = head;

        if (size == 0) {
            return stringBuilder.append("]")
                                .toString();
        }

        for (int i = 0; i < size; i++) {
            stringBuilder.append(current.getValue())
                         .append(", ");
            current = current.getNext();
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));

        return stringBuilder.toString().trim() + "]";
    }
}