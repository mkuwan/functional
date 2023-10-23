package com.example.functional.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public  class  GenericSample<T> {
    public void test(T message){
        System.out.println(message.toString());
    }
}



class StandardClassSample {
    public void test(String message){
        System.out.println(message);
    }
}

class SampleTest {
    @Test
    public void StandardTest(){
        StandardClassSample sample = new StandardClassSample();
        sample.test("普通のクラスです");

        // 数字は扱えない
//        sample.test(123456);
    }

    @Test
    public void GenericSampleTest() {
        GenericSample<String> sample = new GenericSample<>();
        System.out.println("文字列(String)");
        sample.test("ジェネリクスのクラスです");
//        sample.test(123456);

        GenericSample<Integer> sampleInteger = new GenericSample<>();
        System.out.println("数字(Integer)");
        sampleInteger.test(123456);
//        sampleInteger.test("123");
    }
}

