package hw7;

public class DoubleLinkedList {

    private Node head;//голова списка
    private Node tail; // хвост списка

    /**
     * Узел для хранения в списке
     */
    private static class Node {
        int num; // данные - собачка
        Node next;// ссылка на следующий узел
        Node prev;// ссылка на предыдущий узел

        public Node(int num) {
            this.num = num;
        }
    }

    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Метод вставки элемент в хвост списка
     *
     * @param num
     */
    public void pushTail(int num) {
        Node newNode = new Node(num);
        newNode.prev = tail;
        newNode.next = null;
        if (tail != null)
            tail.next = newNode;
        else
            head = newNode;
        tail = newNode;
    }

    public Integer peekTail(){
        if (isEmpty()) return null;
        return tail.num;
    }

    /**
     * Метод снятия элемента с головы списка.
     *
     * @return элемент
     */
    public Integer popHead() {
        int tmp = head.num;
        if (isEmpty()) return null;

        if (head.next != null) {
            head = head.next;
            head.prev = null;
        } else {
            head = null;
            tail = null;
        }
        return tmp;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current.toString());
            current = current.next;
            sb.append((current == null) ? " ]" : "; ");
        }
        return sb.toString();
    }
}
