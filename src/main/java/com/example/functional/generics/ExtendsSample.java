package com.example.functional.generics;

import org.junit.jupiter.api.Test;

public class ExtendsSample {
}
class BoundedClass<T extends Number> {
    private T value;

    public BoundedClass(T value)
    {
        this.value = value;
    }
}

class BoundedTest {
    @Test
    public void boundedClassTest() {
        BoundedClass<Integer> boundedClass = new BoundedClass<>(123);
        BoundedClass<Double> boundedClass2 = new BoundedClass<>(123.4d);
//        BoundedClass<String> boundedClass3 = new BoundedClass<>("123.4");
    }
}
