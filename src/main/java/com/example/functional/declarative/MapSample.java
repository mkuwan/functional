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

    public MapSample() throws IOException {
    }

    /**
     * mapの基本的な使い方
     * @throws IOException
     */
    @Test
    public void transformWithMap001() throws IOException {
        var result = people.stream()
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
        Predicate<PersonDTO> over20AndUnder30 = x -> x.getAge() >= 20 && x.getAge() < 22;

        var result = people.stream()
                .map(p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge()))
                .filter(over20AndUnder30)
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }

    /**
     * map内のFunction<T,R>を取り出した
     * @throws IOException
     */
    @Test
    public void transformWithMap004() throws IOException {
        Predicate<PersonDTO> over20AndUnder30 = x -> x.getAge() >= 20 && x.getAge() < 22;
        // map内を取り出した
        Function<Person, PersonDTO> transformDto = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());

        var result = people.stream()
                .map(transformDto)
                .filter(over20AndUnder30)
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
        Predicate<PersonDTO> over20AndUnder30 = x -> x.getAge() >= 20 && x.getAge() < 22;
        Function<Person, PersonDTO> transformDto = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());

        var result = people.stream()
                .map(transformDto)
                .filter(over20AndUnder30)
                .collect(Collectors.toList());

        // メソッド構文に変更
        result.forEach(System.out::println);
    }
}
