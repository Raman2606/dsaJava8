package collections.customimplementations;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<E> implements Iterable<E> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    protected int modCount;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(E element) {
        //check size constraint, exppand if needed
        ensureCapacity();
        // add the element
        elements[size++] = element;
        // flag modification
        modCount++;


    }

    public E remove(int index) {
        //check whether the index is allowed
        rangeCheck(index);
        E e = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        modCount++;
        return e;
    }

    public E get(int index) {
        rangeCheck(index);
        E e = (E) elements[index];
        return e;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newArr = new Object[(int) Math.ceil(size * 1.5)];
            System.arraycopy(elements, 0, newArr, 0, elements.length);
            elements = newArr;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    private class MyIterator implements Iterator<E> {

        private int cursor = 0;
        private final int expectedModCount;

        public MyIterator() {

            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) elements[cursor++];
        }
    }
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}