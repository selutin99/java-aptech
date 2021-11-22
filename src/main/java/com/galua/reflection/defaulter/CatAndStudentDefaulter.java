package com.galua.reflection.defaulter;

import com.galua.reflection.annotation.DefaultClass;
import com.galua.reflection.annotation.DefaultMethod;
import com.galua.reflection.model.Cat;
import com.galua.reflection.model.Student;

@DefaultClass(targets = {Cat.class, Student.class})
public class CatAndStudentDefaulter {

    @DefaultMethod(fieldName = "age")
    public Integer setDefaultStudentAge() { return 22; }
}
