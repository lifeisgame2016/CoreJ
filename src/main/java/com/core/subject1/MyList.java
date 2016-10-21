package com.core.subject1;

/**
 * Created by MDenisenko on 19.10.16.
 */
public interface MyList {

    void addToTop(int element);

    void addInMiddle(int element, int index);

    void addToEnd(int element);

    void removeIndex(int index);
    void removeValue(int element);

    int get(int index);
    int size();
    void addAll(int... numbers);
    int[] getArray();

}
