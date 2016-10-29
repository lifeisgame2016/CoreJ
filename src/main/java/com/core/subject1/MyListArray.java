package com.core.subject1;

import java.util.stream.IntStream;


public class MyListArray implements MyList {

    private final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int[] collection;

    MyListArray() {
        collection = new int[DEFAULT_CAPACITY];
    }

    MyListArray(int capacity) {
        if (capacity > 0) {
            collection = new int[capacity];
        } else {
            collection = new int[DEFAULT_CAPACITY];
        }
    }

    public void addAll(int... numbers) {
        int lengthArray = numbers.length;
        checkCapacity(lengthArray);
        System.arraycopy(numbers, 0, collection, capacity, lengthArray);
        capacity += lengthArray;
    }


    public int get(int index) {
        checkIndex(index);
        return collection[index];
    }

    public int size() {
        return capacity;
    }

    public void addToTop(int element) {
        checkCapacity(1);
        /*int currentElement, newElement = 0;
        for(int i = 0; i <= size()+1; i++ ){
            currentElement = newElement;
            newElement = collection[i];
            collection[i] = currentElement + element;
        }*/
        collection = IntStream.concat(IntStream.of(element),
                IntStream.of(collection).map(el -> el + element)).toArray();
        capacity++;
    }

    public void addInMiddle(int element, int index) {
        checkIndex(index);
        checkCapacity(1);
        if(index == 0){
            addToTop(element);
        } else if (index == capacity){
            addToEnd(element);
        } else {
            int[] newArray = new int[collection.length];
            for(int i = 0; i < capacity+1; i++){
                if(i < index){
                    newArray[i] = collection[i] + element;
                } else if( i == index){
                    newArray[i] = element;
                } else {
                    newArray[i] = collection[i-1] + element;
                }
            }
            collection = newArray;
        }
        capacity++;

    }

    public void addToEnd(int element) {
        checkCapacity(1);
        IntStream.range(0, capacity).forEach(i -> collection[i] += element);
        int indxElement = capacity++;
        collection[indxElement] = element;
    }

    public void removeIndex(int index) {
        int removeElement = collection[index];
        IntStream.range(0, size()).forEach((i) -> collection[i] -= removeElement);
    }

    public void removeValue(int element) {
        IntStream.range(0, size()).forEach((i) -> collection[i] -= element);
    }

    public int[] getArray() {
        int[] intArray = new int[capacity];
        System.arraycopy(collection, 0, intArray, 0, capacity);
        return intArray;
//        return Arrays.copyOf(collection, capacity);
    }

    private void checkIndex(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" index  = " + index);
        }
    }

    private void checkCapacity(int countElements) {
        int sizeArray = collection.length;
        int newSize = size() + countElements;
        if (newSize >= sizeArray) {
            int[] newArray;
            if (countElements >= DEFAULT_CAPACITY) {
                newArray = new int[(newSize >> 1) + newSize];
            } else {
                newArray = new int[sizeArray + DEFAULT_CAPACITY];
            }
            System.arraycopy(collection, 0, newArray, 0, sizeArray);
            collection = newArray;
        }
    }
}
