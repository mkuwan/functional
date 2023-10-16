package com.example.functional.declarative;


import com.example.functional.mockdata.MockData;
import com.example.functional.model.Person;
import com.example.functional.model.PersonDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * StreamAPIのmapとreduceのサンプル
 */
public class MapSample {

    List<Person> people = MockData.getPeople();
    long startTime = 0L;
    long endTime = 0L;

    public MapSample() throws IOException {
    }

    /**
     * mapの基本的な使い方
     * mapperは　Function<? super T, ? extends R>　が実装されることを求めている
     * @throws IOException
     */
    @Test
    public void transformWithMap001() throws IOException {
        List<PersonDTO> result = people.stream()
                .map(p -> {
                    return new PersonDTO(p.getId(), p.getFirstName(), p.getAge());
                })
                .filter(x -> x.getAge() >= 20 && x.getAge() < 22)
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }

    /**
     * map内をラムダ構文に変更
     * @throws IOException
     */
    @Test
    public void transformWithMap002() throws IOException {

        var result = people.stream()
                // map内をラムダ構文に変更した場合
                .map(p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge()))
                .filter(x -> x.getAge() >= 20 && x.getAge() < 22)
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }

    /**
     * filter内のPredicateを取り出した
     * @throws IOException
     */
    @Test
    public void transformWithMap003() throws IOException {
         // filter内を取り出した
        Predicate<PersonDTO> over20AndUnder22 = x -> x.getAge() >= 20 && x.getAge() < 22;

        var result = people.stream()
                .map(p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge()))
                .filter(over20AndUnder22)
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }

    /**
     * map内のFunction<T,R>を取り出した
     * @throws IOException
     */
    @Test
    public void transformWithMap004() throws IOException {
        Predicate<PersonDTO> over20AndUnder22 = x -> x.getAge() >= 20 && x.getAge() < 22;
        // map内を取り出した
        Function<Person, PersonDTO> transformDto = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());

        var result = people.stream()
                .map(transformDto)
                .filter(over20AndUnder22)
                .collect(Collectors.toList());

        // メソッド参照に変更
        result.forEach(x -> System.out.println(x));
    }

    /**
     * System.out.printlnをメソッド参照に変更した
     * @throws IOException
     */
    @Test
    public void transformWithMap005() throws IOException {
        Predicate<PersonDTO> over20AndUnder22 = x -> x.getAge() >= 20 && x.getAge() < 22;
        Function<Person, PersonDTO> transformDto = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());

        var result = people.stream()
                .map(transformDto)
                .filter(over20AndUnder22)
                .collect(Collectors.toList());

        // メソッド構文に変更
        result.forEach(System.out::println);
    }

    @Test
    public void transformWithMapPerformance() throws IOException {
        // 1秒 = 1,000ミリ秒 = 100万マイクロ秒 = 10億ナノ秒
        // 1ミリ秒 = 1,000マイクロ秒
        // filterを先にした方が速いがわずかの差
        startTime = System.nanoTime();
        var result2 = people.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() < 22)
                .map(p -> {
                    return new PersonDTO(p.getId(), p.getFirstName(), p.getAge());
                })

                .collect(Collectors.toList());
        endTime = System.nanoTime();
        System.out.println(((double)(endTime - startTime) / 1_000_000) + "ミリ秒");

        startTime = System.nanoTime();
        var result = people.stream()
                .map(p -> {
                    return new PersonDTO(p.getId(), p.getFirstName(), p.getAge());
                })
                .filter(x -> x.getAge() >= 20 && x.getAge() < 22)
                .collect(Collectors.toList());
        endTime = System.nanoTime();
        System.out.println(((double)(endTime - startTime) / 1_000_000) + "ミリ秒");



        result.forEach(x -> System.out.println(x));
    }
}
