package knowledge;

import java.lang.reflect.Array;

public class SelfPriorityBlockingQueue<E> {

    private Object[] queue;

    int size;

    public SelfPriorityBlockingQueue() {

    }

    public void enqueue(E ele) {
        shiftUpByComparable(this.queue, ele, size++);
    }

    public static <T> void shiftUpByComparable(Object[] array, T x,
            int k) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (key.compareTo((T) e) >= 0) {
                break;
            }
            array[k] = e;
            k = parent;
        }
        array[k] = x;
    }

    public static <T extends Comparable<? super T>> void shiftDownComparable(Object[] array,
            int index, int size, T key) {
        //size include
        int half = size >>> 1, child;
        while (index < half) {
            child = index >> 1 + 1;
            Object c = array[child];
            if ((child + 1) < size
                    && ((T) array[child + 1]).compareTo((T) c) <= 0) {
                child = child + 1;
                c = array[child];
            }
            array[index] = c;
            index = child;
        }
        array[index] = key;
    }
}
