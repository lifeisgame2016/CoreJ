package com.core.subject1;

import org.junit.*;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by MDenisenko on 21.10.16.
 */
public class MyListArrayTest {

    private MyListArray list;

    @Before
    public  void initMyListArray(){
        list = new MyListArray(8);
        list.addAll(1,2,3,4,5,6);
    }

    @Test
    public void addAllTest() throws Exception {
        int[] expectList = {1,2,3,4,5,6};
        int[] actuals = list.getArray();
        assertArrayEquals(expectList, actuals);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void edgesOfListSizeTest() {
        MyListArray tempList = new MyListArray(2);
        tempList.addAll(1, 2);

        System.out.println(tempList.get(2));

    }

    @Test
    public void addToTopLogicTest() {
        int element = 3;
        int[] collection = new int[]{1, 2};
        int[] result = IntStream.concat(IntStream.of(element),
                IntStream.of(collection).map(el -> el + element)).toArray();
        assertArrayEquals(new int[]{3, 4, 5}, result);
    }

    @Test
    public void getTest() throws Exception {
        int expect = 3;
        int actuals = list.get(2);
        assertEquals(expect, actuals);
    }

    @Test
    public void sizeTest() throws Exception {
        int expect = 6;
        int actuals = list.size();
        assertEquals(expect, actuals);
    }

    @Test
    public void addToTopTest() throws Exception {
        int[] expectList = {2,3,4,5,6,7,8};
        list.addToTop(2);
        int[] actualsList = list.getArray();
        assertArrayEquals(expectList, actualsList);
    }

    @Test
    public void addInMiddleTest() throws Exception {
        int[] expectList = {5,6,7,4,8,9,10};
        list.addInMiddle(4,3);
        int[] actualsList = list.getArray();
        assertArrayEquals(expectList, actualsList);
    }

    @Test
    public void addToEndTest() throws Exception {
        int[] expectList = {3,4,5,6,7,8,2};
        list.addToEnd(2);
        int[] actualsList = list.getArray();
        assertArrayEquals(expectList, actualsList);
    }

    @Test
    public void removeIndexTest() throws Exception {
        int[] expectList =  {-3,-2,-1,0,1,2};
        list.removeIndex(3);
        int[] actualsList = list.getArray();
        assertArrayEquals(expectList, actualsList);
    }

    @Test
    public void removeValueTest() throws Exception {
        int[] expectList =  {-6,-5,-4,-3,-2,-1};
        list.removeValue(7);
        int[] actualsList = list.getArray();
        assertArrayEquals(expectList, actualsList);
    }



}