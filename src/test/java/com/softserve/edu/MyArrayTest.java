package com.softserve.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
        for (int i = 0; i < expected.length; i++) {
            isEqual = isEqual && (actual[i] == expected[i]);
        }
        Assertions.assertTrue(isEqual);
    }

    public static Object[] arraysProvides() {
        return new Object[]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 3, 2, 1}
        };
    }

    //@Test
    @ParameterizedTest
    @MethodSource("arraysProvides")
    public void checkContent2(int[] expected) {
        MyArray myArray = new MyArray();
        Assertions.assertArrayEquals(expected, myArray.getArray());
    }

    @ParameterizedTest
    @MethodSource("arraysProvides")
    public void checkConteins(int[] array) {
        MyArray myArray = new MyArray();
        Assertions.assertTrue(myArray.isContain(array, 3));
    }
}
