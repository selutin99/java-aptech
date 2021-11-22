package com.galua.reflection.defaulter;

import com.galua.reflection.annotation.DefaultClass;
import com.galua.reflection.annotation.DefaultMethod;
import com.galua.reflection.model.Cat;

@DefaultClass(targets = {Cat.class})
public class CatDefaulter {

    @DefaultMethod(fieldName = "name")
    public String setDefaultCatName() {
        return "test cat";
    }

    @DefaultMethod(fieldName = "voice")
    public String setDefaultCatVoice() {
        return "Meow";
    }

    @DefaultMethod(fieldName = "unexistingCatClassAttribute")
    public String setDefaultCatUnexistingAttribute() {
        return "test";
    }

    @DefaultMethod(fieldName = "age")
    public Long setWrongTypeAttribute() {
        return 1L;
    }

    public String notDefaultMethod() {
        return "test";
    }
}
