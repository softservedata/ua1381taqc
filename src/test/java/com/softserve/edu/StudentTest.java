package com.softserve.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentTest {

    @Test
    public void checkExist() {
        List<Student> actual = Student.getStudents();
        Assertions.assertTrue(actual.contains(new Student("Ira", 20)));
    }

    @Test
    public void checkExist2() {
        Student expected = new Student("Ira", 20);
        Student actual = Student.getStudents().get(0);
        Assertions.assertEquals(expected, actual);
    }
}
