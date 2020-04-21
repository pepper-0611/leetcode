package knowledge;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockingQueue的3种接口 add & remove 队列满或空时抛出异常 put & take 队列满或空时阻塞 offer & poll 队列满或者空时返回特殊值 false OR
 * null. offer(timeout, unit) & poll 超时阻塞，超时之后失败返回特殊值。
 */
public class SelfArrayBlockingQueue<E> {

    final int capacity;
    final Object[] items;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition(), notFull = lock.newCondition();
    int cnt, putIndex, takeIndex;

    public SelfArrayBlockingQueue(int size) {
        capacity = size;
        items = new Object[size];
        cnt = putIndex = takeIndex = 0;
    }


    public boolean add(E e) {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (cnt == capacity) {
                throw new IllegalStateException("full queue!");
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
        return true;
    }

    public boolean offer(E e) {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (cnt == capacity) {
                return false;
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E object) {
        final Object[] items = this.items;
        items[putIndex] = object;
        if (++putIndex == capacity) {
            putIndex = 0;
        }
        cnt++;
        notEmpty.signal();
    }

    private E dequeue() {
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E res = (E) items[takeIndex];
        items[takeIndex] = null; // release reference.
        if (++takeIndex == capacity) {
            takeIndex = 0;
        }
        cnt--;
        notFull.signal();
        return res;
    }


    public E remove() {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (cnt == 0) {
                throw new IllegalStateException("null queue");
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E poll() {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return cnt == 0 ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    public void put(E e) throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (cnt == capacity) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        Long waitNanos = unit.toNanos(timeout);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            while (cnt == capacity) {
                if (waitNanos <= 0) {
                    return false;
                }
                waitNanos = notFull.awaitNanos(waitNanos);
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (cnt == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        ReentrantLock lock = this.lock;
        Long waitNanos = unit.toNanos(timeout);
        try {
            while (cnt == 0) {
                if (waitNanos <= 0) {
                    return null;
                }
                waitNanos = notEmpty.awaitNanos(waitNanos);
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
}
