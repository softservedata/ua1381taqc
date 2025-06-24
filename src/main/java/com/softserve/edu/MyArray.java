package com.softserve.edu;

public class MyArray {

    public int[] getArray() {
        return new int[]{5, 4, 3, 2, 1};
    }

    public boolean isContain(int[] array, int number) {
        boolean result = false;
        for (int current : array) {
            result = result || (current == number);
        }
        return result;
    }
}
