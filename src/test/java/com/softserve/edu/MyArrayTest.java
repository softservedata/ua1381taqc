package com.softserve.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayTest {

    @Test
    public void checkLength() {
        MyArray myArray = new MyArray();
        Assertions.assertEquals(5, myArray.getArray().length);
    }

    @Test
    public void checkContent() {
        MyArray myArray = new MyArray();
        int[] expected = {5, 4, 3, 2, 1};
        Assertions.assertArrayEquals(expected, myArray.getArray());
        //
        boolean isEqual = true;
        int[] actual = myArray.getArray();
        for (int i=0; i<expected.length; i++) {
            isEqual = isEqual && (actual[i] == expected[i]);
        }
        Assertions.assertTrue(isEqual);
    }
}
