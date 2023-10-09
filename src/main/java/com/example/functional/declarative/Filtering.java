package com.example.functional.declarative;

import com.example.functional.mockdata.MockData;
import com.example.functional.model.Car;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    /**
     * Predicate
     *      filter(Predicate<? super T> predicate)
     *      任意の引数を受け取りtestメソッドで比較した結果をbooleanで返します。
     * @throws Exception
     */
    @Test
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();

        Predicate<Car> carPredicateLessThan20kPrice = car -> car.getPrice() < 20_000.00;
//        // メソッドで書くとこうなるのを上のようなラムダで省略できる
//        Predicate<Car> carPredicate = new Predicate<Car>() {
//            @Override
//            public boolean test(Car car) {
//                return false;
//            }
//        };

        Predicate<Car> carPredicateByColorYellow = car -> car.getColor().equals("Yellow");

        List<Car> carLessThan20k = cars.stream()
                .filter(carPredicateLessThan20kPrice)
                .filter(carPredicateByColorYellow)
                .collect(Collectors.toList());

        // System.out::printlnはメソッド参照という書き方
        //      ラムダ構文だとx -> System.out.println(x)　を以下のようにさらに短く書くことができます
        //      ただし引数の中身など理解していないとよく分からなくなるので最初はラムダ構文のほうが直感的かもしれません
        carLessThan20k.forEach(System.out::println);
        // .forEachは内部的にはConsumerを引数としてとる
        // Consumer<T>  void accept(T value)  T型の引数をとりacceptというvoidメソッドを実行する

    }

    @Test
    public void dropWhile() throws Exception {
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("using dropWhile");
        Stream.of(2, 4, 6, 8, 9, 10, 12).dropWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

    }

    @Test
    public void takeWhile() throws Exception {
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 6, 8, 9, 10, 12).takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = Arrays.stream(numbers).filter(n -> n == 50)
                .findFirst()
                .orElse(-1);
        System.out.println(result);

    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        int result = Arrays.stream(numbers).filter(n -> n == 9)
                .findAny()
                .orElse(-1);
        System.out.println(result);
    }

    @Test
    public void allMatch() throws Exception {
        int[] even = {2, 4, 6, 8, 10};
        boolean allMatch = Arrays.stream(even).allMatch(n -> n % 2 == 0);
        System.out.println(allMatch);
    }

    @Test
    public void anyMatch() throws Exception {
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};
        boolean anyMatch = Arrays.stream(evenAndOneOdd).anyMatch(n -> !(n % 2 == 0));
        System.out.println(anyMatch);
    }
}
