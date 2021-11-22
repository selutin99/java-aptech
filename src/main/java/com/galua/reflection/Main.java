package com.galua.reflection;

import com.galua.reflection.model.Cat;
import com.galua.reflection.model.Student;

public class Main {

    public static void main(String[] args) {
        System.out.println("Wait when initialize default classes...");
        DefaultValues defaultValues = new DefaultValues();

        // Generate test data
        Cat cat = Cat.builder()
                .age(1)
                .name("cat")
                .build();

        Student student = Student.builder()
                .age(25)
                .id(1L)
                .build();

        // Set default fields and rewrite existing field values
        defaultValues.setDefaults(cat, student);

        // Show result
        System.out.println(cat);
        System.out.println(student);
    }
}
