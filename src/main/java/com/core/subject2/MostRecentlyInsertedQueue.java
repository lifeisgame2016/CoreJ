package com.core.subject2;


import java.util.*;

public class MostRecentlyInsertedQueue<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int modCount;

    private int size = 0;
    private int capacity;
    private static final int DEFAULT_INITIAL_SIZE = 8;

    MostRecentlyInsertedQueue(){
        this.capacity = DEFAULT_INITIAL_SIZE;
    }

    MostRecentlyInsertedQueue(int capacity){
        this.capacity = capacity > 0 ? capacity : DEFAULT_INITIAL_SIZE;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        if(o == null){
            for(Node<E> e = first; e != null; e = e.next){
                if(e.element == null)
                    return true;
            }
        } else {
            for(Node<E> e = first; e != null; e = e.next){
                if(o.equals(e.element)){
                    return true;
                }
            }
        }
        return false;
    }

    // fail-fast iterator
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cursor = first;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) throw new ConcurrentModificationException("Iterator crashed :(");
                if (!hasNext()) throw new NoSuchElementException("Ooops..!");
                E nextElement = cursor.element;
                cursor = cursor.next;
                return nextElement;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for(Node<E> e = first; e != null; e = e.next){
           result[i++] = e.element;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        offer(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        poll();
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if ( isEmpty() ^ c.isEmpty()){
            return false;
        }

        E[] collection = (E[]) toArray();
        long countElement = c.stream().filter(i -> collection.equals(i)).count();


        for(Node<E> e = first; e != null; e = e.next){
           if(!c.stream().anyMatch(e.element::equals)){
              return false;
           }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
       for(Node<E> iterator = first; iterator != null; ){
           Node<E> next = iterator.next;
           iterator.element = null;
           iterator.next = null;
           iterator.prev = null;
           iterator = next;
       }
        first = last = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        Node<E> newLast = this.last;
        Node<E> newNode = new Node<E>(newLast, e, null);
        last = newNode;
        if(newLast == null){
            this.first = newNode;
        } else {
            checkSize();
            newLast.next = newNode;
        }
        size++;
        modCount++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        Node<E> eNode = first;
        return eNode == null ? null : deleteFirst(eNode);
    }

    @Override
    public E element() {
        return last == null ? null : last.element ;
    }

    @Override
    public E peek() {
        Node<E> element = first;
        return element == null ? null : element.element;
    }


    // Node class
    private static class Node<E>{
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    private void checkSize(){
        if (size == capacity){
            Node<E> node = first;
            deleteFirst(node);
        }
    }

    private E deleteFirst(Node<E> deletElement){
        E element = deletElement.element;
        Node<E> next = deletElement.next;
        deletElement.element = null;
        deletElement.next = null;
        first = next;
        next.prev = null;
        size--;
        modCount++;
        return element;
    }



}

