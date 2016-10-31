package com.core.subject2;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MostRecentlyInsertedQueueTest {

    private MostRecentlyInsertedQueue<Integer> queue;

    @Before
    public void initMostRecentlyInsertedQueue(){

        queue = new MostRecentlyInsertedQueue<Integer>(4);
    }


    @Test
    public void size() throws Exception {
        int expected = 0;
        int actual = queue.size();
        assertEquals(expected, actual);

        expected = 4;
        queue.offer(3);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        actual = queue.size();
        assertEquals(expected, actual);
    }

    @Test
    public void isEmpty() throws Exception {
        boolean actual = queue.isEmpty();
        assertTrue(actual);

        queue.offer(2);
        actual = queue.isEmpty();
        assertFalse(actual);
    }

    @Test
    public void contains() throws Exception {
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        boolean expected = queue.contains(6);
        assertTrue(expected);

        expected = queue.contains(11);
        assertFalse(expected);
    }

    @Test
    public void iterator() throws Exception {
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        List<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = queue.iterator();
        while(iterator.hasNext()){
          list.add(iterator.next());
        }
        int expected = 3;
        int actual = list.size();
        assertEquals(expected, actual);
    }

    @Test
    public void toArray() throws Exception {
        queue.offer(11);
        queue.offer(22);
        queue.offer(33);
        Object[] actualArr = new Object[queue.size()];
        actualArr = queue.toArray();
        Object[] expectedArr = {11,22,33};
        assertArrayEquals(expectedArr, actualArr);
    }


    @Test
    public void clear() throws Exception {
        queue.offer(5);
        queue.offer(6);
        queue.clear();
        boolean actual= queue.isEmpty();
        assertTrue(actual);
    }

    @Test
    public void offer() throws Exception {
        queue.offer(1);
        queue.offer(11);
        queue.offer(333);
        List<Integer> listActual = new ArrayList<>();
        queue.stream().forEach(listActual::add);

        int actual = listActual.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void poll() throws Exception {
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Integer actual = queue.poll();
        Integer expected = new Integer(3);
        assertEquals(expected, actual);
    }

    @Test
    public void element() throws Exception {
        queue.offer(8);
        queue.offer(9);
        Integer expected = Integer.valueOf(9);
        Integer actual = queue.element();

        assertEquals(expected, actual);
    }

    @Test
    public void peek() throws Exception {
        queue.offer(8);
        queue.offer(9);
        Integer expected = Integer.valueOf(8);
        Integer actual = queue.peek();

        assertEquals(expected, actual);
    }

}