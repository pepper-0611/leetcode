package knowledge;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SelfLinkedBlockingQueue<E> {

    ReentrantLock putLock = new ReentrantLock();
    ReentrantLock takeLock = new ReentrantLock();
    Condition notFull = putLock.newCondition();
    Condition notEmpty = takeLock.newCondition();
    int capacity;
    AtomicInteger cnt = new AtomicInteger(0);
    Node<E> head;
    Node<E> last;

    SelfLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        head = last = new Node<>(null);
    }

    static class Node<E> {

        Node(E item) {
            this.item = item;
        }

        E item;
        Node<E> next;
    }

    public boolean put(E ele) throws InterruptedException {
        putLock.lock();
        int c;
        try {
            while (cnt.get() == capacity) {
                notFull.await();
            }
            enqueue(ele);
            c = cnt.getAndIncrement();
            //notify one by one.
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }

        if (c == 0) {
            notEmptyNotify();
        }
        return true;
    }


    public E take() throws InterruptedException {
        takeLock.lock();
        int c;
        E x;
        try {
            while (cnt.get() == 0) {
                notEmpty.await();
            }
            x = dequeue();
            c = cnt.getAndDecrement();
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity) {
            notFullNotify();
        }
        return x;
    }

    private void notEmptyNotify() {
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void notFullNotify() {
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void enqueue(E e) {
        last = last.next = new Node<>(e);

    }

    private E dequeue() {
        Node<E> h = this.head;
        Node<E> first = h.next;
        h.next = null;
        this.head = first;
        E x = first.item;
        first.item = null;
        return x;
    }
}
