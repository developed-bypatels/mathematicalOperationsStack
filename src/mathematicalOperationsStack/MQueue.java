/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematicalOperationsStack;

/**
 *
 * @author I am Prerak Patel and my student number is 000736376 and I haven't shared my program
 */
import java.util.LinkedList;

public class MQueue<E> {

    private LinkedList<E> q = new LinkedList<>();

    public void enqueue(E e) {
        q.addLast(e);
    }

    public E dequeue() {
        return q.removeFirst();
    }

    public E peek() {
        return q.getFirst();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public boolean removeAll() {
        while (!q.isEmpty()) {
            q.remove();
        }
        return true;
    }

}
