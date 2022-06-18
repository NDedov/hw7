package hw7;

/**
 * Класс Очередь, унаследованный от двусвязного списка
 */
public class Queue extends DoubleLinkedList{

    public Queue() {}

    public void add(int num){
        super.pushTail(num);
    }

    public int remove(){
        return super.popHead();
    }

    public int peek() {
        return super.peekTail();
    }
}