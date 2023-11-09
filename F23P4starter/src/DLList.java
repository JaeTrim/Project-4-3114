
/**
 * DLList Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-09-07
 * @param <T>
 *            is element of list
 */
public class DLList<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Constructor to call helper setUp
     */
    public DLList() {
        head = new DLList.Node<T>(null);
        tail = new DLList.Node<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }


    /**
     * DLList Constructor Setup
     */
    private void reset() {
        head = new DLList.Node<T>(null);
        tail = new DLList.Node<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }


    /**
     * Number of elements in list
     * 
     * @return number of elements
     */
    public int size() {
        return size;
    }


    /**
     * Checks if array is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Removes all elements in list
     */
    public void clear() {
        reset();
    }


    /**
     * Gets block at index
     * 
     * @param index
     *            of block
     * @return the block
     */
    public T get(int index) {
        T node = nodeAtIndex(index).getData();
        return node;
    }


    /**
     * Adds in increasing order
     * 
     * @param block
     *            representing the added block
     */
    public void add(T block) {
        add(size(), block);
    }


    /**
     * Adds block at the index
     * 
     * @param index
     *            where block is
     * @param block
     *            representing block to add
     */
    public void add(int index, T block) {
        if (block == null) {
            throw new IllegalArgumentException(
                "Can't add null objects to list");
        }

        Node<T> after;
        if (index == size) {
            after = tail;
        }
        else {
            after = nodeAtIndex(index);
        }
        Node<T> add = new Node<T>(block);
        add.setPrevious(after.previous());
        add.setNext(after);
        after.previous().setNext(add);
        after.setPrevious(add);
        size++;
    }


    /**
     * Removes block at index
     * 
     * @param index param
     * @return true if removed
     */
    public boolean remove(int index) {
        Node<T> remove = nodeAtIndex(index);
        remove.previous().setNext(remove.next());
        remove.next().setPrevious(remove.previous());
        size--;
        return true;
    }


    /**
     * Removes the first block in the list that equals the block
     *
     * @param block
     *            the block to remove
     * @return true if the block was found and removed
     */

    public boolean remove(T block) {
        Node<T> curr = head.next();
        while (!curr.equals(tail)) {
            if (curr.getData().equals(block)) {
                curr.previous().setNext(curr.next());
                curr.next().setPrevious(curr.previous());
                size--;
                return true;
            }
            curr = curr.next();
        }
        return false;
    }


    /**
     * Gets the node at the inputed index
     * 
     * @param index
     *            passed
     * @return node at index
     */
    private Node<T> nodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = head.next();
        for (int i = 0; i < index; i++) {
            curr = curr.next();
        }
        return curr;
    }
    
    

    /**
     * Private Node class for doubly linked list
     * 
     */
    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;

        /**
         * Creates new Node
         * 
         * @param data
         *            given
         */
        public Node(T data) {
            this.data = data;
        }


        /**
         * Sets the node after node
         * 
         * @param next
         *            node
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }


        /**
         * Sets the node before
         * 
         * @param prev
         *            node
         */
        public void setPrevious(Node<T> prev) {
            previous = prev;
        }


        /**
         * Gets next node
         * 
         * @return next node
         */
        public Node<T> next() {
            return next;
        }


        /**
         * Gets previous node
         * 
         * @return previous node
         */
        public Node<T> previous() {
            return previous;
        }

        /**
         * Gets data in node
         * 
         * @return data in node
         */
        public T getData() {
            return data;
        }
    }
}
